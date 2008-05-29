package org.csapi.cc.mpccs;

import org.omg.PortableServer.POA;

/**
 *	Generated from IDL interface "IpCallLeg"
 *	@author JacORB IDL compiler V 2.1, 16-Feb-2004
 */

public class IpCallLegPOATie
	extends IpCallLegPOA
{
	private IpCallLegOperations _delegate;

	private POA _poa;
	public IpCallLegPOATie(IpCallLegOperations delegate)
	{
		_delegate = delegate;
	}
	public IpCallLegPOATie(IpCallLegOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.csapi.cc.mpccs.IpCallLeg _this()
	{
		return org.csapi.cc.mpccs.IpCallLegHelper.narrow(_this_object());
	}
	public org.csapi.cc.mpccs.IpCallLeg _this(org.omg.CORBA.ORB orb)
	{
		return org.csapi.cc.mpccs.IpCallLegHelper.narrow(_this_object(orb));
	}
	public IpCallLegOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(IpCallLegOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		else
		{
			return super._default_POA();
		}
	}
	public void setCallback(org.csapi.IpInterface appInterface) throws org.csapi.P_INVALID_INTERFACE_TYPE,org.csapi.TpCommonExceptions
	{
_delegate.setCallback(appInterface);
	}

	public void deassign(int callLegSessionID) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.deassign(callLegSessionID);
	}

	public void getInfoReq(int callLegSessionID, int callLegInfoRequested) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.getInfoReq(callLegSessionID,callLegInfoRequested);
	}

	public void eventReportReq(int callLegSessionID, org.csapi.cc.TpCallEventRequest[] eventsRequested) throws org.csapi.P_INVALID_EVENT_TYPE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID,org.csapi.P_INVALID_CRITERIA
	{
_delegate.eventReportReq(callLegSessionID,eventsRequested);
	}

	public void attachMediaReq(int callLegSessionID) throws org.csapi.P_INVALID_NETWORK_STATE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.attachMediaReq(callLegSessionID);
	}

	public org.csapi.cc.mpccs.TpMultiPartyCallIdentifier getCall(int callLegSessionID) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
		return _delegate.getCall(callLegSessionID);
	}

	public org.csapi.TpAddress getCurrentDestinationAddress(int callLegSessionID) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
		return _delegate.getCurrentDestinationAddress(callLegSessionID);
	}

	public void continueProcessing(int callLegSessionID) throws org.csapi.P_INVALID_NETWORK_STATE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.continueProcessing(callLegSessionID);
	}

	public void routeReq(int callLegSessionID, org.csapi.TpAddress targetAddress, org.csapi.TpAddress originatingAddress, org.csapi.cc.TpCallAppInfo[] appInfo, org.csapi.cc.TpCallLegConnectionProperties connectionProperties) throws org.csapi.P_INVALID_NETWORK_STATE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_ADDRESS,org.csapi.P_INVALID_SESSION_ID,org.csapi.P_UNSUPPORTED_ADDRESS_PLAN
	{
_delegate.routeReq(callLegSessionID,targetAddress,originatingAddress,appInfo,connectionProperties);
	}

	public void release(int callLegSessionID, org.csapi.cc.TpReleaseCause cause) throws org.csapi.P_INVALID_NETWORK_STATE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.release(callLegSessionID,cause);
	}

	public void detachMediaReq(int callLegSessionID) throws org.csapi.P_INVALID_NETWORK_STATE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.detachMediaReq(callLegSessionID);
	}

	public void setCallbackWithSessionID(org.csapi.IpInterface appInterface, int sessionID) throws org.csapi.P_INVALID_INTERFACE_TYPE,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.setCallbackWithSessionID(appInterface,sessionID);
	}

	public void setAdviceOfCharge(int callLegSessionID, org.csapi.TpAoCInfo aOCInfo, int tariffSwitch) throws org.csapi.P_INVALID_AMOUNT,org.csapi.P_INVALID_CURRENCY,org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.setAdviceOfCharge(callLegSessionID,aOCInfo,tariffSwitch);
	}

	public void setChargePlan(int callLegSessionID, org.csapi.cc.TpCallChargePlan callChargePlan) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.setChargePlan(callLegSessionID,callChargePlan);
	}

	public void superviseReq(int callLegSessionID, int time, int treatment) throws org.csapi.TpCommonExceptions,org.csapi.P_INVALID_SESSION_ID
	{
_delegate.superviseReq(callLegSessionID,time,treatment);
	}

}
