package Mapa;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polygon;
import com.teamdev.jxmaps.swing.MapView;

public class Mapa2 extends MapView
{
	/**
	 * Serial version Mapa
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo creacion del mapa
	 */
	private Map map;
	
	/**
	 * Metodo constructor del mapa
	 * @param pNombre Nombre del mapa
	 */
	public Mapa2(String pNombre)
	{
		JFrame frame = new JFrame(pNombre);
		setOnMapReadyHandler(new MapReadyHandler() {
			
			@Override
			public void onMapReady(MapStatus status)
			{
				if(status == MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlOptions);
					
					map.setOptions(mapOptions);
					map.setCenter(new LatLng(4.6097102, -74.081749));
					map.setZoom(11.0);
					pintarCirculosYPoligon(4.597714, 4.621360, -74.094723, -74.062707);
				}
			}	
		});
		
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}
	
	/**
	 * Graficar arco entre dos verices dados en la guia
	 * @param latMin Latitud Minima
	 * @param latMax Latitud Maxima
	 * @param lonMin Longitud Minima
	 * @param lonMax Longitud Maxima
	 */
	public void pintarCirculosYPoligon(double latMin, double latMax, double lonMin, double lonMax)
	{
		LatLng[] arco1 = new LatLng[2];
		LatLng[] arco2 = new LatLng[2];
		LatLng[] arco3 = new LatLng[2];
		LatLng[] arco4 = new LatLng[2];
		LatLng vertice1 = new LatLng(latMin,lonMin);
		LatLng vertice2 = new LatLng(latMax,lonMax);
		LatLng vertice3 = new LatLng(latMin,lonMax);
		LatLng vertice4 = new LatLng(latMax,lonMin);
		LatLng vertice5 = new LatLng(4.60894345,-74.08880157);
		LatLng vertice6 = new LatLng(4.6153082,-74.06631182);
		arco1[0] = vertice1;
		arco1[1] = vertice3;
		arco2[0] = vertice2;
		arco2[1] = vertice4;
		arco3[0] = vertice2;
		arco3[1] = vertice3;
		arco4[0] = vertice1;
		arco4[1] = vertice4;
		
		Circle ver1 = new Circle(map);
		ver1.setCenter(vertice1);
		ver1.setRadius(10.0);
		CircleOptions op1 = new CircleOptions();
		op1.setFillColor("#ff0000");
		ver1.setOptions(op1);
		
		Circle ver2 = new Circle(map);
		ver2.setCenter(vertice2);
		ver2.setRadius(10.0);
		CircleOptions op2 = new CircleOptions();
		op2.setFillColor("#ff0000");
		ver2.setOptions(op2);
		
		Circle ver3 = new Circle(map);
		ver3.setCenter(vertice3);
		ver3.setRadius(10.0);
		CircleOptions op3 = new CircleOptions();
		op3.setFillColor("#ff0000");
		ver3.setOptions(op3);
		
		Circle ver4 = new Circle(map);
		ver4.setCenter(vertice4);
		ver4.setRadius(10.0);
		CircleOptions op4 = new CircleOptions();
		op4.setFillColor("#ff0000");
		ver4.setOptions(op4);
		
		Circle ver5 = new Circle(map);
		ver5.setCenter(vertice5);
		ver5.setRadius(20.0);
		CircleOptions op5 = new CircleOptions();
		op5.setFillColor("#ff0000");
		ver5.setOptions(op5);
		
		Circle ver6 = new Circle(map);
		ver6.setCenter(vertice6);
		ver6.setRadius(20.0);
		CircleOptions op6 = new CircleOptions();
		op6.setFillColor("#ff0000");
		ver6.setOptions(op6);
		
		Polygon union1 = new Polygon(map);
		union1.setPath(arco1);
		
		Polygon union2 = new Polygon(map);
		union2.setPath(arco2);
		
		Polygon union3 = new Polygon(map);
		union3.setPath(arco3);
		
		Polygon union4 = new Polygon(map);
		union4.setPath(arco4);
	}
}
