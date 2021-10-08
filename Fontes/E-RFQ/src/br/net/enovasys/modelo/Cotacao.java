package br.net.enovasys.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe do modelo referente a tabela F4311.
 * 
 * @author alexlirio
 * 
 */
@Entity
@Table(name = "F4311")
public class Cotacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CotacaoID cotacaoID;
	private String produtoCodigo;
	private Double cotacaoQtd;
	private Integer compradorID;
	private Integer produtoNumeroCurto;
	private String produtoDescricao1;
	private String produtoDescricao2;
	private String produtoUnidadeMedida;

	private Set<CotacaoFornecedor> cotacoesFornecedor = new HashSet<CotacaoFornecedor>();
	
	/**
	 * Construtores
	 */
	public Cotacao() {
		super();
	}

	public Cotacao(CotacaoID cotacaoID) {
		this.cotacaoID = cotacaoID;
	}

	public Cotacao(CotacaoID cotacaoID, Fornecedor fornecedor,
			String produtoCodigo, Double cotacaoQtd, Integer compradorID,
			Integer produtoNumeroCurto, String produtoDescricao1,
			String produtoDescricao2, String produtoUnidadeMedida,
			Set<CotacaoFornecedor> cotacoesFornecedor) {
		super();
		this.cotacaoID = cotacaoID;
		this.produtoCodigo = produtoCodigo;
		this.cotacaoQtd = cotacaoQtd;
		this.compradorID = compradorID;
		this.produtoNumeroCurto = produtoNumeroCurto;
		this.produtoDescricao1 = produtoDescricao1;
		this.produtoDescricao2 = produtoDescricao2;
		this.produtoUnidadeMedida = produtoUnidadeMedida;
		this.cotacoesFornecedor = cotacoesFornecedor;
	}

	/**
	 * Getters and Setters
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "KCOO", column = @Column(name = "KCOO", nullable = false)),
			@AttributeOverride(name = "DOCO", column = @Column(name = "DOCO", nullable = false)),
			@AttributeOverride(name = "LNID", column = @Column(name = "LNID", nullable = false, precision = 17, scale = 17)),
			@AttributeOverride(name = "DCTO", column = @Column(name = "DCTO", nullable = false)),
			@AttributeOverride(name = "SFXO", column = @Column(name = "SFXO", nullable = false)) })
	public CotacaoID getCotacaoID() {
		return cotacaoID;
	}

	public void setCotacaoID(CotacaoID cotacaoID) {
		this.cotacaoID = cotacaoID;
	}

	@Column(name = "LITM")
	public String getProdutoCodigo() {
		return produtoCodigo;
	}

	public void setProdutoCodigo(String produtoCodigo) {
		this.produtoCodigo = produtoCodigo;
	}

	@Column(name = "UORG")
	public Double getCotacaoQtd() {
		return cotacaoQtd;
	}

	public void setCotacaoQtd(Double cotacaoQtd) {
		this.cotacaoQtd = cotacaoQtd;
	}

	@Column(name = "AN8")
	public Integer getCotacaoSufixo() {
		return compradorID;
	}

	public void setCotacaoSufixo(Integer compradorID) {
		this.compradorID = compradorID;
	}
	
	@Column(name = "ITM")
	public Integer getProdutoNumeroCurto() {
		return produtoNumeroCurto;
	}

	public void setProdutoNumeroCurto(Integer produtoNumeroCurto) {
		this.produtoNumeroCurto = produtoNumeroCurto;
	}

	@Column(name = "DSC1")
	public String getProdutoDescricao1() {
		return produtoDescricao1;
	}

	public void setProdutoDescricao1(String produtoDescricao1) {
		this.produtoDescricao1 = produtoDescricao1;
	}

	@Column(name = "DSC2")
	public String getProdutoDescricao2() {
		return produtoDescricao2;
	}

	public void setProdutoDescricao2(String produtoDescricao2) {
		this.produtoDescricao2 = produtoDescricao2;
	}

	@Column(name = "UOM")
	public String getProdutoUnidadeMedida() {
		return produtoUnidadeMedida;
	}

	public void setProdutoUnidadeMedida(String produtoUnidadeMedida) {
		this.produtoUnidadeMedida = produtoUnidadeMedida;
	}

	@OneToMany(mappedBy = "cotacao")
	public Set<CotacaoFornecedor> getCotacoesFornecedor() {
		return cotacoesFornecedor;
	}

	public void setCotacoesFornecedor(Set<CotacaoFornecedor> cotacoesFornecedor) {
		this.cotacoesFornecedor = cotacoesFornecedor;
	}
	
}
