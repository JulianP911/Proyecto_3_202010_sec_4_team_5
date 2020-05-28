package Mapa;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.swing.MapView;

import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.Edge;
import model.data_structures.SeparteChainingHashST;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;

import com.teamdev.jxmaps.*;

public class Mapa4 extends MapView
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
	public Mapa4(UnGraph<String, InformacionVertice, InformacionArco> grafo,String pNombre)
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
					pintarCirculosYPoligon();
					pintarTremendoGrafo(grafo);
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
	public void pintarCirculosYPoligon()
	{
		LatLng vertice1 = new LatLng(4.5856236599999995,-74.10313064);
		LatLng vertice2 = new LatLng(4.67382811,-74.08184808);
		LatLng vertice3 = new LatLng(4.60013985,-74.18675708);
		LatLng vertice4 = new LatLng(4.57751074,-74.16459725);
		LatLng vertice5 = new LatLng(4.69008426,-74.10255664);
		LatLng vertice6 = new LatLng(4.66765744,-74.14868982);
		LatLng vertice7 = new LatLng(4.6194025,-74.15943273);
		LatLng vertice8 = new LatLng(4.60894345,-74.08880157);
		LatLng vertice9 = new LatLng(4.61790296,-74.10232964);
		LatLng vertice10 = new LatLng(4.7415167700000005,-74.08498859);
		LatLng vertice11 = new LatLng(4.62744702,-74.06682832);
		LatLng vertice12 = new LatLng(4.5860512700000005,-74.13331834);
		LatLng vertice13 = new LatLng(4.74154282,-74.02668409);
		LatLng vertice14 = new LatLng(4.501014293000026,-74.11777177499994);
		LatLng vertice15 = new LatLng(4.591392,-74.078881);
		LatLng vertice16 = new LatLng(4.64153184,-74.0585054);
		LatLng vertice17 = new LatLng(4.57002559,-74.08598615);
		LatLng vertice18 = new LatLng(4.585445,-74.110591);
		LatLng vertice19 = new LatLng(4.6949603,-74.1415317);
		LatLng vertice20 = new LatLng(4.6511591,-74.1145688);
		LatLng vertice21 = new LatLng(4.6153082,-74.06631182);

		Circle ver1 = new Circle(map);
		ver1.setCenter(vertice1);
		ver1.setRadius(250.0);
		CircleOptions op1 = new CircleOptions();
		op1.setFillColor("#ff0000");
		ver1.setOptions(op1);

		Circle ver2 = new Circle(map);
		ver2.setCenter(vertice2);
		ver2.setRadius(250.0);
		CircleOptions op2 = new CircleOptions();
		op2.setFillColor("#32CD32");
		ver2.setOptions(op2);

		Circle ver3 = new Circle(map);
		ver3.setCenter(vertice3);
		ver3.setRadius(150.0);
		CircleOptions op3 = new CircleOptions();
		op3.setFillColor("#4169E1");
		ver3.setOptions(op3);

		Circle ver4 = new Circle(map);
		ver4.setCenter(vertice4);
		ver4.setRadius(150.0);
		CircleOptions op4 = new CircleOptions();
		op4.setFillColor("#800080");
		ver4.setOptions(op4);
		
		Circle ver5 = new Circle(map);
		ver5.setCenter(vertice5);
		ver5.setRadius(200.0);
		CircleOptions op5 = new CircleOptions();
		op5.setFillColor("#800080");
		ver5.setOptions(op5);

		Circle ver6 = new Circle(map);
		ver6.setCenter(vertice6);
		ver6.setRadius(175.0);
		CircleOptions op6 = new CircleOptions();
		op6.setFillColor("#000000");
		ver6.setOptions(op6);
		
		Circle ver7 = new Circle(map);
		ver7.setCenter(vertice7);
		ver7.setRadius(100.0);
		CircleOptions op7 = new CircleOptions();
		op7.setFillColor("#800000");
		ver7.setOptions(op7);
		
		Circle ver8 = new Circle(map);
		ver8.setCenter(vertice8);
		ver8.setRadius(250.0);
		CircleOptions op8 = new CircleOptions();
		op8.setFillColor("#006400");
		ver8.setOptions(op8);
		
		Circle ver9 = new Circle(map);
		ver9.setCenter(vertice9);
		ver9.setRadius(350.0);
		CircleOptions op9 = new CircleOptions();
		op9.setFillColor("#C71585");
		ver9.setOptions(op9);
		
		Circle ver10 = new Circle(map);
		ver10.setCenter(vertice10);
		ver10.setRadius(50.0);
		CircleOptions op10 = new CircleOptions();
		op10.setFillColor("#008080");
		ver10.setOptions(op4);
		
		Circle ver11 = new Circle(map);
		ver11.setCenter(vertice11);
		ver11.setRadius(250.0);
		CircleOptions op11 = new CircleOptions();
		op11.setFillColor("#6B8E23");
		ver11.setOptions(op11);
		
		Circle ver12 = new Circle(map);
		ver12.setCenter(vertice12);
		ver12.setRadius(50.0);
		CircleOptions op12 = new CircleOptions();
		op12.setFillColor("#008B8B");
		ver12.setOptions(op12);
		
		Circle ver13 = new Circle(map);
		ver13.setCenter(vertice13);
		ver13.setRadius(50.0);
		CircleOptions op13 = new CircleOptions();
		op13.setFillColor("#FF1493");
		ver13.setOptions(op13);
		
		Circle ver14 = new Circle(map);
		ver14.setCenter(vertice14);
		ver14.setRadius(250.0);
		CircleOptions op14 = new CircleOptions();
		op14.setFillColor("#CD5C5C");
		ver14.setOptions(op14);
		
		Circle ver15 = new Circle(map);
		ver15.setCenter(vertice15);
		ver15.setRadius(200.0);
		CircleOptions op15 = new CircleOptions();
		op15.setFillColor("#FF00FF");
		ver15.setOptions(op15);
		
		Circle ver16 = new Circle(map);
		ver16.setCenter(vertice16);
		ver16.setRadius(150.0);
		CircleOptions op16 = new CircleOptions();
		op16.setFillColor("#7CFC00");
		ver16.setOptions(op16);
		
		Circle ver17 = new Circle(map);
		ver17.setCenter(vertice17);
		ver17.setRadius(150.0);
		CircleOptions op17 = new CircleOptions();
		op17.setFillColor("#BC8F8F");
		ver17.setOptions(op17);
		
		Circle ver18 = new Circle(map);
		ver18.setCenter(vertice18);
		ver18.setRadius(250.0);
		CircleOptions op18 = new CircleOptions();
		op18.setFillColor("#00CED1");
		ver18.setOptions(op18);
		
		Circle ver19 = new Circle(map);
		ver19.setCenter(vertice19);
		ver19.setRadius(75.0);
		CircleOptions op19 = new CircleOptions();
		op19.setFillColor("#FFD700");
		ver19.setOptions(op19);
		
		Circle ver20 = new Circle(map);
		ver20.setCenter(vertice20);
		ver20.setRadius(250.0);
		CircleOptions op20 = new CircleOptions();
		op20.setFillColor("#FFFF00");
		ver20.setOptions(op20);
		
		Circle ver21 = new Circle(map);
		ver21.setCenter(vertice21);
		ver21.setRadius(250.0);
		CircleOptions op21 = new CircleOptions();
		op21.setFillColor("#FFB6C1");
		ver21.setOptions(op21);
	}

	/**
	 * Pintar grafo en el mapa recibe un grafo como entrada
	 */
	public void pintarTremendoGrafo(UnGraph<String, InformacionVertice, InformacionArco>  grafoCarga)
	{
		SeparteChainingHashST<Integer, Edge<String, InformacionArco>> arcos= grafoCarga.getArcosGrafo();

		int size=arcos.size();

		int j=0;
		while(j<size)
		{
			Edge<String, InformacionArco> rev= arcos.get(j);
			String inicio = rev.getIdVerticeInicio();
			String fin = rev.getIdVerticeFinal();

			Vertex<String, InformacionVertice, InformacionArco> uno = grafoCarga.getInfoVertexV(inicio);
			Vertex<String, InformacionVertice, InformacionArco> dos = grafoCarga.getInfoVertexV(fin);


			LatLng[] arcs=new LatLng[2];
			LatLng uan=new LatLng(uno.getValorVertice().getLatitud(), uno.getValorVertice().getLongitud());
			LatLng tu= new LatLng(dos.getValorVertice().getLatitud(), dos.getValorVertice().getLongitud());
			arcs[0]=uan;
			arcs[1]=tu;

			Circle ver1= new Circle(map);
			ver1.setCenter(uan);
			ver1.setRadius(1);
			CircleOptions op= new CircleOptions();
			op.setFillColor("#0000ff");
			ver1.setOptions(op);
			ver1.setVisible(true);


			Circle ver2= new Circle(map);
			ver2.setCenter(tu);
			ver2.setRadius(1);
			CircleOptions op2= new CircleOptions();
			op2.setFillColor("#0000ff");
			ver2.setOptions(op2);
			ver2.setVisible(true);

			Polygon w=new Polygon(map);
			PolygonOptions p1 = new PolygonOptions();
			p1.setFillColor("#FF000");
			p1.setStrokeColor("#FF000");
			w.setOptions(p1);
			w.setPath(arcs);
			w.setVisible(true);
			j++;

		}
	}
}