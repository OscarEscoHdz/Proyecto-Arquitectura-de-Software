package mx.edu.uacm.is.slt.as.ws.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {
	@Autowired
    private BeneficiarioRepository beneficiarioRepository;

	public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
		this.beneficiarioRepository = beneficiarioRepository;
	}
	
	
	public List<Poliza> obtenerPolizasPorBeneficiario(String nombres, String primerApellido, String segundoApellido) {
		return beneficiarioRepository.findPolizasByBeneficiarios(nombres, primerApellido, segundoApellido);
	}
	
	public Beneficiario obtenerBeneficiario(Date fechaNacimiento, UUID clavePoliza, String nombres, String primerApellido, String segundoApellido) {
		Optional<Beneficiario> beneficiario = null;

		if (segundoApellido != null && !segundoApellido.isEmpty()) {
			beneficiario = beneficiarioRepository.findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePolizaAndSegundoApellido(nombres, primerApellido, fechaNacimiento, clavePoliza, segundoApellido);

		} else {
			beneficiario = beneficiarioRepository.findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePoliza(nombres, primerApellido, fechaNacimiento, clavePoliza);
		}
		
		return beneficiario.get();
	}


	public List<Poliza> obtenerPolizasPorBeneficiarioConFecha(Date fechaNacimiento) {
		
		return beneficiarioRepository.findPolizasByBeneficiariosConFecha(fechaNacimiento);
	}
}
