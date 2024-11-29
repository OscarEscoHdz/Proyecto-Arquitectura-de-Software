package mx.edu.uacm.is.slt.as.ws.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uacm.is.slt.as.ws.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
	Optional<Cliente> findByCurp(UUID curp);
}
