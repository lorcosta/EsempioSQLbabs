package it.polito.tdp.esempioSQL.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.esempioSQL.model.Station;

public class BabsDAO {
	
	public List<Station> listStation(){
		List<Station> result= new ArrayList<Station>();
		String sql="SELECT station_id, name, dockcount,landmark FROM station ORDER BY name";
		
		try {
			Connection conn=DBConnect.getConnection();//DriverManager è una classe statica
			//Significa che posso chiamarne i metodi senza dover creare un'istanza della classe
			
			PreparedStatement st= conn.prepareStatement(sql);
			
			ResultSet res=st.executeQuery();
			
			while(res.next()) {
				Station s= new Station(res.getInt("station_id"), res.getString("name"),
						res.getInt("dockCount"), res.getString("landmark"));
				result.add(s);
			}
			st.close();
			
			//Codice 
			
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FACTORY: creazione di un oggetto di una classe, 
		//senza conoscere il tipo della classe (non potevo usare NEW)
		//uso un metodo fornito da un'altra classe che internamente fa la NEW
		//e conoscerà il tipo di classe effettivo
		return null;//se qualcosa va male nella creazione della lista
	}
	public List<Station> listStationByLandmark(String landmark){
		List<Station> result= new ArrayList<Station>();
		String sql="SELECT station_id, name, dockcount,landmark FROM station "
				+ "WHERE landmark=? ORDER BY name";
		
		
		try {
			Connection conn=DBConnect.getConnection();
			
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setString(1, landmark);
			ResultSet res=st.executeQuery();
			
			while(res.next()) {
				Station s= new Station(res.getInt("station_id"), res.getString("name"),
						res.getInt("dockCount"), res.getString("landmark"));
				result.add(s);
			}
			st.close();
			
			//Codice 
			
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FACTORY: creazione di un oggetto di una classe, 
		//senza conoscere il tipo della classe (non potevo usare NEW)
		//uso un metodo fornito da un'altra classe che internamente fa la NEW
		//e conoscerà il tipo di classe effettivo
		return null;//se qualcosa va male nella creazione della lista
	}
}
