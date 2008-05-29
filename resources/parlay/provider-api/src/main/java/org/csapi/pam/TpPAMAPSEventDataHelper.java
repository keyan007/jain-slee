package org.csapi.pam;


/**
 *	Generated from IDL definition of struct "TpPAMAPSEventData"
 *	@author JacORB IDL compiler 
 */

public final class TpPAMAPSEventDataHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(org.csapi.pam.TpPAMAPSEventDataHelper.id(),"TpPAMAPSEventData",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("AgentName", org.csapi.pam.TpPAMFQNameListHelper.type(), null),new org.omg.CORBA.StructMember("AgentType", org.csapi.TpStringListHelper.type(), null),new org.omg.CORBA.StructMember("AttributeNames", org.csapi.TpStringListHelper.type(), null),new org.omg.CORBA.StructMember("ReportingPeriod", org.csapi.pam.TpPAMTimeIntervalHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.csapi.pam.TpPAMAPSEventData s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.csapi.pam.TpPAMAPSEventData extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:org/csapi/pam/TpPAMAPSEventData:1.0";
	}
	public static org.csapi.pam.TpPAMAPSEventData read (final org.omg.CORBA.portable.InputStream in)
	{
		org.csapi.pam.TpPAMAPSEventData result = new org.csapi.pam.TpPAMAPSEventData();
		result.AgentName = org.csapi.pam.TpPAMFQNameListHelper.read(in);
		result.AgentType = org.csapi.TpStringListHelper.read(in);
		result.AttributeNames = org.csapi.TpStringListHelper.read(in);
		result.ReportingPeriod=in.read_longlong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.csapi.pam.TpPAMAPSEventData s)
	{
		org.csapi.pam.TpPAMFQNameListHelper.write(out,s.AgentName);
		org.csapi.TpStringListHelper.write(out,s.AgentType);
		org.csapi.TpStringListHelper.write(out,s.AttributeNames);
		out.write_longlong(s.ReportingPeriod);
	}
}
