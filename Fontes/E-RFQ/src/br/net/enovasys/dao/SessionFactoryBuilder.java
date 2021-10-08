package br.net.enovasys.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Classe responsavel pela Sessao. Fabrica de Sessao.
 * 
 * @author alexlirio
 * 
 */
public class SessionFactoryBuilder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SessionFactory factory;
	private Session sessao;
	private Transaction transaction;

	/**
	 * Contrutor
	 */
	private SessionFactoryBuilder() {
		factory= new AnnotationConfiguration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//factory = new Configuration().configure().buildSessionFactory();
	}

	/**
	 * Metodo que retorna uma SessionFactoryBuilder.
	 * 
	 * @return SessionFactoryBuilder
	 */
	public static SessionFactoryBuilder getNewSessionFactoryBuilder() {
		return new SessionFactoryBuilder();
	}

	/**
	 * Metodo que inicia uma Transacao.
	 */
	public void beginTransaction() {
		sessao = factory.openSession();
		transaction = sessao.beginTransaction();
		
		transaction.begin();
	}

	/**
	 * Metodo que retorna a Sessao.
	 * 
	 * @return Sessao
	 */
	public Session getSession() {
		return sessao;
	}

	/**
	 * Metodo que 'commita' o objeto no banco.
	 */
	public void commit() {
		transaction.commit();
		sessao.flush();
	}

	/**
	 * Metodo que retorna, nao 'commita' no banco.
	 */
	public void rollback() {
		transaction.rollback();
	}

	/**
	 * Metodo que fecha a Transacao
	 */
	public void closeTransaction() {
		sessao.close();
	}

	/**
	 * Metodo que verifica se a Transacao esta aberta.
	 * 
	 * @return boolean
	 */
	public boolean isTransactionOpened() {
		return transaction.isActive();
	}

	/**
	 * Metodo que verifica se a Sessao esta aberta.
	 * 
	 * @return boolean
	 */
	public boolean isSessionOpened() {
		return sessao.isOpen();
	}
}
