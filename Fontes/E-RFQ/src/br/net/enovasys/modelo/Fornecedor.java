      package br.net.enovasys.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe do modelo referente a tabela F0101.
 * 
 * @author alexlirio
 * 
 */
@Entity
@Table(name = "F0101")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer fornecedorID;
	private String fornecedorCnpj;
	private String fornecedorNome;
	private String fornecedorEmail;

	private List<CotacaoFornecedor> cotacoesFornecedores = new ArrayList<CotacaoFornecedor>();

	/**
	 * Construtores
	 */
	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer fornecedorID, String fornecedorCnpj,
			String fornecedorNome, String fornecedorEmail,
			List<CotacaoFornecedor> cotacoesFornecedores) {
		super();
		this.fornecedorID = fornecedorID;
		this.fornecedorCnpj = fornecedorCnpj;
		this.fornecedorNome = fornecedorNome;
		this.fornecedorEmail = fornecedorEmail;
		this.cotacoesFornecedores = cotacoesFornecedores;
	}

	/**
	 * Getters and Setters
	 */
	@Id
	@Column(name = "AN8")
	public Integer getFornecedorID() {
		return fornecedorID;
	}

	public void setFornecedorID(Integer fornecedorID) {
		this.fornecedorID = fornecedorID;
	}

	@Column(name = "TAX")
	public String getFornecedorCnpj() {
		return fornecedorCnpj;
	}

	public void setFornecedorCnpj(String fornecedorCnpj) {
		this.fornecedorCnpj = fornecedorCnpj;
	}

	@Column(name = "ALPH")
	public String getFornecedorNome() {
		return fornecedorNome;
	}

	public void setFornecedorNome(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}

	@Column(name = "EMAL")
	public String getFornecedorEmail() {
		return fornecedorEmail;
	}

	public void setFornecedorEmail(String fornecedorEmail) {
		this.fornecedorEmail = fornecedorEmail;
	}

	@OneToMany(mappedBy = "fornecedor")
	public List<CotacaoFornecedor> getCotacoes() {
		return cotacoesFornecedores;
	}

	public void setCotacoes(List<CotacaoFornecedor> cotacoesFornecedores) {
		this.cotacoesFornecedores = cotacoesFornecedores;
	}
}
