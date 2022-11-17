package oosequence;

import java.util.Date;

public class Flight extends TripComponent {
	
	private String departureAirport = "";
	private String arrivalAirport = "";
	

	public Flight(Flight toCopy) {
		super(toCopy);
		departureAirport = toCopy.departureAirport;
		arrivalAirport = toCopy.arrivalAirport;
		
	}

	public Flight(Date departure, Date arrival, String departureAirport, String arrivalAirport) {
		super(departure, arrival);
		if (departureAirport != null) {if (departureAirport.length() == 3) {this.departureAirport = departureAirport;}}
		if (arrivalAirport != null) {if (arrivalAirport.length() == 3) {this.arrivalAirport = arrivalAirport;}}
		
	}

	@Override
	public String getStart() {
		return departureAirport + " " + super.getStart();
	}
	
	@Override
	public String getEnd() {
		return arrivalAirport + " " + super.getEnd();
	}

	public void setDepartureAirport(String airport) {
		if (airport != null) {
			if (airport.length() == 3 ) {departureAirport = airport;} else {departureAirport = "";}
		} else if (airport == null) {departureAirport = "";}}

	public String getDepartureAirport() {return departureAirport;}
	
	public void setArrivalAirport(String airport) {
		if (airport != null) {
			if (airport.length() == 3 ) {arrivalAirport = airport;} else {arrivalAirport = "";}
		} else if (airport == null) {arrivalAirport = "";}}

	public String getArrivalAirport() {return arrivalAirport;}

	public String getDuration() {return String.format("%d minutes", lengthInSeconds() / 60);}
	
	
	
}
