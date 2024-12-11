package mx.edu.uacm.is.slt.as.ws.modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente extends Persona{
	private String direccion;
	@Id
	private String curp;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Poliza> polizas;
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String direccion,
			String curp) throws Exception  {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.direccion = direccion;
		this.curp = curp;
	}
	
	public Cliente() {
		
	}
	
	public Cliente(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento)
			throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
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

