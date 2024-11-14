package mx.edu.uacm.is.slt.as.ws.modelo;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	//COmentario de prueba
	
	public Persona(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento) throws Exception {
		try {
			asignarNombre(nombre);
			asignarPrimerApellido(primerApellido);
			asignarSegundoApellido(segundoApellido);
			asignarFechaNacimiento(fechaNacimiento);
		}catch(Exception e) {
			throw new Exception("Error: "+e);
		}	
	}


	public String getNombre() {
		
		return nombre;
	}


	private void asignarNombre(String nombre) throws Exception {
		Pattern pattern = Pattern.compile("^[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}(\\s[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}){0,10}");
	     Matcher matcher = pattern.matcher(nombre);
	     if(matcher.matches())
	    	 this.nombre = nombre;
	     else
	    	 throw new Exception("El nombre NO es valido ");
	
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	private void asignarPrimerApellido(String primerApellido) throws Exception {
		Pattern pattern = Pattern.compile("^[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}(\\s[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}){0,10}");
	     Matcher matcher = pattern.matcher(primerApellido);
	     if(matcher.matches())
	    	 this.primerApellido = primerApellido;
	     else
	    	 throw new Exception("El primer apellido NO es valido ");
	
	}


	public String getSegundoApellido() {
		return segundoApellido;
	}


	private void asignarSegundoApellido(String segundoApellido) throws Exception {
		Pattern pattern = Pattern.compile("^[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}(\\s[A-Za-zÁÉÍÓÚÑáéíóúñ]{2,50}){0,10}");
	     Matcher matcher = pattern.matcher(primerApellido);
	     if(matcher.matches())
	    	 this.segundoApellido = segundoApellido;
	     else
	    	 throw new Exception("El primer apellido NO es valido ");
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	private void asignarFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
