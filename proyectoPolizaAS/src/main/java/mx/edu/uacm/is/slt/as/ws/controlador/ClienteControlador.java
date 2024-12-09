package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;

@RestController
public class ClienteControlador {
	
	@PostMapping("/{curp}/{direccion}/{fecha_nacimiento}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public Cliente crearCliente(
            @PathVariable("curp") String curp,
            @PathVariable("direccion") String direccion,
            @PathVariable("fecha_nacimiento") String fechaNacimiento,
            @PathVariable("nombres") String nombres,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable("segundo_apellido") String segundoApellido) throws Exception {

        // Formato esperado para la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = dateFormat.parse(fechaNacimiento);

        // Crear el cliente
        Cliente cliente = new Cliente(nombres, primerApellido, segundoApellido, new java.sql.Date(fecha.getTime()), direccion, curp);
        
        // Aquí podrías guardar el cliente en la base de datos si fuera necesario
        
        return cliente;
    }
}
