package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="beneficiarios")
public class Beneficiario extends Persona{
	@Id
	private Long id;
	
	private float porcentaje;
	
	
	@ManyToOne
	@JoinColumn(name="id_poliza")
	private Poliza poliza;


	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, Long id,
			float porcentaje, Poliza poliza) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.id = id;
		this.porcentaje = porcentaje;
		this.poliza = poliza;
	}
	
	


	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento)
			throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
	}




	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento,
			float porcentaje, Poliza poliza) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.porcentaje = porcentaje;
		this.poliza = poliza;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public float getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}


	public Poliza getPoliza() {
		return poliza;
	}


	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	
	 @Override
	    public boolean equals(Object o) {
	        if (super.equals(o)) {
	            return true;
	        } else if (o instanceof Beneficiario) {
	            Beneficiario otro = (Beneficiario) o;
	            return Objects.equals(this.id, otro.id);
	        } else {
	            return false;
	        }
	    }
	    
	    public int hashCode() {
	    	return Objects.hash(this.id);
	    }
}
