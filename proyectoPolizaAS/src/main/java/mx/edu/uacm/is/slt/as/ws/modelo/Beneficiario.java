package mx.edu.uacm.is.slt.as.ws.modelo;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "beneficiarios")
public class Beneficiario extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float porcentaje;

    // Ahora se almacena solo el UUID de la póliza asociada
    @Column(name = "id_poliza", nullable = false)
    private UUID poliza;


    // Constructores
    public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento,
            Long id, float porcentaje, UUID poliza) throws Exception {
        super(nombre, primerApellido, segundoApellido, fechaNacimiento);
        this.id = id;
        this.porcentaje = porcentaje;
        this.poliza = poliza;
    }
    
    public Beneficiario(String nombre, String primerApellido, String segundoApellido, Date fechaNacimiento,
			float porcentaje, UUID poliza) throws Exception {
		super(nombre, primerApellido, segundoApellido, fechaNacimiento);
		this.porcentaje = porcentaje;
		this.poliza = poliza;
	}

	public Beneficiario() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public UUID getPoliza() {
        return poliza;
    }

    public void setPoliza(UUID poliza) {
        this.poliza = poliza;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            return true;
        } else if (o instanceof Beneficiario) {
            Beneficiario otro = (Beneficiario) o;
            return Objects.equals(this.id, otro.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}

