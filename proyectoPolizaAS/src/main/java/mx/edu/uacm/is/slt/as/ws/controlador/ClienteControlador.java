package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;
import mx.edu.uacm.is.slt.as.ws.repository.ClienteRepository;

@RestController
public class ClienteControlador {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	@PostMapping("/{curp}/{direccion}/{fecha_nacimiento}/{nombre}/{primer_apellido}/{segundo_apellido}")
    public Cliente crearCliente(
            @PathVariable("curp") String curp,
            @PathVariable("direccion") String direccion,
            @PathVariable("fecha_nacimiento") String fechaNacimiento,
            @PathVariable("nombre") String nombre,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable("segundo_apellido") String segundoApellido) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date fecha = null; // Declarar la variable fuera del bloque try-catch

	    try {
	        // Intentar parsear la fecha
	        fecha = dateFormat.parse(fechaNacimiento);
	    } catch (ParseException e) {
	        // Manejar el error si el formato de la fecha es incorrecto
	        throw new IllegalArgumentException("Formato de fecha inválido: " + fechaNacimiento, e);
	    }
        	
        // Crear el cliente
        Cliente cliente = new Cliente(nombre, primerApellido, segundoApellido, fecha, direccion, curp);
        
        cliente = clienteRepository.save(cliente);
        
        return cliente;
    }
}
