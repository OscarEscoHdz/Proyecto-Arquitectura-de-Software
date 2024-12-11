package mx.edu.uacm.is.slt.as.ws.modelo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class IdBeneficiarioPoliza implements Serializable {
   private String nombres;
   private String primerApellido;
   private String segundoApellido;
   private Date fechaNacimiento;
   private UUID clavePoliza;
   private static final long serialVersionUID = 5846409127633959899L;

   public IdBeneficiarioPoliza() {
   }

   public IdBeneficiarioPoliza(String nombres, String primerApellido, String segundoApellido, Date fechaNacimiento, UUID clavePoliza) {
      this.nombres = nombres;
      this.primerApellido = primerApellido;
      this.segundoApellido = segundoApellido;
      this.fechaNacimiento = fechaNacimiento;
      this.clavePoliza = clavePoliza;
   }

   public String getNombres() {
      return this.nombres;
   }

   public String getPrimerApellido() {
      return this.primerApellido;
   }

   public String getSegundoApellido() {
      return this.segundoApellido;
   }

   public Date getFechaNacimiento() {
      return this.fechaNacimiento;
   }

   public UUID getClavePoliza() {
      return this.clavePoliza;
   }

   public void setNombres(String nombres) {
      this.nombres = nombres;
   }

   public void setPrimerApellido(String primerApellido) {
      this.primerApellido = primerApellido;
   }

   public void setSegundoApellido(String segundoApellido) {
      this.segundoApellido = segundoApellido;
   }

   public void setFechaNacimiento(Date fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   public void setClavePoliza(UUID clavePoliza) {
      this.clavePoliza = clavePoliza;
   }

   public boolean equals(Object o) {
      if (super.equals(o)) {
         return true;
      } else if (!(o instanceof IdBeneficiarioPoliza)) {
         return false;
      } else {
         IdBeneficiarioPoliza otroIdBeneficiario = (IdBeneficiarioPoliza)o;
         return Objects.equals(this.nombres, otroIdBeneficiario.nombres) && Objects.equals(this.primerApellido, otroIdBeneficiario.primerApellido) && Objects.equals(this.segundoApellido, otroIdBeneficiario.segundoApellido) && Objects.equals(this.fechaNacimiento, otroIdBeneficiario.fechaNacimiento) && Objects.equals(this.clavePoliza, otroIdBeneficiario.clavePoliza);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.nombres, this.primerApellido, this.segundoApellido, this.fechaNacimiento, this.clavePoliza});
   }
}
