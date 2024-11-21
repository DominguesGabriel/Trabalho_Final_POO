package dados;

public class TransporteCargaViva extends Transporte {

	private double temperaturaMinima;

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
				-------------------------------------------------------------
				""", temperaturaMinima,temperaturaMaxima);
	}
	@Override
	public double calculaCusto() {
		return 0;
	}
}
