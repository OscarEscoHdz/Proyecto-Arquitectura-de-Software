package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	Optional<Cliente> findByCurp(String curp);
	
	List<Cliente> findByNombreAndPrimerApellidoAndSegundoApellido(String nombre, String primerApellido, String segundoApellido);
}
 
