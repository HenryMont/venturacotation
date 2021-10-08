package br.net.enovasys.modelo;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQuery;

import br.net.enovasys.config.ConverteData;

/**
 * Classe do modelo referente a tabela F4331
 * 
 * @author alexlirio
 * 
 */
@Entity
@Table(name = "F4331")
@NamedQuery(name = "buscarRespostasDasCotacoesPrincipais", query =
			"SELECT c FROM RespostaCotacao c" +
			" WHERE c.respostaCotacaoID.fornecedorID = :fornecedorID" +
			" AND c.respostaCotacaoID.cotacaoID = :cotacaoID" +
			" AND c.cotacaoFornecedor.respostaDataLimite >= :dataAtual" + //Incluido para limitar a data
			" AND c.respostaCotacaoID.ciaID = c.cotacaoFornecedor.cotacaoFornecedorID.ciaID" +
			" AND c.respostaCotacaoID.cotacaoLinha = c.cotacaoFornecedor.cotacaoFornecedorID.cotacaoLinha" +
			" AND c.respostaCotacaoID.cotacaoTipo = c.cotacaoFornecedor.cotacaoFornecedorID.cotacaoTipo" +
			" AND c.respostaCotacaoID.cotacaoSufixo = c.cotacaoFornecedor.cotacaoFornecedorID.cotacaoSufixo" +
			" ORDER BY c.cotacaoFornecedor.cotacao.produtoCodigo, c.respostaCotacaoID.cotacaoQtd")
