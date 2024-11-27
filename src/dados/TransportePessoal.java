package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;
	private DronePessoal dronePessoal;


	public TransportePessoal(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem,
							 double latitudeDestino, double longitudeOrigem, double longitudeDestino, int qtdPessoas) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.qtdPessoas = qtdPessoas;

	}

	@Override
	public String toString() {
		//double custoFinal = dronePessoal != null ? calculaCustoFinal(dronePessoal) : 0.0;

		return super.toString() + String.format("""
                Quantidade de Pessoas: %d
                Custo Final: %.2f
                Distancia a ser percorrida: %.2f
                -------------------------------------------------------------
                """, qtdPessoas, calculaCusto(),calculaDistancia()); // ver com o rodrigo
	}

	@Override
	public double calculaCusto() {
		return calculaCustoFinal(dronePessoal);
	}
	public double calculaCustoFinal(DronePessoal dronePessoal){
		double custoFin =0.0;
		if(dronePessoal != null){
			custoFin = (dronePessoal.calculaCustoKm() * calculaDistancia()) + (10 * getQtdPessoas());
			return custoFin;
		}
		return custoFin;
	}

	public String geraCSV() {
		return String.format("1;%s;%d", super.geraCSV(), getQtdPessoas());
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setDronePessoal(DronePessoal dronePessoal) {
		this.dronePessoal = dronePessoal;
	}
}
