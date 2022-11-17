package oosequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


public class Itinerary {
	private ArrayList<TripComponent> tripComponents;
	private String name = "";
	
	public Itinerary(String name) {
		this.name = name;
		tripComponents = new ArrayList<TripComponent>();
	}

	public String getName() {
		return name;
	}
	

	public void addTripComponent(TripComponent trip) {

		boolean overlap = false;
		
			for (TripComponent trips : tripComponents) {
				if (trips.overlapsWith(trip)) {
					overlap = true;
				}
			}
		
			if (overlap == false) {
				tripComponents.add(trip);
				Collections.sort(tripComponents, (a,b) -> a.endForComparison().compareTo(b.endForComparison()));
			}
	}
		
		
		

	public ArrayList<TripComponent> getTripComponents() {
		return tripComponents;
	}
	
	public String toString() {
		String theTrips = "";
		
		for (TripComponent trips : tripComponents) {
			theTrips += tripComponents.indexOf(trips) + "\t" + trips.getStart() + "\t" + trips.getEnd() + "\n";
		}
		

		return this.name + "\n" + theTrips;
	}

}