public class RespostaCotacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private RespostaCotacaoID respostaCotacaoID;
	private CotacaoFornecedor cotacaoFornecedor;
	private String moedaCodigo;
	private Integer respostaData;
	private Integer respostaDataEntrega;
	private Double respostaValorUnitario;
	private Integer vencimentoData;

	// Para utilizar Datas Gregorianas
	private Date respostaDataGregoriana;
	private Date respostaDataEntregaGregoriana;
	private Date vencimentoDataGregoriana;
	
	// Para utilizar Valor normal. Diferente do JDE que exige quatro zeros a mais
	private Double respostaValorUnitarioNormal;

	// Para controlar o Status da Resposta. Status 'Finalizado(F)' ou 'Rascunho(R)'
	private String statusDaResposta;
	
	// Para controlar o Status de Processamento de sincronizacao para o JDE. Status 'Não enviado(0)' ou 'Enviado(1)'
	private String statusDeProcessamento;
	
	/**
	 * Construtores
	 */
	public RespostaCotacao() {
		super();
	}
	
	public RespostaCotacao(RespostaCotacaoID respostaCotacaoID) {
		super();
		this.respostaCotacaoID = respostaCotacaoID;
	}

	public RespostaCotacao(RespostaCotacaoID respostaCotacaoID,
			CotacaoFornecedor cotacaoFornecedor, String moedaCodigo,
			Integer respostaData, Integer respostaDataEntrega,
			Double respostaValorUnitario, Integer vencimentoData) {
		super();
		this.respostaCotacaoID = respostaCotacaoID;
		this.cotacaoFornecedor = cotacaoFornecedor;
		this.moedaCodigo = moedaCodigo;
		this.respostaData = respostaData;
		this.respostaDataEntrega = respostaDataEntrega;
		this.respostaValorUnitario = respostaValorUnitario;
		this.vencimentoData = vencimentoData;
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
			@AttributeOverride(name = "AN8", column = @Column(name = "AN8", nullable = false)),
			@AttributeOverride(name = "SFXO", column = @Column(name = "SFXO", nullable = false)),
			@AttributeOverride(name = "UORG", column = @Column(name = "UORG", nullable = false)) })
	public RespostaCotacaoID getRespostaCotacaoID() {
		return respostaCotacaoID;
	}

	public void setRespostaCotacaoID(RespostaCotacaoID respostaCotacaoID) {
		this.respostaCotacaoID = respostaCotacaoID;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "AN8", referencedColumnName = "AN8", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "KCOO", referencedColumnName = "KCOO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DOCO", referencedColumnName = "DOCO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DCTO", referencedColumnName = "DCTO", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "LNID", referencedColumnName = "LNID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "SFXO", referencedColumnName = "SFXO", nullable = false, insertable = false, updatable = false) })
	public CotacaoFornecedor getCotacaoFornecedor() {
		return cotacaoFornecedor;
	}

	public void setCotacaoFornecedor(CotacaoFornecedor cotacaoFornecedor) {
		this.cotacaoFornecedor = cotacaoFornecedor;
	}

	@Column(name = "CRCD")
	public String getMoedaCodigo() {
		return moedaCodigo;
	}

	public void setMoedaCodigo(String moedaCodigo) {
		this.moedaCodigo = moedaCodigo;
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

	@Column(name = "PRRC")
	public Double getRespostaValorUnitario() {
		return respostaValorUnitario;
	}

	public void setRespostaValorUnitario(Double respostaValorUnitario) {
		this.respostaValorUnitario = respostaValorUnitario;
	}

	@Column(name = "CNDJ")
	public Integer getVencimentoData() {
		return vencimentoData;
	}

	public void setVencimentoData(Integer vencimentoData) {
		this.vencimentoData = vencimentoData;
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

	public void setRespostaDataEntregaGregoriana(
		Date respostaDataEntregaGregoriana) {
		Integer dt = new ConverteData().toJulianDate(respostaDataEntregaGregoriana);
		respostaDataEntrega = dt;
		this.respostaDataEntregaGregoriana = respostaDataEntregaGregoriana;
	}

	@Transient
	public Date getVencimentoDataGregoriana() {
		Date dt = new ConverteData().toDate(vencimentoData);
		vencimentoDataGregoriana = dt;
		return vencimentoDataGregoriana;
	}

	public void setVencimentoDataGregoriana(Date vencimentoDataGregoriana) {
		Integer dt = new ConverteData().toJulianDate(vencimentoDataGregoriana);
		vencimentoData = dt;
		this.vencimentoDataGregoriana = vencimentoDataGregoriana;
	}


	//Trata o formato de valor do JDE, com quatro digitos a mais, para o formato normal(WEB)
	@Transient
	public Double getRespostaValorUnitarioNormal() {
		Double resposta = respostaValorUnitario / 10000; 
		respostaValorUnitarioNormal = resposta;
		
		return respostaValorUnitarioNormal;
	}

	public void setRespostaValorUnitarioNormal(Double respostaValorUnitarioNormal) {
		
//		//Formata o Valor no formato adequado para o JDE
//		Double valor = rc.getRespostaValorUnitario();
		String valorString = String.valueOf(respostaValorUnitarioNormal);
		valorString.replace(",", "");
		valorString.replace(".", "");
		respostaValorUnitarioNormal = Double.valueOf(valorString) * 10000;
		respostaValorUnitario = respostaValorUnitarioNormal;
		
		this.respostaValorUnitarioNormal = respostaValorUnitarioNormal;
	}
	
	@Column(name = "STATUS")
	public String getStatusDaResposta() {
		return statusDaResposta;
	}

	public void setStatusDaResposta(String statusDaResposta) {
		this.statusDaResposta = statusDaResposta;
	}
	
	@Column(name = "STATUSRETORNO")
	public String getStatusDeProcessamento() {
		return statusDeProcessamento;
	}

	public void setStatusDeProcessamento(String setStatusDeProcessamento) {
		this.statusDeProcessamento = setStatusDeProcessamento;
	}
	
	@Override
	public int hashCode() {
		final Integer prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((respostaCotacaoID == null) ? 0 : respostaCotacaoID
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaCotacao other = (RespostaCotacao) obj;
		if (respostaCotacaoID == null) {
			if (other.respostaCotacaoID != null)
				return false;
		} else if (!respostaCotacaoID.equals(other.respostaCotacaoID))
			return false;
		return true;
	}
	
}
