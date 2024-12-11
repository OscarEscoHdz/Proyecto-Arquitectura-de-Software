package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/polizas/c/{nombre}/{primer_apellido}/{segundo_apellido}")
	public List<Cliente> obtenerClientesPorNombre(@PathVariable String nombre, 
	                                              @PathVariable String primer_apellido, 
	                                              @PathVariable(required = false) String segundo_apellido) {
	    // Buscar clientes por nombre y primer apellido
	    List<Cliente> clientes;
	    if (segundo_apellido != null) {
	        // Si se proporciona segundo apellido, buscar por todos los apellidos
	        clientes = clienteRepository.findByNombreAndPrimerApellidoAndSegundoApellido(nombre, primer_apellido, segundo_apellido);
	    } else {
	        // Si no se proporciona segundo apellido, buscar solo por nombre y primer apellido
	        clientes = clienteRepository.findByNombreAndPrimerApellidoAndSegundoApellido(nombre, primer_apellido, null);
	        System.out.println("No se encontraron los cliente con sólo 1 apellido");
	    }
	    
	    // Si no se encuentran clientes, lanzar excepción
	    if (clientes.isEmpty()) {
	        System.out.println("No se encontraron clientes con los datos proporcionados.");
	    } else {
	    	 // Si se encuentran clientes, imprimir mensaje de éxito
	        System.out.println("Se encontraron " + clientes.size() + " clientes con los datos proporcionados.");
	    }
	    
	    return clientes;
	}
	
	
}
