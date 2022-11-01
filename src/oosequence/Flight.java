package oosequence;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flight {
	Date departure;
	Date arrival;

	public Flight(Date departureDate, Date arrivalDate) {
		if (departureDate != null && arrivalDate != null) {
			if (!departureDate.before(arrivalDate)) {
				departure = null;
				arrival = null;
			} else {
				departure = departureDate;
				arrival = arrivalDate;
			}
			
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
