package br.net.enovasys.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe chave composta da tabela F4330
 * 
 * @author alexlirio
 * 
 */
@Embeddable
public class CotacaoFornecedorID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer fornecedorID;
	private String ciaID;
	private Integer cotacaoID;
	private String cotacaoTipo;
	private double cotacaoLinha;
	private String cotacaoSufixo;

	/**
	 * Construtores
	 */
	public CotacaoFornecedorID() {
		super();
	}

	public CotacaoFornecedorID(Integer fornecedorID, String ciaID,
			Integer cotacaoID, String cotacaoTipo, double cotacaoLinha,
			String cotacaoSufixo) {
		super();
		this.fornecedorID = fornecedorID;
		this.ciaID = ciaID;
		this.cotacaoID = cotacaoID;
		this.cotacaoTipo = cotacaoTipo;
		this.cotacaoLinha = cotacaoLinha;
		this.cotacaoSufixo = cotacaoSufixo;
	}

	/**
	 * Getters and Setters
	 */
	@Column(name = "AN8", nullable = false)
	public Integer getFornecedorID() {
		return fornecedorID;
	}

	public void setFornecedorID(Integer fornecedorID) {
		this.fornecedorID = fornecedorID;
	}
	
	@Column(name = "KCOO", nullable = false)
	public String getCiaID() {
		return ciaID;
	}

	public void setCiaID(String ciaID) {
		this.ciaID = ciaID;
	}

	@Column(name = "DOCO", nullable = false)
	public Integer getCotacaoID() {
		return cotacaoID;
	}

	public void setCotacaoID(Integer cotacaoID) {
		this.cotacaoID = cotacaoID;
	}

	@Column(name = "DCTO", nullable = false)
	public String getCotacaoTipo() {
		return cotacaoTipo;
	}

	public void setCotacaoTipo(String cotacaoTipo) {
		this.cotacaoTipo = cotacaoTipo;
	}
	
	@Column(name = "LNID", nullable = false)
	public double getCotacaoLinha() {
		return cotacaoLinha;
	}

	public void setCotacaoLinha(double cotacaoLinha) {
		this.cotacaoLinha = cotacaoLinha;
	}

	@Column(name = "SFXO", nullable = false)
	public String getCotacaoSufixo() {
		return cotacaoSufixo;
	}

	public void setCotacaoSufixo(String cotacaoSufixo) {
		this.cotacaoSufixo = cotacaoSufixo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciaID == null) ? 0 : ciaID.hashCode());
		result = prime * result + cotacaoID;
		long temp;
		temp = Double.doubleToLongBits(cotacaoLinha);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((cotacaoSufixo == null) ? 0 : cotacaoSufixo.hashCode());
		result = prime * result
				+ ((cotacaoTipo == null) ? 0 : cotacaoTipo.hashCode());
		result = prime * result + fornecedorID;
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
		CotacaoFornecedorID other = (CotacaoFornecedorID) obj;
		if (ciaID == null) {
			if (other.ciaID != null)
				return false;
		} else if (!ciaID.equals(other.ciaID))
			return false;
		if (cotacaoID != other.cotacaoID)
			return false;
		if (Double.doubleToLongBits(cotacaoLinha) != Double
				.doubleToLongBits(other.cotacaoLinha))
			return false;
		if (cotacaoSufixo == null) {
			if (other.cotacaoSufixo != null)
				return false;
		} else if (!cotacaoSufixo.equals(other.cotacaoSufixo))
			return false;
		if (cotacaoTipo == null) {
			if (other.cotacaoTipo != null)
				return false;
		} else if (!cotacaoTipo.equals(other.cotacaoTipo))
			return false;
		if (fornecedorID != other.fornecedorID)
			return false;
		return true;
	}

}
