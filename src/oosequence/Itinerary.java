package oosequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class Itinerary {
	private ArrayList<TripComponent> flights;
	private String name = "";
	
	public Itinerary(String name) {
		this.name = name;
		flights = new ArrayList<TripComponent>();
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<TripComponent> getFlights() {
		return flights;
	}

	public void addFlight(TripComponent flightToAdd) {
		boolean overlap = false;
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getEnd().after(flightToAdd.getStart()) && flights.get(i).getStart().before(flightToAdd.getEnd())) {
				overlap = true;
			}
		}
	
		if (overlap == false) {
			flights.add(flightToAdd);
			Collections.sort(flights, (a, b) -> a.getEnd().compareTo(b.getEnd()));
		}
		

	}

	public long getTotalLayover() {
		long layoverTime = 0;
		for (int i = 0; i < flights.size() - 1 ; i++) {
			layoverTime += TimeUnit.MINUTES.convert((flights.get(i+1).getStart().getTime()) - (flights.get(i).getEnd().getTime()), TimeUnit.MILLISECONDS);
		}
		return layoverTime;
	}

}
