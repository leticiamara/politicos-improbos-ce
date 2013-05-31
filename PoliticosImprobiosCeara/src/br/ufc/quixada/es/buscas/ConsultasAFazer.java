package br.ufc.quixada.es.buscas;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class ConsultasAFazer {
	
	public List<String> retornarLatitudeLongitudeTodosMunicipios() throws XQException{
		String consulta = 
				"<municipios>{for $geoname in doc('http://api.geonames.org/children?geonameId=3402362&amp;username=leticiamara')/geonames/geoname , " + 
				"$municipio in doc('http://api.tcm.ce.gov.br/sim/1_0/municipios.xml')/rsp/municipios " +
				"where $geoname/upper-case(toponymName) = $municipio/nome_municipio "+
				"return <lat_lon_municipio> {$geoname/lat} {$geoname/lng} {$municipio/nome_municipio} </lat_lon_municipio>}</municipios>";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		List<String> listaDeResultadosConsulta = new ArrayList<String>();
		
		while (sequencia.next()) {
        	listaDeResultadosConsulta.add(sequencia.getItem().getItemAsString(null));
        }
		
		return listaDeResultadosConsulta;
	}
	
	public List<String> retornarListaDeMunicipiosPossuemGestoresImprobios() throws XQException{
		
		String consulta = 
				"for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores " +
						"where $gestor/nota_improbidade = 'X' return data($gestor/municipio)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		List<String> listaDeResultadosConsulta = new ArrayList<String>();
		
		while (sequencia.next()) {
        	listaDeResultadosConsulta.add(sequencia.getItem().getItemAsString(null));
        }
		
		return listaDeResultadosConsulta;
	}
	
	public List<String> retornarNomeMunicipioDoGestorImpróbioPorAno(String ano) throws XQException{
		
		String consulta = 
				"for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+

				"where $gestor/exercicio='"+ ano +"' and $gestor/nota_improbidade = 'X' "+

				"return data($gestor/municipio)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		List<String> listaDeResultadosConsulta = new ArrayList<String>();
		
		while (sequencia.next()) {
        	listaDeResultadosConsulta.add(sequencia.getItem().getItemAsString(null));
        }
		
		return listaDeResultadosConsulta;
	}
	
	public List<String> retornarDadosDoGestorPorMunicipio(String municipio) throws XQException{
		
		String consulta = 
				"for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+

				"where $gestor/municipio = '"+municipio+"' and $gestor/nota_improbidade = 'X' "+

				"return <gestor> {$gestor/gestor} {$gestor/natureza_processo} {$gestor/exercicio} </gestor>";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		List<String> listaDeResultadosConsulta = new ArrayList<String>();
		
		while (sequencia.next()) {
        	listaDeResultadosConsulta.add(sequencia.getItem().getItemAsString(null));
        }
		
		return listaDeResultadosConsulta;
	}

	public String retornarContagemGestoresImprobiosPorMunicipio(String municipio) throws XQException{
		
		String consulta = 
				"count(for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+
				"where 	$gestor/municipio='"+ municipio +"' and $gestor/nota_improbidade = 'X' "+
				"return $gestor)";		
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		sequencia.next();
		
		return sequencia.getItem().getItemAsString(null);
	}
	
	public String retornarContagemGestoresImprobiosPorAno(String ano) throws XQException{
		
		String consulta = 
				"count(for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+
				"where $gestor/exercicio='"+ ano +"' and $gestor/nota_improbidade = 'X' "+
				"return $gestor)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		sequencia.next();
		
		return sequencia.getItem().getItemAsString(null);
	}
	
	public String retornarContagemTotalGestoresImprobiosDoCeara() throws XQException{
		
		String consulta = 
				"count(for $gestor in doc('http://api.tcm.ce.gov.br/tre/1_0/processos_gestores.xml')/rsp/processos_gestores "+
				"where $gestor/nota_improbidade = 'X' "+
				"return $gestor)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		sequencia.next();
		
		return sequencia.getItem().getItemAsString(null);
	}
	
	public String retornarLatitudeMunicipio(String municipio) throws XQException {
		String consulta = "for $geoname in doc('http://api.geonames.org/children?geonameId=3402362&amp;username=leticiamara')/geonames/geoname "+
				"where $geoname/upper-case(toponymName) = '"+municipio+"' return data($geoname/lat)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		sequencia.next();
		
		return sequencia.getItem().getItemAsString(null);
	}

	public String retornarLongitudeMunicipio(String municipio) throws XQException {
		String consulta = "for $geoname in doc('http://api.geonames.org/children?geonameId=3402362&amp;username=leticiamara')/geonames/geoname "+
				"where $geoname/upper-case(toponymName) = '"+municipio+"' return data($geoname/lng)";
		
		XQDataSource ds = new SaxonXQDataSource();
        XQConnection conexao = ds.getConnection();
		
		XQExpression expressao;
		XQSequence sequencia;
		expressao = conexao.createExpression();
		sequencia = expressao.executeQuery(consulta);
		
		sequencia.next();
		
		return sequencia.getItem().getItemAsString(null);
	}
}
