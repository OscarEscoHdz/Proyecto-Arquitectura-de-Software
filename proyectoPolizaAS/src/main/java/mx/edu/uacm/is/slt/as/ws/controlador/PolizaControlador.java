package mx.edu.uacm.is.slt.as.ws.controlador;

import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.modelo.TipoPoliza;
import mx.edu.uacm.is.slt.as.ws.services.BeneficiarioService;
import mx.edu.uacm.is.slt.as.ws.services.ClienteService;
import mx.edu.uacm.is.slt.as.ws.services.PolizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/polizas")
public class PolizaControlador {

	@Autowired
	private final PolizaService polizaService;
	@Autowired
	private final BeneficiarioService beneficiarioService;
	@Autowired
	private final ClienteService clienteService;

    // Constructor para inyección de dependencias (services)
    public PolizaControlador(PolizaService polizaService, ClienteService clienteService, BeneficiarioService beneficiarioService) {
        this.polizaService = polizaService;
        this.clienteService = clienteService;
        this.beneficiarioService = beneficiarioService;
    }


    // 3. Crear una nueva póliza
    @PostMapping("/cliente/{curp}")
    public Poliza crearPoliza(@PathVariable("curp") UUID curp, @RequestBody Poliza poliza) {
        return polizaService.crearPoliza(curp,poliza);
    }

    // 4. Actualizar una póliza
    @PutMapping("/{id}")
    public Poliza actualizarPoliza(@PathVariable("id") UUID id, @RequestBody Poliza polizaActualizada) {
        return polizaService.actualizarPoliza(id,polizaActualizada);
    }

    // 5. Eliminar una póliza
    @DeleteMapping("/{id}")
    public String eliminarPoliza(@PathVariable("id") UUID id) {
        polizaService.eliminarPoliza(id);
        return "Poliza eliminada con éxito";
    }
    
    //Operaciones Get

    //Devuelve todas las polizas
    @GetMapping("/")
    public List<Poliza> obtenerTodasLasPolizas() {
        return polizaService.obtenerTodasLasPolizas();
    }

    //Devuelve la poliza con la clave dada
    @GetMapping("/{id}")
    public Poliza obtenerPoliza(@PathVariable("id") UUID id) {
       return polizaService.obtenerPoliza(id);
    }
    
    //Devuelve todas las polizas por el tipo de poliza dado
    @GetMapping("/{tipoPoliza}")
    public List<Poliza> obtenerPolizasTipo(@PathVariable("tipoPoliza") TipoPoliza tipoPoliza){
    	return polizaService.obtenerPolizasTipo(tipoPoliza);
    }
    
    @GetMapping("/b/{nombres}/{primer_apellido}/{segundo_apellido}")
    public List<Poliza> obtenerPolizasPorBeneficiario(@PathVariable("nombres") String nombres,
                                                      @PathVariable("primer_apellido") String primerApellido,
                                                      @PathVariable("segundo_apellido") String segundoApellido) {
        return beneficiarioService.obtenerPolizasPorBeneficiario(nombres, primerApellido, segundoApellido);
    }
    
    //Devuelve el registro del beneficiario de la póliza solicitada de existir. En otro caso devuelve null.
    @GetMapping("/beneficiario/{fecha_nacimiento}/{clave_poliza}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public Beneficiario obtenerBeneficiario(@PathVariable("fecha_nacimiento") Date fechaNacimiento,
                                            @PathVariable("clave_poliza") UUID clavePoliza,
                                            @PathVariable("nombres") String nombres,
                                            @PathVariable("primer_apellido") String primerApellido,
                                            @PathVariable("segundo_apellido") String segundoApellido) {
    	
    	return beneficiarioService.obtenerBeneficiario(fechaNacimiento, clavePoliza, nombres, primerApellido, segundoApellido);
       
    }
    
    @GetMapping("/cliente/{curp}")
    public List<Poliza> obtenerPolizasPorCurp(@PathVariable("curp") UUID curp) {
        return polizaService.obtenerPolizasPorCurp(curp);
    }

}