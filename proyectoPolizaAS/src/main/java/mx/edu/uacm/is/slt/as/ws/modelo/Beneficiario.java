package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

public class Beneficiario extends Persona{
	private float porcentaje;

	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento)
			throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		//TODO Auto-generated constructor stub
	}
	
	public float getPorcentaje() {
		return porcentaje;
	}

}
