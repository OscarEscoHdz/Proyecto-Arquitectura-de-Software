package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Beneficiario extends Persona{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//esta clase se actualiza
	private float porcentaje;
	//comentario de prueba
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="clave_poliza", nullable=false)
	private Poliza poliza;

	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento)
			throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		//TODO Auto-generated constructor stub
	}

	
	public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento, Long id,
			float porcentaje, Cliente cliente, Poliza poliza) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.id = id;
		this.porcentaje = porcentaje;
		this.cliente = cliente;
		this.poliza = poliza;
	}



	public float getPorcentaje() {
		return porcentaje;
	}

}
