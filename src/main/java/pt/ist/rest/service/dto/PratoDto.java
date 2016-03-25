package pt.ist.rest.service.dto;

import java.util.Set;

public class PratoDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private float preco;
	private int calorias;
	private int id;
	private int classificacao;
	private RestauranteDto restauranteDto;
	private QuantidadeDto quantidadeDto;
	private Set<AlimentoDto> alimentosPrato;

	
	public PratoDto(){}
	
	public PratoDto(String nome, float preco, int calorias, int id){
		this.setNome(nome);
		this.setPreco(preco);
		this.setCalorias(calorias);
		this.setId(id);
	}
	
	public PratoDto(String nome, float preco, int calorias, int id, QuantidadeDto quantidade){
		this.setNome(nome);
		this.setPreco(preco);
		this.setCalorias(calorias);
		this.setId(id);
		this.setQuantidadeDto(quantidade);
	}

	public PratoDto(String nome, float preco, int calorias, int id, int classificacao, RestauranteDto restauranteDto){
		this.setNome(nome);
		this.setPreco(preco);
		this.setCalorias(calorias);
		this.setId(id);
		this.setClassificacao(classificacao);
		this.setRestauranteDto(restauranteDto);
	}
		
	public PratoDto(String nome, RestauranteDto restaurante){
		this.setNome(nome);
		this.setRestauranteDto(restaurante);
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	private void setPreco(float preco) {
		this.preco = preco;
	}

	public int getCalorias() {
		return calorias;
	}

	private void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
	
	public float getClassificacao() {
		return classificacao;
	}

	private void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	
	public RestauranteDto getRestauranteDto() {
		return restauranteDto;
	}

	private void setRestauranteDto(RestauranteDto rDto) {
		this.restauranteDto = rDto;
	}
	
	private void setQuantidadeDto(QuantidadeDto quantidadeDto) {
		this.quantidadeDto = quantidadeDto;
	}

	public QuantidadeDto getQuantidadeDto() {
		return this.quantidadeDto;
	}

	public Set<AlimentoDto> getAlimentosPrato() {
		return alimentosPrato;
	}

	public void setAlimentosPrato(Set<AlimentoDto> alimentosPrato) {
		this.alimentosPrato = alimentosPrato;
	}
}
