package pt.ist.rest.service.dto;

public class ClienteDto implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private String username;
	private String password;
	private String morada;
	private String email;
	private int nif;
	private float saldo;
	
	public ClienteDto(String nome, String username, String password, String morada, String email, int nif, float saldo){
		this.setNome(nome);
		this.setUsername(username);
		this.setPassword(password);
		this.setMorada(morada);
		this.setEmail(email);
		this.setNif(nif);
		this.setSaldo(saldo);
	}
	
	public ClienteDto(String username){
		this.setUsername(username);
	}
	
	public ClienteDto(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public ClienteDto(){}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getMorada() {
		return morada;
	}

	private void setMorada(String morada) {
		this.morada = morada;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	public int getNif() {
		return nif;
	}

	private void setNif(int nif) {
		this.nif = nif;
	}

	public float getSaldo() {
		return saldo;
	}

	private void setSaldo(float saldo) {
		this.saldo = saldo;
	}


}
