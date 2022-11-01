package oosequence;

import java.util.Date;

public class Flight {
	Date arrival;
	Date departure;

	public Flight(Date departureDate, Date arrivalDate) {
		// TODO Auto-generated constructor stub
		departure = departureDate;
		arrival = arrivalDate;
	
	
	}

	public Flight(Flight p) {
		// TODO Auto-generated constructor stub
	}

	public long length() {
		// TODO Auto-generated method stub
		return 0;
	}

}
