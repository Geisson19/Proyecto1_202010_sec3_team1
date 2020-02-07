package model;


import java.util.Date;

public class Comparendo {
	public int OBJECTID;
	public Date FECHA_HORA;
	public String DES_INFRAC;
	public String MEDIO_DETE;
	public String CLASE_VEHI;
	public String TIPO_SERVI;
	public String INFRACCION;
	public String LOCALIDAD;

	public double latitud;
	public double longitud;
	
	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + OBJECTID + ", FECHA_HORA=" + FECHA_HORA + ", DES_INFRAC=" + DES_INFRAC
				+ ", MEDIO_DETE=" + MEDIO_DETE + ", CLASE_VEHI=" + CLASE_VEHI + ", TIPO_SERVI=" + TIPO_SERVI
				+ ", INFRACCION=" + INFRACCION + ", LOCALIDAD=" + LOCALIDAD + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	
}
