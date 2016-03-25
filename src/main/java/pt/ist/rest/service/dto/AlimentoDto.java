package pt.ist.rest.service.dto;

public class AlimentoDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private String tipo;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public AlimentoDto(String tipo){
		this.tipo = tipo;
	}
	
	public AlimentoDto(){}

}
