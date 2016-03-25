package pt.ist.rest.service.dto;

import java.util.List;

public class TabuleiroDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<PratoDto> listPratoDto;
	private float custo;
	private float saldo;
	
	public TabuleiroDto(){}
	
	public TabuleiroDto(List<PratoDto> list, float custo, float saldo){
		listPratoDto = list;
		this.setCusto(custo);
		this.setSaldo(saldo);
	}
	
	public List<PratoDto> getListPratoDto(){
		return this.listPratoDto;
	}
	
	private void setCusto(float custo){
		this.custo = custo;
	}
	
	public float getCusto(){
		return this.custo;
	}
	
	private void setSaldo(float saldo){
		this.saldo = saldo;
	}
	
	public float getSaldo(){
		return this.saldo;
	}
}
