package br.ufc.quixada.es.buscas;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

public class Consulta2 {
	
	static String consulta = 
			"<municipios>{for $geoname in doc('http://api.geonames.org/children?geonameId=3402362&amp;username=leticiamara')/geonames/geoname , " + 

			"$municipio in doc('http://api.tcm.ce.gov.br/sim/1_0/municipios.xml')/rsp/municipios " +

			"where $geoname/upper-case(toponymName) = $municipio/nome_municipio "+

			"return <lat_lon_municipio> {$geoname/lat} {$geoname/lng} {$municipio/nome_municipio} </lat_lon_municipio>}</municipios>";
		
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
