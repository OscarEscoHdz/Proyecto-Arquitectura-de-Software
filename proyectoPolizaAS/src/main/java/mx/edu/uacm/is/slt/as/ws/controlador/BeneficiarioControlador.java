package mx.edu.uacm.is.slt.as.ws.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Beneficiario registrarBeneficiario(Date fechaNacimiento, UUID clavePoliza, Double porcentaje,
                                              String nombres, String primerApellido, String segundoApellido) {
        
        // Busca la póliza asociada a la clave
        Optional<Poliza> polizaOpt = polizaRepository.findById(clavePoliza);

        if (polizaOpt.isEmpty()) {
            throw new RuntimeException("Poliza no encontrada");
        }

        Poliza poliza = polizaOpt.get();

        // Verificar si ya existe un beneficiario con los mismos datos
        Optional<Beneficiario> beneficiarioExistente = beneficiarioRepository.findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePolizaAndSegundoApellido(
                nombres, primerApellido, fechaNacimiento, clavePoliza, segundoApellido);

        if (beneficiarioExistente.isPresent()) {
            throw new RuntimeException("El beneficiario ya existe");
        }

        // Crear un nuevo beneficiario
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNombre(nombres);
        beneficiario.setPrimerApellido(primerApellido);
        beneficiario.setSegundoApellido(segundoApellido);
        beneficiario.setFechaNacimiento(fechaNacimiento);
        beneficiario.setPoliza(poliza);
        beneficiario.setPorcentaje(porcentaje);

        // Guardar el beneficiario en la base de datos
        return beneficiarioRepository.save(beneficiario);
    }
	
}
