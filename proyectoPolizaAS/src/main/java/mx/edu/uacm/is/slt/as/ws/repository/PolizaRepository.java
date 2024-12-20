package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;
import mx.edu.uacm.is.slt.as.ws.modelo.Poliza;
import mx.edu.uacm.is.slt.as.ws.modelo.TipoPoliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, UUID>{
	
	List<Poliza>findByCliente(Cliente cliente);
	  Poliza findByClavePoliza(UUID clavePoliza);
	List<Poliza>findByTipoPoliza(TipoPoliza tipoPoliza);
	//Comentario de prueba

}
