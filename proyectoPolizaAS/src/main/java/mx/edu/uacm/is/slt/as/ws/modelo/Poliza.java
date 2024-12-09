package mx.edu.uacm.is.slt.as.ws.modelo;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne
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



	public Poliza() {
		super();
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
	
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            return true;
        } else if (o instanceof Poliza) {
            Poliza otro = (Poliza) o;
            return Objects.equals(this.clavePoliza, otro.clavePoliza);
        } else {
            return false;
        }
    }
    
    public int hashCode() {
    	return Objects.hash(this.clavePoliza);
    }



	public void setCliente(Cliente cliente2) {
		// TODO Auto-generated method stub
		
	}

	public void setTipoPoliza(TipoPoliza tipoSeguro) {
		// TODO Auto-generated method stub		
	}

	public void setClavePoliza(UUID clavePoliza) {
	    this.clavePoliza = clavePoliza;
	}

	public void setMontoAsegurado(double montoAsegurado2) {
		// TODO Auto-generated method stub
		
	}



	public void setDescripcion(String descripcion2) {
		// TODO Auto-generated method stub
		
	}
    
}
