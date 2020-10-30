package progweb.vo;

public class PessoaVO {

	private Integer id;
	private String nome;
	private String cpf;
	private Integer idConjuge;
	// nomeConjuge: somente para exibição na tabela
	private String nomeConjuge;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdConjuge() {
		return idConjuge;
	}
	public void setIdConjuge(Integer idConjuge) {
		this.idConjuge = idConjuge;
	}
	public String getNomeConjuge() {
		return nomeConjuge;
	}
	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}
}
