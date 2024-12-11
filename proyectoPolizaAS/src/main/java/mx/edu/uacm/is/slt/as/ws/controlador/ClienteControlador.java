package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;
import mx.edu.uacm.is.slt.as.ws.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteControlador {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/{curp}/{direccion}/{fecha_nacimiento}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public ResponseEntity<?> registrarCliente(
            @PathVariable("curp") String curp,
            @PathVariable("direccion") String direccion,
            @PathVariable("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento,
            @PathVariable("nombres") String nombres,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable(value = "segundo_apellido", required = false) String segundoApellido) {

        try {
            if (segundoApellido == null || segundoApellido.isEmpty()) {
                segundoApellido = "N/A";
            }

            Cliente cliente = new Cliente(nombres, primerApellido, segundoApellido, fechaNacimiento, direccion, curp);

            clienteRepository.save(cliente);

            return ResponseEntity.ok("Cliente registrado con éxito: " + cliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

}
