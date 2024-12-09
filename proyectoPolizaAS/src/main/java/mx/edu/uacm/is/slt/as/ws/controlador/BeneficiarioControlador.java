package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.repository.BeneficiarioRepository;
import mx.edu.uacm.is.slt.as.ws.repository.PolizaRepository;

@RestController
public class BeneficiarioControlador {
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private PolizaRepository polizaRepository;

    @PostMapping("/beneficiario/{fecha_nacimiento}/{clave_poliza}/{porcentaje}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public ResponseEntity<Beneficiario> registrarBeneficiario(
            @PathVariable("fecha_nacimiento") String fechaNacimientoStr, // Cambiado de Date a String
            @PathVariable("clave_poliza") UUID clavePoliza,
            @PathVariable("porcentaje") Double porcentaje,
            @PathVariable("nombres") String nombres,
            @PathVariable("primer_apellido") String primerApellido,
            @PathVariable(value = "segundo_apellido", required = false) String segundoApellido) {

        try {
            // Formato esperado para la fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);

            // Convertir de java.util.Date a java.sql.Date
            java.util.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());

            // Verificar si la póliza existe
            if (!polizaRepository.existsById(clavePoliza)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Poliza no encontrada
            }

            // Verificar si ya existe un beneficiario con los mismos datos
            Optional<Beneficiario> beneficiarioExistente = beneficiarioRepository
                    .findByNombreAndPrimerApellidoAndFechaNacimientoAndPolizaAndSegundoApellido(
                            nombres, primerApellido, sqlFechaNacimiento, clavePoliza, segundoApellido);

            if (beneficiarioExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null); // Beneficiario ya existe
            }

            // Crear un nuevo beneficiario
            Beneficiario beneficiario = new Beneficiario();
            beneficiario.setNombre(nombres);
            beneficiario.setPrimerApellido(primerApellido);
            beneficiario.setSegundoApellido(segundoApellido);
            beneficiario.setFechaNacimiento(sqlFechaNacimiento); // Usar java.sql.Date
            beneficiario.setPoliza(clavePoliza); // Ajustar para usar el UUID directamente
            beneficiario.setPorcentaje(porcentaje.floatValue()); // Convertir de Double a float

            // Guardar el beneficiario en la base de datos
            Beneficiario nuevoBeneficiario = beneficiarioRepository.save(beneficiario);

            return new ResponseEntity<>(nuevoBeneficiario, HttpStatus.CREATED); // Retornar el beneficiario creado

        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Error de formato de fecha
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Manejar cualquier otro error
        }
    }

}
