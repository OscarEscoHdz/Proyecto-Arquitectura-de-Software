package mx.edu.uacm.is.slt.as.ws.controlador;

import mx.edu.uacm.is.slt.as.ws.modelo.*;
import repository.BeneficiarioRepository;
import repository.ClienteRepository;
import repository.PolizaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import excepcion.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/polizas")
public class PolizaControlador {

	@Autowired
    private final PolizaRepository polizaRepository;
	@Autowired
    private final ClienteRepository clienteRepository;
	@Autowired
    private final BeneficiarioRepository beneficiarioRepository;

    // Constructor para inyección de dependencias (repositorios)
    public PolizaControlador(PolizaRepository polizaRepository, ClienteRepository clienteRepository, BeneficiarioRepository beneficiarioRepository) {
        this.polizaRepository = polizaRepository;
        this.clienteRepository = clienteRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    // 1. Obtener todas las pólizas
    @GetMapping("/")
    public List<Poliza> obtenerTodasLasPolizas() {
        return polizaRepository.findAll();
    }

    // 2. Obtener una póliza por ID
    @GetMapping("/{id}")
    public Poliza obtenerPoliza(@PathVariable("id") UUID id) {
        return polizaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
    }

    // 3. Crear una nueva póliza
    @PostMapping("/cliente/{curp}")
    public Poliza crearPoliza(@PathVariable("curp") String curp, @RequestBody Poliza poliza) {
        Cliente cliente = clienteRepository.findByCurp(curp)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        poliza.setCliente(cliente);
        return polizaRepository.save(poliza);
    }

    // 4. Actualizar una póliza
    @PutMapping("/{id}")
    public Poliza actualizarPoliza(@PathVariable("id") UUID id, @RequestBody Poliza polizaActualizada) {
        Poliza poliza = polizaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
        poliza.setTipoPoliza(polizaActualizada.getTipoSeguro());
        poliza.setMontoAsegurado(polizaActualizada.getMontoAsegurado());
        poliza.setDescripcion(polizaActualizada.getDescripcion());
        return polizaRepository.save(poliza);
    }

    // 5. Eliminar una póliza
    @DeleteMapping("/{id}")
    public String eliminarPoliza(@PathVariable("id") UUID id) {
        Poliza poliza = polizaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
        polizaRepository.delete(poliza);
        return "Poliza eliminada con éxito";
    }

    // 6. Obtener todas las pólizas de un cliente por CURP
    @GetMapping("/cliente/{curp}")
    public List<Poliza> obtenerPolizasPorCurp(@PathVariable("curp") String curp) {
        Cliente cliente = clienteRepository.findByCurp(curp)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return polizaRepository.findByCliente(cliente);
    }

    // 7. Obtener todas las pólizas de un beneficiario
    @GetMapping("/b/{nombres}/{primer_apellido}/{segundo_apellido}")
    public List<Poliza> obtenerPolizasPorBeneficiario(@PathVariable("nombres") String nombres,
                                                      @PathVariable("primer_apellido") String primerApellido,
                                                      @PathVariable("segundo_apellido") String segundoApellido) {
        return polizaRepository.findPolizasByBeneficiario(nombres, primerApellido, segundoApellido);
    }
    
 // 8. Obtener el beneficiario de una póliza
    @GetMapping("/beneficiario/{fecha_nacimiento}/{clave_poliza}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public Beneficiario obtenerBeneficiario(@PathVariable("fecha_nacimiento") Date fechaNacimiento,
                                            @PathVariable("clave_poliza") UUID clavePoliza,
                                            @PathVariable("nombres") String nombres,
                                            @PathVariable("primer_apellido") String primerApellido,
                                            @PathVariable("segundo_apellido") String segundoApellido) {
        Poliza poliza = polizaRepository.findById(clavePoliza)
            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
        return beneficiarioRepository.findBeneficiarioByPolizaAndDatos(poliza, nombres, primerApellido, segundoApellido, fechaNacimiento)
            .orElseThrow(() -> new ResourceNotFoundException("Beneficiario no encontrado"));
    }
}