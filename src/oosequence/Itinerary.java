package oosequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class Itinerary {
	private ArrayList<Flight> flights;
	private String name = "";
	
	public Itinerary(String name) {
		this.name = name;
		flights = new ArrayList<Flight>();
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void addFlight(Flight flightToAdd) {
		boolean overlap = false;
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getArrival().after(flightToAdd.getDeparture()) && flights.get(i).getDeparture().before(flightToAdd.getArrival())) {
				overlap = true;
			}
		}
	
		if (overlap == false) {
			flights.add(flightToAdd);
			Collections.sort(flights, (a, b) -> a.getArrival().compareTo(b.getArrival()));
		}
		

	}

	public long getTotalLayover() {
		long layoverTime = 0;
		for (int i = 0; i < flights.size() - 1 ; i++) {
			layoverTime += TimeUnit.MINUTES.convert((flights.get(i+1).getDeparture().getTime()) - (flights.get(i).getArrival().getTime()), TimeUnit.MILLISECONDS);
		}
		return layoverTime;
	}

}
