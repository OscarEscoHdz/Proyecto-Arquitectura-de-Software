package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.is.slt.as.ws.modelo.Beneficiario;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{
	Optional<Beneficiario> findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePolizaAndSegundoApellido(
            String nombre, String primerApellido, Date fechaNacimiento, UUID clavePoliza, String segundoApellido);

    Optional<Beneficiario> findByNombreAndPrimerApellidoAndFechaNacimientoAndPoliza_ClavePoliza(
            String nombre, String primerApellido, Date fechaNacimiento, UUID clavePoliza);
	
	@Query("SELECT p FROM Poliza p JOIN p.beneficiarios b WHERE b.nombre = :nombre AND b.primerApellido = :primerApellido AND b.segundoApellido = :segundoApellido")
    List<Poliza> findPolizasByBeneficiarios(
        @Param("nombre") String nombre,
        @Param("primerApellido") String primerApellido,
        @Param("segundoApellido") String segundoApellido
    );
}
