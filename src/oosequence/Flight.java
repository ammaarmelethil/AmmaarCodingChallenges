package oosequence;

public class Flight extends TripComponent {
	
	private String departureAirport = "";
	private String arrivalAirport = "";

	public void setDepartureAirport(String airport) {
		if (airport != null) {
			if (airport.length() == 3 ) {departureAirport = airport;} else {departureAirport = "";}}}

	public String getDepartureAirport() {return departureAirport;}
	
	public void setArrivalAirport(String airport) {
		if (airport != null) {
			if (airport.length() == 3 ) {arrivalAirport = airport;} else {arrivalAirport = "";}}}

	public String getArrivalAirport() {return arrivalAirport;}

	public String getDuration() {return String.format("%d minutes", lengthInSeconds() / 60);}
	
	
	
}
