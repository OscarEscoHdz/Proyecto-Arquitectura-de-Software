package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Persona{
	private String direccion;
	@Id
	private String curp;
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp) throws Exception  {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.direccion = direccion;
		this.curp = curp;
		
	}
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp, Poliza poliza) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.direccion = direccion;
		this.curp = curp;
		
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}




}
