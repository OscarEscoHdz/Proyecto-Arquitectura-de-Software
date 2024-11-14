package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends Persona{
	private String direccion;
	@Id
	private String curp;
	
	@OneToOne
	private Poliza poliza;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Poliza polizas;
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp) throws Exception  {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		asignarDireccion(direccion);
		asignarCurp(curp);
		
	}
	
//modificaciones
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp, Poliza poliza, Poliza polizas) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.direccion = direccion;
		this.curp = curp;
		this.poliza = poliza;
		this.polizas = polizas;
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
