package it.polito.tdp.esempioSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeggiBabs {
	public void run() {
		String jdbcURL="jdbc:mysql://localhost/babs?user=root&password=mortaccimia";
		try {
			Connection conn=DriverManager.getConnection(jdbcURL);//DriverManager è una classe statica
			//Significa che posso chiamarne i metodi senza dover creare un'istanza della classe
			String sql="SELECT NAME FROM station WHERE landmark= ? ";
			
			PreparedStatement st= conn.prepareStatement(sql);
			
			st.setString(1, "Palo Alto");
			
			ResultSet res=st.executeQuery(); 
			
			while(res.next()) {
				String nomeStazione=res.getString("NAME");
				System.out.println(nomeStazione);
			}
			st.close();
			
			Statement st2= conn.createStatement();
			
			//Codice 
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FACTORY: creazione di un oggetto di una classe, 
		//senza conoscere il tipo della classe (non potevo usare NEW)
		//uso un metodo fornito da un'altra classe che internamente fa la NEW
		//e conoscerà il tipo di classe effettivo
		
	}
	public static void main(String args[]) {
		LeggiBabs babs=new LeggiBabs();
		babs.run();
	}
}
