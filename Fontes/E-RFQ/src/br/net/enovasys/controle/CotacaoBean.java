package br.net.enovasys.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import br.net.enovasys.config.ConverteData;
import br.net.enovasys.dao.RespostaCotacaoDAO;
import br.net.enovasys.modelo.RespostaCotacao;

/**
 * Classe Bean cotacao.
 * 
 * @author alexlirio
 * 
 */
@ManagedBean(name = "cotacaoBean")
@SessionScoped
public class CotacaoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Parametro passado pela reescrita de URL. Contem a cotacaoID e o fornecedorID juntos.
	private String l337cr1p7;
	
	private String cotacaoID;
	private String fornecedorID;
	private String fornecedorNome;
	private RespostaCotacao respostaCotacao;
	private RespostaCotacao respostaCotacaoSelecionado;
	private List<RespostaCotacao> respostas = new ArrayList<RespostaCotacao>();
	
	//Usado para controlar a Data Minima
	private Date minDate;

	/**
	 * Construtor
	 */
	public CotacaoBean() {
		super();
	}
	
	/**
	 * Getters and Setters
	 */
	public String getL337cr1p7() {
		return l337cr1p7;
	}
	
	/**
	 * Metodo SET usado para descriptografar o "l337cr1p7"
	 * da Reescrita de URL
	 * 
	 * @param fornecedorIDCriptografado
	 */
	public void setL337cr1p7(String l337cr1p7) {
		ROT18 rot18 = new ROT18();
		String descriptografado = rot18.descriptografa(l337cr1p7);
		String cotacaoIDDesc = descriptografado.substring(0, 8);
		String fornecedorIDDesc = descriptografado.substring(8, 13);
		
		setCotacaoID(cotacaoIDDesc);
		setFornecedorID(fornecedorIDDesc);

		this.l337cr1p7 = descriptografado;

	}
	
	public String getCotacaoID() {
		return cotacaoID;
	}

	public void setCotacaoID(String cotacaoID) {
		this.cotacaoID = cotacaoID;
	}

	public String getFornecedorID() {
		return fornecedorID;
	}

	public void setFornecedorID(String fornecedorID) {
		this.fornecedorID = fornecedorID;
	}

	public String getFornecedorNome() {
		if (fornecedorNome == null || fornecedorNome == "") {
			fornecedorNome = buscarRespostas().iterator().next().getCotacaoFornecedor().getFornecedor().getFornecedorNome();
			return fornecedorNome;
		} else {
			return fornecedorNome;
		}

	}

	public void setFornecedorNome(String fornecedorNome) {
		this.fornecedorNome = fornecedorNome;
	}

	public RespostaCotacao getRespostaCotacao() {
		return respostaCotacao;
	}

	public void setRespostaCotacao(RespostaCotacao respostaCotacao) {
		this.respostaCotacao = respostaCotacao;
	}
	
	public RespostaCotacao getRespostaCotacaoSelecionado() {
		return respostaCotacaoSelecionado;
	}

	public void setRespostaCotacaoSelecionado(RespostaCotacao respostaCotacaoSelecionado) {
		this.respostaCotacaoSelecionado = respostaCotacaoSelecionado;
	}

	public List<RespostaCotacao> getRespostas() {
		
		if(this.respostas == null || this.respostas == new ArrayList<RespostaCotacao>()){
			buscarRespostas();
		}
		
		return respostas;
	}

	public void setRespostas(List<RespostaCotacao> respostas) {
		this.respostas = respostas;
	}

	public Date getMinDate() {
		minDate = new Date();
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	
	/**
	 * Metodo para buscar as respostas
	 * 
	 * @return List<RespostaCotacao>
	 */
	public List<RespostaCotacao> buscarRespostas() {
		RespostaCotacaoDAO respostaCotacaoDAO = new RespostaCotacaoDAO();

		try {
			if(respostas == null || respostas.isEmpty()){
				ConverteData converteData = new ConverteData();
				Integer dataAtual = converteData.toJulianDate(new Date());
				
				// if criado para bloquear acesso caso retorne NULL na Lista principal. 
				if (respostaCotacaoDAO.buscarRespostas(cotacaoID, fornecedorID, dataAtual).size() == 0) {
					finalizar();
				} else {
					// Linha abaixo e mantida se necessario retirar o 'if else'
					respostas = respostaCotacaoDAO.buscarRespostas(cotacaoID, fornecedorID, dataAtual);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return this.respostas;
	}

	/**
	 * Captura o objeto editado pelo "rowEdit" e atualiza o banco com os novos dados. Tambem usado para validar a data de entrega.
	 * 
	 * @param e
	 */
	public void onEditRow(RowEditEvent e){
		RespostaCotacao rc = (RespostaCotacao)e.getObject();
		respostaCotacao = rc;
		Date dataAtual = new Date();
		Date dataLimDate = new ConverteData().toDate(rc.getCotacaoFornecedor().getRespostaDataLimite());
		
		Date dataInserida = new ConverteData().toDate(rc.getRespostaDataEntrega());
		RespostaCotacaoDAO rcDao = new RespostaCotacaoDAO();
		
		if (dataInserida != null) {
			
			if ((dataInserida.getTime() >= dataAtual.getTime()) && (dataInserida.getTime() <= dataLimDate.getTime()) && rc.getRespostaValorUnitario() >= 0.0) {
	       		try {
	       			rcDao.update(rc);
	       			FacesContext context = FacesContext.getCurrentInstance();  
		   	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", "Additional Info Here..."));
	       		} catch (Exception e2) {
	       			e2.printStackTrace();
	       		}
	    		
	    	} else {
	       		try {
	       			
		   			if (rc.getRespostaValorUnitario() < 0.0) {
			   			FacesContext context = FacesContext.getCurrentInstance();  
			   	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Inválido!", "Additional Info Here..."));
					}

		   			if ((dataInserida.getTime() < dataAtual.getTime()) || (dataInserida.getTime() > dataLimDate.getTime())) {
			   			FacesContext context = FacesContext.getCurrentInstance();  
			   	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Inválida!", "Additional Info Here..."));
					}

		   			rcDao.refresh(rc);
		   	        
		   		} catch (Exception e2) {
		   			e2.printStackTrace();
		   		}
			}
			
		} else {
			
			if (rc.getRespostaValorUnitario() >= 0.0) {
	       		try {
	       			rc.setRespostaDataEntrega(null);
	       			rcDao.update(rc);
	       			FacesContext context = FacesContext.getCurrentInstance();  
		   	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", "Additional Info Here..."));
	       		} catch (Exception e2) {
	       			e2.printStackTrace();
	       		}
	    		
	    	} else {
	       		try {
	       			
		   			if (rc.getRespostaValorUnitario() < 0.0) {
			   			FacesContext context = FacesContext.getCurrentInstance();  
			   	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Inv�lido!", "Additional Info Here..."));
					}
		   			rcDao.refresh(rc);
		   	        
		   		} catch (Exception e2) {
		   			e2.printStackTrace();
		   		}
			
			System.out.println("------------->>>>>P" + dataInserida);
		}
		}
    	
	}
    
	/**
	 * Metodo usado setar as respostas com Status Finalizado(F) e para encerrar a Session
	 * 
	 * @return String
	 */
    public String finalizar() {

    	RespostaCotacaoDAO rcDAO = new RespostaCotacaoDAO();
    	
    	// Descriptografa cotacaoID e fornecedorID para o metodo 'rcDAO.finalizar(cotacaoIDDesc, fornecedorIDDesc)'
      	ROT18 rot18 = new ROT18();
    	String criptografado = rot18.descriptografa(cotacaoID+fornecedorID);
    	
		String s1 = criptografado.substring(0, 3);
		String s2 = criptografado.substring(3, 5);
		String s3 = criptografado.substring(5, 8);
		String s4 = criptografado.substring(8, 10);
		String s5 = criptografado.substring(10, 13);
		
		String cotacaoIDDesc = s3.concat(s2.concat(s5));
		String fornecedorIDDesc = s4.concat(s1);
    	
    	rcDAO.finalizar(cotacaoIDDesc, fornecedorIDDesc);
    	
    	String finalizar = "finalizar";
    	FacesContext fc = FacesContext.getCurrentInstance();
    	HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    	session.invalidate();
    	
        return finalizar;  
    }

}
