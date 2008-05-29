package org.csapi.fw.fw_access.trust_and_security;

/**
 *	Generated from IDL interface "IpClientAPILevelAuthentication"
 *	@author JacORB IDL compiler V 2.1, 16-Feb-2004
 */


public abstract class IpClientAPILevelAuthenticationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.csapi.fw.fw_access.trust_and_security.IpClientAPILevelAuthenticationOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "challenge", new java.lang.Integer(0));
		m_opsHash.put ( "authenticationSucceeded", new java.lang.Integer(1));
		m_opsHash.put ( "authenticate", new java.lang.Integer(2));
		m_opsHash.put ( "abortAuthentication", new java.lang.Integer(3));
	}
	private String[] ids = {"IDL:org/csapi/fw/fw_access/trust_and_security/IpClientAPILevelAuthentication:1.0","IDL:org/csapi/IpInterface:1.0"};
	public org.csapi.fw.fw_access.trust_and_security.IpClientAPILevelAuthentication _this()
	{
		return org.csapi.fw.fw_access.trust_and_security.IpClientAPILevelAuthenticationHelper.narrow(_this_object());
	}
	public org.csapi.fw.fw_access.trust_and_security.IpClientAPILevelAuthentication _this(org.omg.CORBA.ORB orb)
	{
		return org.csapi.fw.fw_access.trust_and_security.IpClientAPILevelAuthenticationHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // challenge
			{
				byte[] _arg0=org.csapi.TpOctetSetHelper.read(_input);
				_out = handler.createReply();
				org.csapi.TpOctetSetHelper.write(_out,challenge(_arg0));
				break;
			}
			case 1: // authenticationSucceeded
			{
				_out = handler.createReply();
				authenticationSucceeded();
				break;
			}
			case 2: // authenticate
			{
				byte[] _arg0=org.csapi.TpOctetSetHelper.read(_input);
				_out = handler.createReply();
				org.csapi.TpOctetSetHelper.write(_out,authenticate(_arg0));
				break;
			}
			case 3: // abortAuthentication
			{
				_out = handler.createReply();
				abortAuthentication();
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
