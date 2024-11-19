package repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.qos.logback.core.net.server.Client;
import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;

public interface BeneficiarioRepository extends JpaRepository<Client, String>{
	Optional<Beneficiario> findBeneficiarioByPolizaAndDatos(Poliza poliza, String nombres, String primerApellido, String segundoApellido, Date fechaNacimiento);

}
