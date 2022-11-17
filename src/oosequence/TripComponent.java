package oosequence;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TripComponent {
	
	private Date start = new Date();
	private Date end = new Date(start.getTime() + 3600000);
	
	public void setStart(Date departureDate) {
		if (end != null) {
			if (departureDate.before(end)) {start = departureDate;} 
		} 	else if (departureDate == null || end == null) {start = departureDate;}}
	
	public String getStart() {
		String theStart = ""; 
		if (start != null) {
			theStart = start.toString();	
		}
		return theStart;
	}

	public void setEnd(Date arrivalDate) {
		if (start != null) {
			if (arrivalDate.after(start)) {end = arrivalDate;} 
		} 	else if (arrivalDate == null || start == null) {end = arrivalDate;}} 
	
	public String getEnd() {
		String theEnd = ""; 
		if (end != null) {
			theEnd = end.toString();	
		}
		return theEnd;
	}
	
	public TripComponent(){}

	public TripComponent(Date startDate, Date endDate) {
		if (startDate != null && endDate != null) {
			if (endDate.after(startDate)) {
				start = startDate;
				end = endDate;
			} else if (!endDate.after(startDate)) {
				start = startDate;
				end = null;
			}
		} else if (endDate == null || startDate == null) {
			start = startDate;
			end = endDate;
		}
	}

	public TripComponent(TripComponent toCopy) {start = toCopy.start;end = toCopy.end;}

	public long lengthInSeconds() {
		long duration = 0;
		if (start != null && end != null) {duration = TimeUnit.SECONDS.convert(end.getTime()-start.getTime(), TimeUnit.MILLISECONDS);}
		return duration;
	}
	
	public Date endForComparison() {
		return end;
	}

	public boolean overlapsWith(TripComponent timePeriod) {
		boolean overlap = false;
		if (timePeriod.start != null && timePeriod.end != null) {
			if (this.start != null && this.end != null) {
				if (this.start.before(timePeriod.end) && this.end.after(timePeriod.start)) {overlap = true;}}}
		return overlap;
	}
}
