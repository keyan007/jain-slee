package org.csapi.cc.gccs;


/**
 *	Generated from IDL definition of struct "TpCallReport"
 *	@author JacORB IDL compiler 
 */

public final class TpCallReportHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(org.csapi.cc.gccs.TpCallReportHelper.id(),"TpCallReport",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("MonitorMode", org.csapi.cc.TpCallMonitorModeHelper.type(), null),new org.omg.CORBA.StructMember("CallEventTime", org.csapi.TpDateAndTimeHelper.type(), null),new org.omg.CORBA.StructMember("CallReportType", org.csapi.cc.gccs.TpCallReportTypeHelper.type(), null),new org.omg.CORBA.StructMember("AdditionalReportInfo", org.csapi.cc.gccs.TpCallAdditionalReportInfoHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final org.csapi.cc.gccs.TpCallReport s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static org.csapi.cc.gccs.TpCallReport extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:org/csapi/cc/gccs/TpCallReport:1.0";
	}
	public static org.csapi.cc.gccs.TpCallReport read (final org.omg.CORBA.portable.InputStream in)
	{
		org.csapi.cc.gccs.TpCallReport result = new org.csapi.cc.gccs.TpCallReport();
		result.MonitorMode=org.csapi.cc.TpCallMonitorModeHelper.read(in);
		result.CallEventTime=in.read_string();
		result.CallReportType=org.csapi.cc.gccs.TpCallReportTypeHelper.read(in);
		result.AdditionalReportInfo=org.csapi.cc.gccs.TpCallAdditionalReportInfoHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final org.csapi.cc.gccs.TpCallReport s)
	{
		org.csapi.cc.TpCallMonitorModeHelper.write(out,s.MonitorMode);
		out.write_string(s.CallEventTime);
		org.csapi.cc.gccs.TpCallReportTypeHelper.write(out,s.CallReportType);
		org.csapi.cc.gccs.TpCallAdditionalReportInfoHelper.write(out,s.AdditionalReportInfo);
	}
}
