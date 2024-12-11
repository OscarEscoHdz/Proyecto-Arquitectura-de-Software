
package mx.edu.uacm.is.slt.as.ws.controlador;

import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.modelo.TipoPoliza;
import mx.edu.uacm.is.slt.as.ws.repository.ClienteRepository;
import mx.edu.uacm.is.slt.as.ws.repository.PolizaRepository;
import mx.edu.uacm.is.slt.as.ws.services.BeneficiarioService;
import mx.edu.uacm.is.slt.as.ws.services.ClienteService;
import mx.edu.uacm.is.slt.as.ws.services.PolizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/poliza") // Agregar /api al inicio de la ruta
public class PolizaControlador {

    @Autowired
    private final PolizaService polizaService;
    @Autowired
    private final BeneficiarioService beneficiarioService;
    @Autowired
    private final ClienteService clienteService;
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public PolizaControlador(PolizaService polizaService, ClienteService clienteService, BeneficiarioService beneficiarioService) {
        this.polizaService = polizaService;
        this.clienteService = clienteService;
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping("/poliza/{clave}/{tipo}/{monto}/{descripcion}/{curp_cliente}")
    public Poliza registrarPoliza(
            @PathVariable("clave") UUID clave,
            @PathVariable("tipo") int tipo,
            @PathVariable("monto") Double monto,
            @PathVariable("descripcion") String descripcion,
            @PathVariable("curp_cliente") String curpCliente) {

        System.out.println("Iniciando registro de póliza con los siguientes datos:");
        System.out.println("Clave: " + clave);
        System.out.println("Tipo (ordinal): " + tipo);
        System.out.println("Monto: " + monto);
        System.out.println("Descripción: " + descripcion);
        System.out.println("CURP Cliente: " + curpCliente);

        if (polizaRepository.existsById(clave)) {
            throw new RuntimeException("Ya existe una póliza con la clave: " + clave);
        }

        TipoPoliza tipoPoliza;
        try {
            tipoPoliza = TipoPoliza.values()[tipo]; // Convertir ordinal a Enum
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Tipo de póliza inválido: " + tipo);
        }

        Cliente cliente = clienteRepository.findById(curpCliente).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado con CURP: " + curpCliente));

        Poliza nuevaPoliza = new Poliza(clave, tipoPoliza, monto, descripcion, cliente);

        return polizaRepository.save(nuevaPoliza);
    }

    //Devuelve todas las polizas
    @GetMapping("/")
    public List<Poliza> obtenerTodasLasPolizas() {
        return polizaService.obtenerTodasLasPolizas();
    }
    
    @GetMapping("/{id}")
	public String obtenerPoliza(@PathVariable("id") Long id) {
		return "Poliza con ID: "+ id;
	}
	
    //Devuelve la poliza con la clave dada
    @GetMapping("/clave/{UUID}")
    public Poliza obtenerPoliza(@PathVariable("UUID") UUID id) {
       return polizaService.obtenerPoliza(id);
    }
    
    //Devuelve todas las polizas por el tipo de poliza dado
    @GetMapping("/tipo/{tipoPoliza}")
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
    public List<Poliza> obtenerPolizasPorCurp(@PathVariable("curp") String curp) {
        return polizaService.obtenerPolizasPorCurp(curp);
    }
    
    //Actualiza el cliente con los atributos dados.
    @PutMapping("/cliente/{curp}/{direccion}/{fecha_nacimiento}/{nombres}/{primer_apellido}/{segundo_apellido}")
    public String actualizarCliente(@PathVariable("curp") String curp,
                                    @PathVariable("direccion") String direccion,
                                    @PathVariable("fecha_nacimiento") Date fechaNacimiento,
                                    @PathVariable("nombres") String nombres,
                                    @PathVariable("primer_apellido") String primerApellido,
                                    @PathVariable(value = "segundo_apellido", required = false) String segundoApellido) {
        
    	clienteService.actualizarCliente(curp, direccion, new java.sql.Date(fechaNacimiento.getTime()), nombres, primerApellido, segundoApellido);
        return "Cliente actualizado con éxito";
    }
    
    //Actualiza la poliza con los atributos dados
    @PutMapping("/poliza/{clave}/{tipo}/{monto}/{descripcion}/{curp_cliente}")
    public String actualizarPoliza(@PathVariable("clave") UUID clave,
                                    @PathVariable("tipo") TipoPoliza tipo,
                                    @PathVariable("monto") double monto,
                                    @PathVariable("descripcion") String descripcion,
                                    @PathVariable("curp_cliente") String curpCliente) {
        polizaService.actualizarPolizaConAtributos(clave, tipo, monto, descripcion, curpCliente);
        return "Póliza actualizada con éxito";
    }
    
    @PostMapping("/cliente/{curp}")
    public Poliza crearPoliza(@PathVariable("curp") String curp, @RequestBody Poliza poliza) {
        return polizaService.crearPoliza(curp,poliza);
    }
    
    //Operacion DELET
    @DeleteMapping("/{id}")
    public String eliminarPoliza(@PathVariable("id")UUID id) {
    	polizaService.eliminarPoliza(id);
    	return "Poliza eliminada con exito";
    }
}