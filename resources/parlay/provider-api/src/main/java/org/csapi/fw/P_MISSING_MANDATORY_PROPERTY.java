package org.csapi.fw;

/**
 *	Generated from IDL definition of exception "P_MISSING_MANDATORY_PROPERTY"
 *	@author JacORB IDL compiler 
 */

public final class P_MISSING_MANDATORY_PROPERTY
	extends org.omg.CORBA.UserException
{
	public P_MISSING_MANDATORY_PROPERTY()
	{
		super(org.csapi.fw.P_MISSING_MANDATORY_PROPERTYHelper.id());
	}

	public java.lang.String ExtraInformation;
	public P_MISSING_MANDATORY_PROPERTY(java.lang.String _reason,java.lang.String ExtraInformation)
	{
		super(org.csapi.fw.P_MISSING_MANDATORY_PROPERTYHelper.id()+""+_reason);
		this.ExtraInformation = ExtraInformation;
	}
	public P_MISSING_MANDATORY_PROPERTY(java.lang.String ExtraInformation)
	{
		this.ExtraInformation = ExtraInformation;
	}
}
