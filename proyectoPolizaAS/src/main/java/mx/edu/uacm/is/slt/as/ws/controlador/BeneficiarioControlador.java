package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.repository.BeneficiarioRepository;
import mx.edu.uacm.is.slt.as.ws.repository.PolizaRepository;

public class BeneficiarioControlador {
	@Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private PolizaRepository polizaRepository;

    @PostMapping("/beneficiario/{fecha_nacimiento}/{clave_poliza}/{porcentaje}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public ResponseEntity<Beneficiario> registrarBeneficiario(
            @PathVariable("fecha_nacimiento") Date fechaNacimiento,
            @PathVariable("clave_poliza") UUID clavePoliza,
            @PathVariable("porcentaje") Double porcentaje,
            @PathVariable("nombres") String nombres,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable("segundo_apellido") String segundoApellido) {

        try {
            // Buscar la póliza asociada a la clave
            Optional<Poliza> polizaOpt = polizaRepository.findById(clavePoliza);

            if (polizaOpt.isEmpty()) {
                throw new RuntimeException("Poliza no encontrada");
            }

            Poliza poliza = polizaOpt.get();

            // Verificar si ya existe un beneficiario con los mismos datos
            Optional<Beneficiario> beneficiarioExistente = beneficiarioRepository
                .findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePolizaAndSegundoApellido(
                        nombres, primerApellido, fechaNacimiento, clavePoliza, segundoApellido);

            if (beneficiarioExistente.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Beneficiario ya existe
            }

            // Crear un nuevo beneficiario
            Beneficiario beneficiario = new Beneficiario();
            beneficiario.setNombre(nombres);
            beneficiario.setPrimerApellido(primerApellido);
            beneficiario.setSegundoApellido(segundoApellido);
            beneficiario.setFechaNacimiento(fechaNacimiento);
            beneficiario.setPoliza(poliza);
            beneficiario.setPorcentaje(porcentaje.floatValue()); // Convertir de Double a float

            // Guardar el beneficiario en la base de datos
            Beneficiario nuevoBeneficiario = beneficiarioRepository.save(beneficiario);

            return new ResponseEntity<>(nuevoBeneficiario, HttpStatus.CREATED); // Retornar el beneficiario creado

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Manejar cualquier error
        }
    }
	
}
