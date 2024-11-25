package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;


	public TransportePessoal(int numero, String nomeCliente,String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino,int qtdPessoas) {
		super(numero,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino);
		this.qtdPessoas = qtdPessoas;
	}

	@Override
	public String toString() {
		Administracao adm = new Administracao();
		return super.toString() + String.format("""
				Quantidade de Pessoas: %d
				Distancia a ser percorrida: %.2f
				-------------------------------------------------------------
				""",qtdPessoas,calculaDistancia());
	}
	@Override
	public double calculaCusto() {
		return 0;
	}

	public String geraCSV(){
		return String.format("1;%s;%d",super.geraCSV(),getQtdPessoas());
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

}
