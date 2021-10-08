package br.net.enovasys.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.net.enovasys.modelo.RespostaCotacao;

/**
 * Classe DAO especifica, responsavel pelos metodos de persistencia da classe
 * RespostaCotacao.
 * 
 * @author alexlirio
 * 
 */
public class RespostaCotacaoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SessionFactoryBuilder sessionFactory;

	private static final String COTACAO_ID = "cotacaoID";
	private static final String FORNECEDOR_ID = "fornecedorID";
	private static final String DATA_ATUAL = "dataAtual";

	/**
	 * Construtor
	 */
	public RespostaCotacaoDAO() {
		super();
	}
	
	/**
	 * Metodo para buscar as RespostasDasCotacoesPrincipais
	 * 
	 * @param cotacaoID, fornecedorID
	 * @return List<RespostaCotacao>
	 */
	@SuppressWarnings("unchecked")
	public List<RespostaCotacao> buscarRespostas(String cotacaoID,
			String fornecedorID, Integer dataAtual) {

		sessionFactory = SessionFactoryBuilder.getNewSessionFactoryBuilder();
		sessionFactory.beginTransaction();
		Session session = sessionFactory.getSession();

		Query q = session.getNamedQuery("buscarRespostasDasCotacoesPrincipais");
		q.setInteger(COTACAO_ID, Integer.parseInt(cotacaoID));
		q.setInteger(FORNECEDOR_ID, Integer.parseInt(fornecedorID));
		q.setInteger(DATA_ATUAL, dataAtual);

		List<RespostaCotacao> resultado = new ArrayList<RespostaCotacao>();

		try {
			resultado = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}
	
	/**
	 * Metodo para salvar as alteracoes da Resposta Cotacao da view
	 * 
	 */
	public void update(RespostaCotacao rc){
		sessionFactory = SessionFactoryBuilder.getNewSessionFactoryBuilder();
		sessionFactory.beginTransaction();
		Session session = sessionFactory.getSession();
		
		try{
			rc.setStatusDaResposta("R");
			rc.setStatusDeProcessamento("0");
			session.update(rc);
			sessionFactory.commit();
			
		} catch (Exception e) {
			sessionFactory.rollback();
		}
		
		if(sessionFactory.isSessionOpened() || sessionFactory.isTransactionOpened()){
			session.close();
		}
	}
	
	/**
	 * Metodo para realizar o recarregamento de um objeto e todas as suas colecoes.
	 * 
	 * @param rc
	 */
	public void refresh(RespostaCotacao rc){
		sessionFactory = SessionFactoryBuilder.getNewSessionFactoryBuilder();
		sessionFactory.beginTransaction();
		Session session = sessionFactory.getSession();
		
		try{
			session.refresh(rc);
			sessionFactory.commit();
		} catch (Exception e) {
			sessionFactory.rollback();
		}
		
		if(sessionFactory.isSessionOpened() || sessionFactory.isTransactionOpened()){
			session.close();
		}
	}
	

	/**
	 * Metodo responsavel por finalizar a sessão e setar os 'Status das Respostas' de 'Rascunho (R)' para 'Finalizado (F)'
	 * @param cotacaoID
	 * @param fornecedorID
	 */
	public void finalizar(String cotacaoID, String fornecedorID) {
		sessionFactory = SessionFactoryBuilder.getNewSessionFactoryBuilder();
		sessionFactory.beginTransaction();
		Session session = sessionFactory.getSession();
		
		try {
			session.createQuery("update RespostaCotacao rc set rc.statusDaResposta='F'" +
				" where rc.respostaCotacaoID.cotacaoID=:cotacaoID" +
				" and rc.respostaCotacaoID.fornecedorID=:fornecedorID" +
				" and rc.statusDaResposta='R'")
				.setInteger("cotacaoID", Integer.parseInt(cotacaoID))
				.setInteger("fornecedorID", Integer.parseInt(fornecedorID)).executeUpdate();
			
			sessionFactory.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(sessionFactory.isSessionOpened() || sessionFactory.isTransactionOpened()){
			session.close();
		}
		
	}

}
