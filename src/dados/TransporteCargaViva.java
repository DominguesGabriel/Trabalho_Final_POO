package dados;

public class TransporteCargaViva extends Transporte {

	private double temperaturaMinima;
	private DroneCargaViva droneCargaViva;
	private double temperaturaMaxima;


	public TransporteCargaViva(int numero, String nomeCliente,String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino, double temperaturaMinima, double temperaturaMaxima) {
		super(numero,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino);
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
	}

	@Override
	public String toString(){
		return super.toString() + String.format("""
				Temperatura Minima: %.2f
				Temperatura Maxima: %.2f
				Distancia a ser percorrida: %.2f
				-------------------------------------------------------------
				""", temperaturaMinima,temperaturaMaxima,calculaDistancia());
	}
	@Override
	public double calculaCusto() {
		return calculaCustoFinal(droneCargaViva);
	}
	public double calculaCustoFinal(DroneCargaViva droneCargaViva){
		double custoFin =0.0;
		if(droneCargaViva != null){
			custoFin = (droneCargaViva.calculaCustoKm()*calculaDistancia()) + 1000;
			return custoFin;
		}
		return custoFin;
	}

	public String geraCSV(){
		return String.format("3;%s;%.2f;%.2f",super.geraCSV(),getTemperaturaMinima(),getTemperaturaMaxima());
	}

	public double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

}


