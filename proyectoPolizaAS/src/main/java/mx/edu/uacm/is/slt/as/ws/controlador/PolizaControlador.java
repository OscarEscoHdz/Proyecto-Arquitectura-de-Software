package mx.edu.uacm.is.slt.as.ws.controlador;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;

@RestController
@RequestMapping("/api/polizas")
public class PolizaControlador {
	
	@PostMapping("/cliente/{curp}")					//post --> insertar 
	public String crearPoliza(@RequestBody Poliza poliza ) {
		return "Curp cliente:" + poliza;
	}
	
	@GetMapping("/{id}")
	public String obtenerPoliza(@PathVariable("id") Long id) {
		return "Poliza con ID: "+ id;
	}
	
	@PutMapping("/{id}")//Se agrego metodo put
	public String actualizarPoliza(@PathVariable("id") Long id, @RequestBody String poliza) {
		return "\n\nPoliza con ID: " + id + " actualizada a: " + poliza +"\n\n";
	}

	@DeleteMapping("/{id}")//metodo delete
	public String eliminarPoliza(@PathVariable("id") Long id) {
		return "La Poliza con ID: " + id + "ha sido eliminada";
	}
}