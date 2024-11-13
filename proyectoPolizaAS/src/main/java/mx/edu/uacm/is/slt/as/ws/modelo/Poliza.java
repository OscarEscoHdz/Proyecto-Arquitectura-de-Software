package mx.edu.uacm.is.slt.as.ws.modelo;

public class Poliza {
	
	private String clavePoliza;
	private String tipoSeguro;
	private double montoAsegurado;
	private String descripcion;
	private Cliente cliente;
	
	public Poliza(String clavePoliza, String tipoSeguro, double montoAsegurado, String descripcion, Cliente cliente) {
		
		this.clavePoliza = clavePoliza;
		this.tipoSeguro = tipoSeguro;
		this.montoAsegurado = montoAsegurado;
		this.descripcion = descripcion;
		this.cliente = cliente;
	}

	public String getClavePoliza() {
		return clavePoliza;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
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
