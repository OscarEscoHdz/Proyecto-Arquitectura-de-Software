package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

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

	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento)
			throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		//TODO Auto-generated constructor stub
	}

	




	public float getPorcentaje() {
		return porcentaje;
	}

}
