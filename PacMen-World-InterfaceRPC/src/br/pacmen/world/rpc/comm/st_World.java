package br.pacmen.world.rpc.comm;

//Generated by Netbula JRPCGEN V2.5.6.
//Netbula JavaRPC demo, expires after a fixed date!



import netbula.ORPC.*;

public class st_World implements XDT{
	public short id;
	public String map;
	public short width;
	public short height;

	public XDT Clone() { return new st_World();}

	public XDT [] getArray(int len) { return new st_World[len];}

	public void xdr(XDR xdrs) throws XDRError{
		switch(xdrs.xdr_op){
		case XDR.XDR_ENCODE:
		xdrs.enc_short(id);
		xdrs.enc_String(map);
		xdrs.enc_short(width);
		xdrs.enc_short(height);
		break;

		case XDR.XDR_DECODE:
		id = xdrs.dec_short();
		map = xdrs.dec_String();
		width = xdrs.dec_short();
		height = xdrs.dec_short();
		break;

		}
	}
};
