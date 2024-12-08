package mx.edu.uacm.is.slt.as.ws.services;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import excepcion.ResourceNotFoundException;
import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	//lo agrego Oscar
	public void actualizarCliente(UUID curp, String direccion, Date fechaNacimiento, String nombres, String primerApellido, String segundoApellido) {
	    Cliente cliente = clienteRepository.findById(curp)
	        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el CURP: " + curp));
	    
	    cliente.setDireccion(direccion);
	    cliente.asignarFechaNacimiento(fechaNacimiento);
	    cliente.setNombre(nombres);
	    cliente.setPrimerApellido(primerApellido);
	    cliente.setSegundoApellido(segundoApellido);

	    clienteRepository.save(cliente);
	}


}
