package mx.edu.uacm.is.slt.as.ws.modelo;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="polizas")
public class Poliza {
	
	@Id
	@GeneratedValue
	private UUID clavePoliza;
	private TipoPoliza tipoPoliza;
	private double montoAsegurado;
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "curp_cliente", nullable = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL)	
	private List<Beneficiario> beneficiarios;
	
	
	public Poliza(UUID clavePoliza, TipoPoliza tipoPoliza, double montoAsegurado, String descripcion, Cliente cliente) {
		this.clavePoliza = clavePoliza;
		this.tipoPoliza = tipoPoliza;
		this.montoAsegurado = montoAsegurado;
		this.descripcion = descripcion;
		this.cliente = cliente;
	}
	
	

	public Poliza(UUID clavePoliza, TipoPoliza tipoPoliza, double montoAsegurado, String descripcion, Cliente cliente,
			List<Beneficiario> beneficiarios) {
		super();
		this.clavePoliza = clavePoliza;
		this.tipoPoliza = tipoPoliza;
		this.montoAsegurado = montoAsegurado;
		this.descripcion = descripcion;
		this.cliente = cliente;
		this.beneficiarios = beneficiarios;
	}



	public UUID getClavePoliza() {
		return clavePoliza;
	}

	public TipoPoliza getTipoSeguro() {
		return tipoPoliza;
	}

	public double getMontoAsegurado() {
		return montoAsegurado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
