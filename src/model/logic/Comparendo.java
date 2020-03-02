package model.logic;


import java.util.Date;

public class Comparendo implements Comparable<Comparendo> {
	private int objectId;
	private Date fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;

	public Comparendo(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}



	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}



	public int getObjectId() {
		return objectId;
	}

	public Date getFecha_hora() {
		return fecha_hora;
	}

	public String getDes_infrac() {
		return des_infrac;
	}

	public String getMedio_dete() {
		return medio_dete;
	}

	public String getClase_vehi() {
		return clase_vehi;
	}

	public String getTipo_servi() {
		return tipo_servi;
	}

	public String getInfraccion() {
		return infraccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	@Override
	public int compareTo(Comparendo comparendo) {
		return 0;
	}
}