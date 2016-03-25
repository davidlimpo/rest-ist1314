package pt.ist.rest.service.dto;

public class RestauranteDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String morada;
	private float classificacao;
	
	public RestauranteDto(){}
	
	public RestauranteDto(String nome, String morada, float classificacao){
		this.setNome(nome);
		this.setMorada(morada);
		this.setClassificacao(classificacao);
	}
	
	public RestauranteDto(String nome){
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	private void setMorada(String morada) {
		this.morada = morada;
	}

	public float getClassificacao() {
		return classificacao;
	}

	private void setClassificacao(float classificacao) {
		this.classificacao = classificacao;
	}
}
