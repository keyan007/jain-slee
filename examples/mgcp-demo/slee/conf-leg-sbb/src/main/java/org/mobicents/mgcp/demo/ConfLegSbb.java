/*
 * CallSbb.java
 *
 * The source code contained in this file is in in the public domain.
 * It can be used in any project or product without prior permission,
 * license or royalty payments. There is  NO WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR STATUTORY, INCLUDING, WITHOUT LIMITATION,
 * THE IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * AND DATA ACCURACY.  We do not warrant or make any representations
 * regarding the use of the software or the  results thereof, including
 * but not limited to the correctness, accuracy, reliability or
 * usefulness of the software.
 */
package org.mobicents.mgcp.demo;

import jain.protocol.ip.mgcp.JainMgcpEvent;
import jain.protocol.ip.mgcp.message.CreateConnection;
import jain.protocol.ip.mgcp.message.CreateConnectionResponse;
import jain.protocol.ip.mgcp.message.DeleteConnection;
import jain.protocol.ip.mgcp.message.NotificationRequest;
import jain.protocol.ip.mgcp.message.parms.CallIdentifier;
import jain.protocol.ip.mgcp.message.parms.ConnectionIdentifier;
import jain.protocol.ip.mgcp.message.parms.ConnectionMode;
import jain.protocol.ip.mgcp.message.parms.EndpointIdentifier;
import jain.protocol.ip.mgcp.message.parms.EventName;
import jain.protocol.ip.mgcp.message.parms.NotifiedEntity;
import jain.protocol.ip.mgcp.message.parms.RequestedAction;
import jain.protocol.ip.mgcp.message.parms.RequestedEvent;
import jain.protocol.ip.mgcp.message.parms.ReturnCode;
import jain.protocol.ip.mgcp.pkg.MgcpEvent;
import jain.protocol.ip.mgcp.pkg.PackageName;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.FactoryException;
import javax.slee.RolledBackContext;
import javax.slee.Sbb;
import javax.slee.SbbContext;
import javax.slee.UnrecognizedActivityException;
import javax.slee.facilities.Tracer;

import net.java.slee.resource.mgcp.JainMgcpProvider;
import net.java.slee.resource.mgcp.MgcpActivityContextInterfaceFactory;
import net.java.slee.resource.mgcp.MgcpConnectionActivity;
import net.java.slee.resource.mgcp.MgcpEndpointActivity;

import org.mobicents.mgcp.demo.events.CustomEvent;

/**
 * 
 * @author amit bhayani
 */
public abstract class ConfLegSbb implements Sbb {

	public final static String ENDPOINT_NAME = "/mobicents/media/IVR/$";

	public final static String JBOSS_BIND_ADDRESS = System.getProperty("jboss.bind.address", "127.0.0.1");
	
	public final static String SONG = "http://" + JBOSS_BIND_ADDRESS + ":8080/mgcpdemo/audio/ulaw-fashion.wav";

	public static final int MGCP_PEER_PORT = 2427;
	public static final int MGCP_PORT = 2727;

	private SbbContext sbbContext;

	// MGCP
	private JainMgcpProvider mgcpProvider;
	private MgcpActivityContextInterfaceFactory mgcpAcif;

	private Tracer logger;

	/** Creates a new instance of CallSbb */
	public ConfLegSbb() {
	}

	public void onCallCreated(CustomEvent evt, ActivityContextInterface aci) {

		logger.info("Custom Event received Endpoint = " + evt.getEndpointName() + " Call Id = " + evt.getCallId());
		try {
			String endpointName = evt.getEndpointName();
			EndpointIdentifier endpointID = new EndpointIdentifier(endpointName, JBOSS_BIND_ADDRESS + ":"
					+ MGCP_PEER_PORT);

			EndpointIdentifier ivrEndpointID = new EndpointIdentifier(ENDPOINT_NAME, JBOSS_BIND_ADDRESS + ":"
					+ MGCP_PEER_PORT);

			CallIdentifier callID = new CallIdentifier(evt.getCallId());
			this.setCallIdentifier(callID);

			CreateConnection createConnection = new CreateConnection(this, callID, endpointID, ConnectionMode.SendRecv);
			createConnection.setSecondEndpointIdentifier(ivrEndpointID);

			int txID = mgcpProvider.getUniqueTransactionHandler();

			createConnection.setTransactionHandle(txID);

			MgcpConnectionActivity connectionActivity = null;

			connectionActivity = mgcpProvider.getConnectionActivity(txID, endpointID);
			ActivityContextInterface epnAci = mgcpAcif.getActivityContextInterface(connectionActivity);
			epnAci.attach(sbbContext.getSbbLocalObject());

			mgcpProvider.sendMgcpEvents(new JainMgcpEvent[] { createConnection });
		} catch (Exception e) {
			logger.severe("Error while receiving the Custom Event ", e);
		}
	}

