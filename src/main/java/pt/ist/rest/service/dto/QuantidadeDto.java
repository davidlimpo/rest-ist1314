package pt.ist.rest.service.dto;

public class QuantidadeDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int quantidade;
	
	public QuantidadeDto(){}
	
	public QuantidadeDto(int q){
		this.setQuantidade(q);
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	private void setQuantidade(int q) {
		this.quantidade = q;
	}
}

