package br.net.enovasys.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe chave composta da tabela F4311
 * 
 * @author alexlirio
 * 
 */
@Embeddable
public class CotacaoID implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ciaID;
	private Integer cotacaoID;
	private String cotacaoTipo;
	private Double cotacaoLinha;
	private String cotacaoSufixo;

	/**
	 * Construtor
	 */
	public CotacaoID() {
		super();
	}

	public CotacaoID(String ciaID, Integer cotacaoID, String cotacaoTipo,
			Double cotacaoLinha, String cotacaoSufixo) {
		super();
		this.ciaID = ciaID;
		this.cotacaoID = cotacaoID;
		this.cotacaoTipo = cotacaoTipo;
		this.cotacaoLinha = cotacaoLinha;
		this.cotacaoSufixo = cotacaoSufixo;
	}

	/**
	 * Getters and Setters
	 */
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
	public Double getCotacaoLinha() {
		return cotacaoLinha;
	}

	public void setCotacaoLinha(Double cotacaoLinha) {
		this.cotacaoLinha = cotacaoLinha;
	}

	@Column(name = "SFXO", nullable = false)
	public String getFornecedorID() {
		return cotacaoSufixo;
	}

	public void setFornecedorID(String cotacaoSufixo) {
		this.cotacaoSufixo = cotacaoSufixo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciaID == null) ? 0 : ciaID.hashCode());
		result = prime * result
				+ ((cotacaoID == null) ? 0 : cotacaoID.hashCode());
		result = prime * result
				+ ((cotacaoLinha == null) ? 0 : cotacaoLinha.hashCode());
		result = prime * result
				+ ((cotacaoSufixo == null) ? 0 : cotacaoSufixo.hashCode());
		result = prime * result
				+ ((cotacaoTipo == null) ? 0 : cotacaoTipo.hashCode());
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
		CotacaoID other = (CotacaoID) obj;
		if (ciaID == null) {
			if (other.ciaID != null)
				return false;
		} else if (!ciaID.equals(other.ciaID))
			return false;
		if (cotacaoID == null) {
			if (other.cotacaoID != null)
				return false;
		} else if (!cotacaoID.equals(other.cotacaoID))
			return false;
		if (cotacaoLinha == null) {
			if (other.cotacaoLinha != null)
				return false;
		} else if (!cotacaoLinha.equals(other.cotacaoLinha))
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
		return true;
	}

}
