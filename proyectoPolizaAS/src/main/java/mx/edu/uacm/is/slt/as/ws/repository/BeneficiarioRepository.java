package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.net.server.Client;
import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Client, String>{
	Optional<Beneficiario> findBeneficiarioByPolizaAndDatos(Poliza poliza, String nombres, String primerApellido, String segundoApellido, Date fechaNacimiento);
	@Query("SELECT p FROM Poliza p JOIN p.beneficiarios b WHERE b.nombre = :nombre AND b.primerApellido = :primerApellido AND b.segundoApellido = :segundoApellido")
    List<Poliza> findPolizasByBeneficiarios(
        @Param("nombre") String nombre,
        @Param("primerApellido") String primerApellido,
        @Param("segundoApellido") String segundoApellido
    );
}
