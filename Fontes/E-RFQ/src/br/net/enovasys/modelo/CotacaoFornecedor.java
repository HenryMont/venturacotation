package br.net.enovasys.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.net.enovasys.config.ConverteData;


/**
 * Classe do modelo referente a tabela F4330
 * 
 * @author alexlirio
 * 
 */
@Entity
@Table(name = "F4330")
public class CotacaoFornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CotacaoFornecedorID cotacaoFornecedorID;
	private Fornecedor fornecedor;
	private Cotacao cotacao;
	private Integer cotacaoDataPedido;
	private Character cotacaoQtdImpressao;
	private Double qtdLiberada;
	private Integer respostaData;
	private Integer respostaDataEntrega;
	private Integer respostaDataLimite;
	private Character respostaQtd;
	private Double valorLiberado;
	
	// Para utilizar Datas Gregorianas
	private Date respostaDataGregoriana;
	private Date respostaDataEntregaGregoriana;
	private Date respostaDataLimiteGregoriana;

	private Set<RespostaCotacao> respostasCotacao = new HashSet<RespostaCotacao>();

	/**
	 * Construtores
	 */
	public CotacaoFornecedor() {
		super();
	}
	
	public CotacaoFornecedor(CotacaoFornecedorID cotacaoFornecedorID, Cotacao cotacao) {
		this.cotacaoFornecedorID = cotacaoFornecedorID;
		this.cotacao = cotacao;
	}

	/**
	 * Getters and Setters
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "AN8", column = @Column(name = "AN8", nullable = false)),
			@AttributeOverride(name = "KCOO", column = @Column(name = "KCOO", nullable = false)),
			@AttributeOverride(name = "DOCO", column = @Column(name = "DOCO", nullable = false)),
			@AttributeOverride(name = "DCTO", column = @Column(name = "DCTO", nullable = false)),
			@AttributeOverride(name = "LNID", column = @Column(name = "LNID", nullable = false, precision = 17, scale = 17)),
			@AttributeOverride(name = "SFXO", column = @Column(name = "SFXO", nullable = false)) })
	public CotacaoFornecedorID getCotacaoFornecedorID() {
		return cotacaoFornecedorID;
	}

	public void setCotacaoFornecedorID(CotacaoFornecedorID cotacaoFornecedorID) {
		this.cotacaoFornecedorID = cotacaoFornecedorID;
	}

	@ManyToOne
	@JoinColumn(name = "AN8", referencedColumnName = "AN8", nullable = false, insertable = false, updatable = false)
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "KCOO", referencedColumnName = "KCOO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DOCO", referencedColumnName = "DOCO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DCTO", referencedColumnName = "DCTO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LNID", referencedColumnName = "LNID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SFXO", referencedColumnName = "SFXO", nullable = false, insertable = false, updatable = false) })
	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}

	@Column(name = "TRDJ")
	public Integer getCotacaoDataPedido() {
		return cotacaoDataPedido;
	}

	public void setCotacaoDataPedido(Integer cotacaoDataPedido) {
		this.cotacaoDataPedido = cotacaoDataPedido;
	}

	@Column(name = "QPRT")
	public Character getCotacaoQtdImpressao() {
		return cotacaoQtdImpressao;
	}

	public void setCotacaoQtdImpressao(Character cotacaoQtdImpressao) {
		this.cotacaoQtdImpressao = cotacaoQtdImpressao;
	}

	@Column(name = "UREL")
	public Double getQtdLiberada() {
		return qtdLiberada;
	}

	public void setQtdLiberada(Double qtdLiberada) {
		this.qtdLiberada = qtdLiberada;
	}

	@Column(name = "QRDJ")
	public Integer getRespostaData() {
		return respostaData;
	}

	public void setRespostaData(Integer respostaData) {
		this.respostaData = respostaData;
	}

	@Column(name = "PDDJ")
	public Integer getRespostaDataEntrega() {
		return respostaDataEntrega;
	}

	public void setRespostaDataEntrega(Integer respostaDataEntrega) {
		this.respostaDataEntrega = respostaDataEntrega;
	}

	@Column(name = "RQQJ")
	public Integer getRespostaDataLimite() {
		return respostaDataLimite;
	}

	public void setRespostaDataLimite(Integer respostaDataLimite) {
		this.respostaDataLimite = respostaDataLimite;
	}

	@Column(name = "RSPO")
	public Character getRespostaQtd() {
		return respostaQtd;
	}

	public void setRespostaQtd(Character respostaQtd) {
		this.respostaQtd = respostaQtd;
	}

	@Column(name = "AREL")
	public Double getValorLiberado() {
		return valorLiberado;
	}

	public void setValorLiberado(Double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}

	@Transient
	public Date getRespostaDataGregoriana() {
		Date dt = new ConverteData().toDate(respostaData);
		respostaDataGregoriana = dt;
		return respostaDataGregoriana;
	}

	public void setRespostaDataGregoriana(Date respostaDataGregoriana) {
		Integer dt = new ConverteData().toJulianDate(respostaDataGregoriana);
		respostaData = dt;
		this.respostaDataGregoriana = respostaDataGregoriana;
	}

	@Transient
	public Date getRespostaDataEntregaGregoriana() {
		Date dt = new ConverteData().toDate(respostaDataEntrega);
		respostaDataEntregaGregoriana = dt;
		return respostaDataEntregaGregoriana;
	}

	public void setRespostaDataEntregaGregoriana(Date respostaDataEntregaGregoriana) {
		try {
			Integer dt = new ConverteData().toJulianDate(respostaDataEntregaGregoriana);
			respostaDataEntrega = dt;
			this.respostaDataEntregaGregoriana = respostaDataEntregaGregoriana;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transient
	public Date getRespostaDataLimiteGregoriana() {
		Date dt = new ConverteData().toDate(respostaDataLimite);
		respostaDataLimiteGregoriana = dt;
		return respostaDataLimiteGregoriana;
	}

	public void setRespostaDataLimiteGregoriana(Date respostaDataLimiteGregoriana) {
		Integer dt = new ConverteData().toJulianDate(respostaDataLimiteGregoriana);
		respostaDataLimite = dt;
		this.respostaDataLimiteGregoriana = respostaDataLimiteGregoriana;
	}

	@OneToMany(mappedBy = "cotacaoFornecedor")
	public Set<RespostaCotacao> getRespostasCotacao() {
		return respostasCotacao;
	}

	public void setRespostasCotacao(Set<RespostaCotacao> respostasCotacao) {
		this.respostasCotacao = respostasCotacao;
	}

}
