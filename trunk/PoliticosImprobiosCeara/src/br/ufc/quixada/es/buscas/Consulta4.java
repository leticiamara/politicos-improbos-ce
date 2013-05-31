package br.ufc.quixada.es.buscas;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class Consulta4 {
	
	//Retorna o nome do municipio do gestor impróbio por ano
	static String consulta = 
			"for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+

			"where $gestor/exercicio='2002' and $gestor/nota_improbidade = 'X' "+

			"return data($gestor/municipio)";
		
		public static void main(String[] args) throws XQException {
			XQDataSource ds = new SaxonXQDataSource();
	        XQConnection conexao = ds.getConnection();
			
			XQExpression expressao;
			XQSequence sequencia;
			expressao = conexao.createExpression();
			sequencia = expressao.executeQuery(consulta);
	        
			while (sequencia.next()) {
	        	System.out.println("item = " + sequencia.getItem().getItemAsString(null));
	        }
		}

}
