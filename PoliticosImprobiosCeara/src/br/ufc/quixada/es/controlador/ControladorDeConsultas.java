package br.ufc.quixada.es.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.xquery.XQException;

import br.ufc.quixada.es.buscas.ConsultasAFazer;
import br.ufc.quixada.es.modelo.Gestor;

@ManagedBean(name="controladorConsultas")
@SessionScoped
public class ControladorDeConsultas {

	private Gestor gestor = new Gestor();
	private ConsultasAFazer consulta = new ConsultasAFazer();
	private List<String> listaDeResultadosdaConsulta = new ArrayList<String>();
	private String resultadoConsulta;

	public void retornarLatLngMunicipios() throws XQException{
		listaDeResultadosdaConsulta = consulta.retornarLatitudeLongitudeTodosMunicipios();
	}
	
	public void retornarGestoresImprobiosMunicipios() throws XQException{
		listaDeResultadosdaConsulta = consulta.retornarListaDeMunicipiosPossuemGestoresImprobios();
	}
	
	public void retornarMunicipiosGestoresImprobiosPorAno() throws XQException{
		listaDeResultadosdaConsulta = consulta.retornarNomeMunicipioDoGestorImpróbioPorAno(gestor.getAno());
	}
	
	public void retornarDadosGestorMunicipios() throws XQException{
		listaDeResultadosdaConsulta = consulta.retornarDadosDoGestorPorMunicipio(gestor.getMunicipio());
	}
	
	public void retornarNumeroGestoresImprobiosMunicipio() throws XQException{
		resultadoConsulta = consulta.retornarContagemGestoresImprobiosPorMunicipio(gestor.getMunicipio());
	}
	
	public void retornarNumeroGestoresImprobiosCeara() throws XQException{
		resultadoConsulta = consulta.retornarContagemTotalGestoresImprobiosDoCeara();
	}
	
	public void retornarNumeroGestoresImprobiosPorAno() throws XQException {
		resultadoConsulta = consulta.retornarContagemGestoresImprobiosPorAno(gestor.getAno());
	}
	
	public Gestor getGestor() {
		return gestor;
	}
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
	public ConsultasAFazer getConsulta() {
		return consulta;
	}
	public void setConsulta(ConsultasAFazer consulta) {
		this.consulta = consulta;
	}
	
	public List<String> getListaDeResultadosdaConsulta() {
		return listaDeResultadosdaConsulta;
	}
	
	public void setListaDeResultadosdaConsulta(
			List<String> listaDeResultadosdaConsulta) {
		this.listaDeResultadosdaConsulta = listaDeResultadosdaConsulta;
	}
	
	
}
