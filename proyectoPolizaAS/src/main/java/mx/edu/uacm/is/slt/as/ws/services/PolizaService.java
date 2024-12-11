package mx.edu.uacm.is.slt.as.ws.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import excepcion.ResourceNotFoundException;
import mx.edu.uacm.is.slt.as.ws.modelo.*;
import mx.edu.uacm.is.slt.as.ws.repository.ClienteRepository;
import mx.edu.uacm.is.slt.as.ws.repository.PolizaRepository;

@Service
public class PolizaService {
	
	@Autowired
    private final PolizaRepository polizaRepository;
	@Autowired
	private final ClienteRepository clienteRepository;
	
	public PolizaService(PolizaRepository polizaRepository, ClienteRepository clienteRepository) {
		this.polizaRepository = polizaRepository; 
		this.clienteRepository = clienteRepository; 
	}
	
	public Poliza crearPoliza(String curp, Poliza poliza) {
		Cliente cliente = clienteRepository.findByCurp(curp).orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado"));
		poliza.setCliente(cliente);
		
		return polizaRepository.save(poliza);
	}
	
	public Poliza actualizarPoliza(UUID id, Poliza polizaActualizada) {
		Poliza poliza = polizaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
	        poliza.setTipoPoliza(polizaActualizada.getTipoSeguro());
	        poliza.setMontoAsegurado(polizaActualizada.getMontoAsegurado());
	        poliza.setDescripcion(polizaActualizada.getDescripcion());
	        return polizaRepository.save(poliza);
	}
	
	public void eliminarPoliza(UUID id) {
		Poliza poliza = polizaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
	        polizaRepository.delete(poliza);
	}
	
	public List<Poliza> obtenerTodasLasPolizas() {
        return polizaRepository.findAll();
    }
	
	 public Poliza obtenerPoliza(UUID id) {
		 return polizaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Poliza no encontrada"));
	 }
	 
	 public List<Poliza> obtenerPolizasTipo(TipoPoliza tipoPoliza){
		 return polizaRepository.findByTipoPoliza(tipoPoliza);
	 }
	 
	 public List<Poliza> obtenerPolizasPorCurp(String curp) {
	        Cliente cliente = clienteRepository.findByCurp(curp)
	            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
	        return polizaRepository.findByCliente(cliente);
	    }
	 
	 
	 public void actualizarPolizaConAtributos(UUID clave, TipoPoliza tipo, double monto, String descripcion, String curpCliente) {
		   
		    Poliza poliza = polizaRepository.findById(clave)
		        .orElseThrow(() -> new RuntimeException("Póliza no encontrada con la clave: " + clave));
		    
		    // Validar que el cliente con el CURP proporcionado exista
		    Cliente cliente = clienteRepository.findById(curpCliente)
		        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con el CURP: " + curpCliente));
		    
		    
		    poliza.setTipoPoliza(tipo);
		    poliza.setMontoAsegurado(monto);
		    poliza.setDescripcion(descripcion);
		    poliza.setCliente(cliente); 
		    
		  
		    polizaRepository.save(poliza);
		}
}
