package mx.edu.uacm.is.slt.as.ws.controlador;

import mx.edu.uacm.is.slt.as.ws.modelo.*;
import mx.edu.uacm.is.slt.as.ws.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import excepcion.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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


    // 3. Crear una nueva póliza
    @PostMapping("/cliente/{curp}")
    public Poliza crearPoliza(@PathVariable("curp") UUID curp, @RequestBody Poliza poliza) {
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

   
    
    //Operaciones Get

    //Devuelve todas las polizas
    @GetMapping("/")
    public List<Poliza> obtenerTodasLasPolizas() {
        return polizaRepository.findAll();
    }

    //Devuelve la poliza con la clave dada
    @GetMapping("/{id}")
    public Poliza obtenerPoliza(@PathVariable("id") UUID id) {
        return polizaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
    }
    
    //Devuelve todas las polizas por el tipo de poliza dado
    @GetMapping("/{tipoPoliza}")
    public List<Poliza> obtenerPolizasTipo(@PathVariable("tipoPoliza") TipoPoliza tipoPoliza){
    	return polizaRepository.findByTipoPoliza(tipoPoliza);
    }
    
  
    //Devuelve las polizas por el beneficiario dado
    @GetMapping("/b/{nombres}/{primer_apellido}/{segundo_apellido}")
    public List<Poliza> obtenerPolizasPorBeneficiario(@PathVariable("nombres") String nombres,
                                                      @PathVariable("primer_apellido") String primerApellido,
                                                      @PathVariable("segundo_apellido") String segundoApellido) {
        return beneficiarioRepository.findPolizasByBeneficiarios(nombres, primerApellido, segundoApellido);
    }
    
    //Devuelve el registro del beneficiario de la póliza solicitada de existir. En otro caso devuelve null.
    @GetMapping("/beneficiario/{fecha_nacimiento}/{clave_poliza}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public Beneficiario obtenerBeneficiario(@PathVariable("fecha_nacimiento") Date fechaNacimiento,
                                            @PathVariable("clave_poliza") UUID clavePoliza,
                                            @PathVariable("nombres") String nombres,
                                            @PathVariable("primer_apellido") String primerApellido,
                                            @PathVariable("segundo_apellido") String segundoApellido) {
    	Optional<Beneficiario> beneficiario = null;
    	
    	if (segundoApellido != null && !segundoApellido.isEmpty()) {
    		beneficiario = beneficiarioRepository.findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePolizaAndSegundoApellido(nombres, primerApellido, fechaNacimiento, clavePoliza, segundoApellido);

        } else {
        	beneficiario = beneficiarioRepository.findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePoliza(
                    nombres, primerApellido, fechaNacimiento, clavePoliza);
        }
    	
    	return beneficiario.get();
       
    }
    
    @GetMapping("/cliente/{curp}")
    public List<Poliza> obtenerPolizasPorCurp(@PathVariable("curp") UUID curp) {
        Cliente cliente = clienteRepository.findByCurp(curp)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return polizaRepository.findByCliente(cliente);
    }

}