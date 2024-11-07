package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

public class Cliente extends Persona{
	private String direccion;
	private String curp;//Este sera el identificador del cliente
	
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp) throws Exception  {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		asignarDireccion(direccion);
		asignarCurp(curp);
		
	}


	public String getDireccion() {
		return direccion;
	}


	private void asignarDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCurp() {
		return curp;
	}


	private void asignarCurp(String curp) {
		this.curp = curp;
	}	
}
