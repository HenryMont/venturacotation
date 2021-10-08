package br.net.enovasys.config;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

//import br.net.enovasys.modelo.Cotacao;
//import br.net.enovasys.modelo.CotacaoID;

/**
 * Classe usada para gerar o esquema do Banco de Dados utilizando o
 * hibernate.cfg.xml.
 * 
 * @author alexlirio
 * 
 */
public class MainHibernate {

	public static void main(String[] args) {

		new SchemaExport(new Configuration().configure("/hibernate.cfg.xml")).create(true, true);
		
//		// Usado apenas para teste
		// SessionFactory factory = new
		// Configuration().configure().buildSessionFactory();
		//
		// Session sessao = factory.openSession();
		// Transaction transaction = sessao.beginTransaction();
		//
		// transaction.begin();
		//
		// CotacaoID cotacaoID = new CotacaoID();
		// cotacaoID.setCiaID("AAA");
		// cotacaoID.setCotacaoID(1);
		// cotacaoID.setCotacaoLinha(1d);
		// cotacaoID.setCotacaoSufixo("AAA");
		// cotacaoID.setCotacaoTipo("AAA");
		//
		// Cotacao cotacao = new Cotacao();
		// cotacao.setProdutoDescricao1("ASD");
		// cotacao.setCotacaoID(cotacaoID);
		//
		// sessao.save(cotacao);
		//
		// transaction.commit();
		// sessao.close();
	}
}
