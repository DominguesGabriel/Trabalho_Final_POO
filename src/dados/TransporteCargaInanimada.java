package dados;

public class TransporteCargaInanimada extends Transporte {

	private boolean cargaPerigosa;

	public TransporteCargaInanimada(int numero, String nomeCliente,String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, boolean cargaPerigosa) {
		super(numero,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino);
		this.cargaPerigosa = cargaPerigosa;
	}
	@Override
	public String toString(){
		return super.toString() + String.format("""
				Carga Perigosa: %b
				-------------------------------------------------------------
				""", cargaPerigosa);
	}
	public void setCargaPerigosa() {
		this.cargaPerigosa = true;
	}

	@Override
	public double calculaCusto() {
		return 0.0;
	}

	public String geraCSV(){
		return String.format("2;%s;%b",super.geraCSV(),getCargaPerigosa());
	}

	public boolean getCargaPerigosa() {
		return cargaPerigosa;
	}

}
