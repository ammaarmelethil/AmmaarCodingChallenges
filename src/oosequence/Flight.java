package oosequence;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flight {
	
	
	private Date departure;
	private Date arrival;
	
	public void setDeparture(Date departureDate) {
		if (arrival != null) {
			if (departureDate.before(arrival)) {
				departure = departureDate;
			} 
		} else if (departureDate == null || arrival == null) {
			departure = departureDate;
		}

		
	}

	public void setArrival(Date arrivalDate) {
		if (departure != null) {
			if (arrivalDate.after(departure)) {
				arrival = arrivalDate;
			} 
		} else if (arrivalDate == null || departure == null) {
			arrival = arrivalDate;
		}
		
	} 
	
	public Date getDeparture() {
		return departure;
	}
	
	public Date getArrival() {
		return arrival;
	}
	
	

	public Flight(Date departureDate, Date arrivalDate) {
		if (departureDate != null && arrivalDate != null) {
			if (arrivalDate.after(departureDate)) {
				arrival = arrivalDate;
				departure = departureDate;
			} 
		} else if (arrivalDate == null || departureDate == null) {
			arrival = arrivalDate;
			departure = departureDate;
		}
		
	}
		
	

	public Flight(Flight toCopy) {
		departure = toCopy.departure;
		arrival = toCopy.arrival;
	}

	public long length() {
		long duration = 0;
		if (departure != null && arrival != null) {
			duration = TimeUnit.MINUTES.convert(arrival.getTime()-departure.getTime(), TimeUnit.MILLISECONDS);
		}
		return duration;
	}



	



}
