package mx.edu.uacm.is.slt.as.ws.controlador;

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
	
	@PostMapping("/{curp}/{direccion}/{fecha_nacimiento}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public Cliente crearCliente(
            @PathVariable("curp") String curp,
            @PathVariable("direccion") String direccion,
            @PathVariable("fecha_nacimiento") String fechaNacimiento,
            @PathVariable("nombres") String nombres,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable("segundo_apellido") String segundoApellido) throws Exception {
		
		System.out.println("Se mete a esta clase");
		
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse(fechaNacimiento);
        	
        System.out.println("Tipo Recibido");
        
        // Crear el cliente
        Cliente cliente = new Cliente(nombres, primerApellido, segundoApellido, new java.util.Date(fechaNacimiento), direccion, curp);
        
        cliente = clienteRepository.save(cliente);
        
        return cliente;
    }
}
