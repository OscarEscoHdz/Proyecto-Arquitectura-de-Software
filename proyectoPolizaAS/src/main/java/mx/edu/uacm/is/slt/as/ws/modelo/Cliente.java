package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Persona{
	private String direccion;
	@Id
	private UUID curp;
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			UUID curp) throws Exception  {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.direccion = direccion;
		this.curp = curp;
		
	}
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			UUID curp, Poliza poliza) throws Exception {
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

	public UUID getCurp() {
		return curp;
	}

	public void setCurp(UUID curp) {
		this.curp = curp;
	}

	 @Override
	    public boolean equals(Object o) {
	        if (super.equals(o)) {
	            return true;
	        } else if (o instanceof Cliente) {
	            Cliente otro = (Cliente) o;
	            return Objects.equals(this.curp, otro.curp);
	        } else {
	            return false;
	        }
	    }
	    
	    public int hashCode() {
	    	return Objects.hash(this.curp);
	    }

		
}

