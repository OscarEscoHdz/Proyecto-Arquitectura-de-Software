package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	Optional<Cliente> findByCurp(String curp);
}
 