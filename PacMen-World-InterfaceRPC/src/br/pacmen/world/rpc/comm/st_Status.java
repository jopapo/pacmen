package br.pacmen.world.rpc.comm;

//Generated by Netbula JRPCGEN V2.5.6.
//Netbula JavaRPC demo, expires after a fixed date!



import netbula.ORPC.*;

public class st_Status implements XDT{
	public short id;
	public String msg;

	public XDT Clone() { return new st_Status();}

	public XDT [] getArray(int len) { return new st_Status[len];}

	public void xdr(XDR xdrs) throws XDRError{
		switch(xdrs.xdr_op){
		case XDR.XDR_ENCODE:
		xdrs.enc_short(id);
		xdrs.enc_String(msg);
		break;

		case XDR.XDR_DECODE:
		id = xdrs.dec_short();
		msg = xdrs.dec_String();
		break;

		}
	}
};
