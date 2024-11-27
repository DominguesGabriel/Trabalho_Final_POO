package dados;


public abstract class Transporte {

	private int numero;

	private String nomeCliente;

	private String descricao;

	private double peso;

	private double latitudeOrigem;

	private double latitudeDestino;

	private double longitudeOrigem;

	private double longitudeDestino;

	private Drone droneAlocado;

	private Estado situacao;

	public Transporte(int numero, String nomeCliente,String descricao, double peso, double latitudeOrigem, double latitudeDestino, double longitudeOrigem, double longitudeDestino) {
		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.descricao = descricao;
		this.peso = peso;
		this.latitudeOrigem = latitudeOrigem;
		this.latitudeDestino = latitudeDestino;
		this.longitudeOrigem = longitudeOrigem;
		this.longitudeDestino = longitudeDestino;
		this.situacao = Estado.PENDENTE;
	}
	public int getNumero() {
		return numero;
	}

	public void setSituacao(Estado situacao) {
		this.situacao = situacao;
	}

	public Estado getSituacao() {return situacao;}

	public double getPeso(){
		return peso;
	}

	public double getLatitudeOrigem() {
		return latitudeOrigem;
	}

	public double getLatitudeDestino() {
		return latitudeDestino;
	}

	public double getLongitudeOrigem() {
		return longitudeOrigem;
	}

	public double getLongitudeDestino() {
		return longitudeDestino;
	}

	public abstract double calculaCusto();

	public double calculaDistancia(){
		double lat1Rad = Math.toRadians(getLatitudeOrigem());
		double lat2Rad = Math.toRadians(getLatitudeDestino());
		double long1Rad= Math.toRadians(getLongitudeOrigem());
		double long2Rad = Math.toRadians(getLongitudeDestino());

		final int raioTerraKm = 6371;

		double deltaLat = lat2Rad - lat1Rad;
		double deltaLong = long2Rad - long1Rad;

		double a = Math.pow(Math.sin(deltaLat/2),2) + Math.cos(lat1Rad)*Math.cos(lat2Rad)*Math.pow(Math.sin(deltaLong/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return raioTerraKm * c;
	}
	public void adcDrone(Drone drone){
		this.droneAlocado = drone; // Associa o drone ao transporte
		this.situacao = Estado.ALOCADO; // Atualiza o estado do transporte para ALOCADO
		drone.setAlocado(true); // Marca o drone como alocado
		drone.setTransporte(this);

	}


	public String toString() {
		return String.format("""
				-------------------------------------------------------------
				Nome Cliente: %s
				Número do transporte: %d
				Peso: %.2f
				Descrição: %s
				Latitude de Origem: %.2f
				Latitude de Destino: %.2f
				Longitude de Origem: %.2f
				Longitude de Destino: %.2f
				Custo Final: %.2f
				Situação: %s
				""",nomeCliente, numero,peso,descricao,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino,calculaCusto(),situacao);
	}

	public String getNomeCliente(){
		return nomeCliente;
	}

	public String getDescricao(){
		return descricao;
	}

	public Drone getDroneAlocado(){ return droneAlocado;}

	public String geraCSV(){
		return String.format("%d;%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;",getNumero(),getNomeCliente(),getDescricao(),getPeso(),getLatitudeOrigem(),getLatitudeDestino(),getLongitudeOrigem(),getLongitudeDestino());
	}
}
