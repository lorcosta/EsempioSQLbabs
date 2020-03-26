package it.polito.tdp.esempioSQL.model;

import java.util.List;

import it.polito.tdp.esempioSQL.db.BabsDAO;

public class LeggiBabs {
	public void run() {
		BabsDAO dao= new BabsDAO();
		List<Station> tutte=dao.listStation();
		
		for(Station s:tutte) {
			System.out.println(s.getName());
		}
		
		System.out.println("\n*******Stazioni di Palo Alto***********\n");
		List<Station> paloAlto=dao.listStationByLandmark("Palo Alto");
		for(Station s:paloAlto) {
			System.out.println(s.getName());
		}
	}
	public static void main(String args[]) {
		LeggiBabs babs=new LeggiBabs();
		babs.run();
	}
}
 