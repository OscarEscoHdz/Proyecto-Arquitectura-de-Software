package mx.edu.uacm.is.slt.as.ws.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polizas")
public class PolizaControlador {
	
	@GetMapping("/{id}")
	public String obtenerPoliza(@PathVariable("id") Long id) {
		return "Poliza con ID: "+ id;
	}
	
	@PutMapping("/{id}")
	public String actualizarPoliza(@PathVariable("id") Long id, @RequestBody String poliza) {
		return "\n\nPoliza con ID: " + id + " actualizada a: " + poliza +"\n\n";
	}
}