	public void onCreateConnectionResponse(CreateConnectionResponse event, ActivityContextInterface aci) {
		logger.info("Receive CRCX response: " + event.getTransactionHandle());

		ReturnCode status = event.getReturnCode();

		switch (status.getValue()) {
		case ReturnCode.TRANSACTION_EXECUTED_NORMALLY:

			this.setEndpointIdentifier(event.getSecondEndpointIdentifier());
			this.setConnectionIdentifier(event.getSecondConnectionIdentifier().toString());

			logger.info("The IVR Connected EndpointID = " + this.getEndpointIdentifier() + " ConnectionID = "
					+ this.getConnectionIdentifier());

			sendRQNT(SONG, true);
			
			break;
		default:
			logger.severe("CRCX Response returned " + status);
		}
	}

	private void sendRQNT(String mediaPath, boolean createActivity) {
		EndpointIdentifier endpointID = this.getEndpointIdentifier();

		NotificationRequest notificationRequest = new NotificationRequest(this, endpointID, mgcpProvider
				.getUniqueRequestIdentifier());

		EventName[] signalRequests = { new EventName(PackageName.Announcement, MgcpEvent.ann.withParm(mediaPath)) };
		notificationRequest.setSignalRequests(signalRequests);

		RequestedAction[] actions = new RequestedAction[] { RequestedAction.NotifyImmediately };

		ConnectionIdentifier connectionIdentifier = new ConnectionIdentifier(this.getConnectionIdentifier());
		RequestedEvent[] requestedEvents = {
				new RequestedEvent(new EventName(PackageName.Announcement, MgcpEvent.oc), actions),
				new RequestedEvent(new EventName(PackageName.Announcement, MgcpEvent.of), actions) };

		notificationRequest.setRequestedEvents(requestedEvents);
		notificationRequest.setTransactionHandle(mgcpProvider.getUniqueTransactionHandler());

		NotifiedEntity notifiedEntity = new NotifiedEntity(JBOSS_BIND_ADDRESS, JBOSS_BIND_ADDRESS, MGCP_PORT);
		notificationRequest.setNotifiedEntity(notifiedEntity);

		if (createActivity) {
			MgcpEndpointActivity endpointActivity = null;
			try {
				endpointActivity = mgcpProvider.getEndpointActivity(endpointID);
				ActivityContextInterface epnAci = mgcpAcif.getActivityContextInterface(endpointActivity);
				epnAci.attach(sbbContext.getSbbLocalObject());
			} catch (FactoryException ex) {
				ex.printStackTrace();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			} catch (UnrecognizedActivityException ex) {
				ex.printStackTrace();
			}
		} // if (createActivity)

		mgcpProvider.sendMgcpEvents(new JainMgcpEvent[] { notificationRequest });

		logger.info(" NotificationRequest sent");
	}

	public void onCallTerminated(CustomEvent evt, ActivityContextInterface aci) {
		logger.info("Conference Terminated " + evt.getEndpointName() + " callId = " + evt.getCallId());

		EndpointIdentifier endpointID = this.getEndpointIdentifier();
		DeleteConnection deleteConnection = new DeleteConnection(this, this.getCallIdentifier(), endpointID);

		deleteConnection.setTransactionHandle(this.mgcpProvider.getUniqueTransactionHandler());
		mgcpProvider.sendMgcpEvents(new JainMgcpEvent[] { deleteConnection });
	}

	public void setSbbContext(SbbContext sbbContext) {
		this.sbbContext = sbbContext;
		this.logger = sbbContext.getTracer(ConfLegSbb.class.getSimpleName());
		try {
			Context ctx = (Context) new InitialContext().lookup("java:comp/env");

			// initialize media api

			mgcpProvider = (JainMgcpProvider) ctx.lookup("slee/resources/jainmgcp/2.0/provider/demo");
			mgcpAcif = (MgcpActivityContextInterfaceFactory) ctx.lookup("slee/resources/jainmgcp/2.0/acifactory/demo");

		} catch (Exception ne) {
			logger.severe("Could not set SBB context:", ne);
		}
	}

	public abstract EndpointIdentifier getEndpointIdentifier();

	public abstract void setEndpointIdentifier(EndpointIdentifier endpointIdentifier);

	public abstract CallIdentifier getCallIdentifier();

	public abstract void setCallIdentifier(CallIdentifier callIdentifier);

	public abstract String getConnectionIdentifier();

	public abstract void setConnectionIdentifier(String connectionIdentifier);

	public void unsetSbbContext() {
		this.sbbContext = null;
		this.logger = null;
	}

	public void sbbCreate() throws CreateException {
	}

	public void sbbPostCreate() throws CreateException {
	}

	public void sbbActivate() {
	}

	public void sbbPassivate() {
	}

	public void sbbLoad() {
	}

	public void sbbStore() {
	}

	public void sbbRemove() {
	}

	public void sbbExceptionThrown(Exception exception, Object object, ActivityContextInterface activityContextInterface) {
	}

	public void sbbRolledBack(RolledBackContext rolledBackContext) {
	}
}
