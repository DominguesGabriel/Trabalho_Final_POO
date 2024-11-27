package dados;

public class TransporteCargaInanimada extends Transporte {

	private boolean cargaPerigosa;
	private DroneCargaInanimada droneCargaInanimada;

	public TransporteCargaInanimada(int numero, String nomeCliente,String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, boolean cargaPerigosa) {
		super(numero,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino);
		this.cargaPerigosa = cargaPerigosa;
	}
	@Override
	public String toString(){
		return super.toString() + String.format("""
				Carga Perigosa: %b
				Distancia a ser percorrida: %.2f
				-------------------------------------------------------------
				""", cargaPerigosa,calculaDistancia());
	}
	public void setCargaPerigosa() {
		this.cargaPerigosa = true;
	}

	@Override
	public double calculaCusto() {

		return calculaCustoFinal(droneCargaInanimada);
	}
	public double calculaCustoFinal(DroneCargaInanimada droneCargaInanimada){
		double custoFin =0.0;
		if(droneCargaInanimada != null){
			custoFin = (droneCargaInanimada.calculaCustoKm()*calculaDistancia()) + 500;
			return custoFin;
		}
		return custoFin;
	}

	public String geraCSV(){
		return String.format("2;%s;%b",super.geraCSV(),getCargaPerigosa());
	}

	public boolean getCargaPerigosa() {
		return cargaPerigosa;
	}

}
