package br.ufc.quixada.es.mapas;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.xml.xquery.XQException;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.ufc.quixada.es.buscas.ConsultasAFazer;

@ManagedBean(name="mapaBean")
public class MapaBean implements Serializable {  
    
    private MapModel advancedModel;  
  
    private Marker marker;  
  
    public MapaBean() throws XQException {  
        advancedModel = new DefaultMapModel();  
        
        ConsultasAFazer consultas = new ConsultasAFazer();
        List<String> nomesMunicipios = consultas.retornarListaDeMunicipiosPossuemGestoresImprobios();
        
        System.out.println("Listou municipios");
        String nomeAnterior=nomesMunicipios.get(0);
        String nomeProximo="";
        for (int i=0 ; i<2;i++) {
        	System.out.println(""+i);
        	nomeAnterior = nomesMunicipios.get(i);
        	if(nomeAnterior.equals(nomeProximo)){
        		
        	}else{
        		nomeProximo=nomeAnterior;
			String lat = consultas.retornarLatitudeMunicipio(nomesMunicipios.get(i));
			System.out.println("Contou latitude");
			String lng = consultas.retornarLongitudeMunicipio(nomesMunicipios.get(i));
			System.out.println("Contou longitude");
			
			double latitude = Double.parseDouble(lat);
			double longitude = Double.parseDouble(lng);
			
			String totalGestoresImprobios = consultas.retornarContagemGestoresImprobiosPorMunicipio(nomesMunicipios.get(i));
			System.out.println("Contou todos");
			Integer totalImprobios = Integer.parseInt(totalGestoresImprobios);
			System.out.println(totalImprobios);
			if(totalImprobios < 10){
				LatLng coord1 = new LatLng(latitude,longitude);			
				advancedModel.addOverlay(new Marker(coord1, ""+nomesMunicipios.get(i),"","http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
			}
			if(totalImprobios>11 && totalImprobios <30){
				LatLng coord1 = new LatLng(latitude,longitude);			
				advancedModel.addOverlay(new Marker(coord1, ""+nomesMunicipios.get(i),"","http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
			}
			if(totalImprobios>31){
				LatLng coord1 = new LatLng(latitude,longitude);			
				advancedModel.addOverlay(new Marker(coord1, ""+nomesMunicipios.get(i),"","http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
			}}}
          
//        //Shared coordinates  
//        LatLng coord1 = new LatLng(36.879466, 30.667648);  
//        LatLng coord2 = new LatLng(36.883707, 30.689216);  
//        LatLng coord3 = new LatLng(36.879703, 30.706707);  
//        LatLng coord4 = new LatLng(36.885233, 30.702323);  
//          
//        //Icons and Data  
//        advancedModel.addOverlay(new Marker(coord1, "Konyaalti", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));  
//        advancedModel.addOverlay(new Marker(coord2, "Ataturk Parki", "ataturkparki.png"));  
//        advancedModel.addOverlay(new Marker(coord4, "Kaleici", "kaleici.png", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));  
//        advancedModel.addOverlay(new Marker(coord3, "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));  
    }  
  
    public MapModel getAdvancedModel() {  
        return advancedModel;  
    }  
      
    public void onMarkerSelect(OverlaySelectEvent event) {  
        marker = (Marker) event.getOverlay();  
    }  
      
    public Marker getMarker() {  
        return marker;  
    }  
}  
