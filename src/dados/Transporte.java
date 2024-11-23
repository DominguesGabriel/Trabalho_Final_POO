package dados;

import java.util.ArrayList;

public abstract class Transporte {

	private int numero;

	private String nomeCliente;

	private String descricao;

	private double peso;

	private double latitudeOrigem;

	private double latitudeDestino;

	private double longitudeOrigem;

	private double longitudeDestino;

	private Estado Situacao;

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
		this.Situacao = Estado.PENDENTE;
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


	public String toString() {
		return String.format("""
				-------------------------------------------------------------
				Peso: %.2f
				Numero: %d
				NomeCliente: %s
				Descricao: %s
				LatitudeOrigem: %.2f
				LatitudeDestino: %.2f
				LongitudeOrigem: %.2f
				LongitudeDestino: %.2f
				Situacao: %s
				
				""",peso, numero,nomeCliente,descricao,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino,situacao);
	}
}
