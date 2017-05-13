package de.db.matcha;

import java.util.Set;

public class TicketRequirement {
	public int maxNumberOfPersons;
	public int minRidePerDay; //TODO - not implemented yet
	public Set<String> placesToCover;

	@Override
	public String toString() {
		return "TicketRequirement [maxNumberOfPersons=" + maxNumberOfPersons + ", placesToCover="+placesToCover+"]";
	} 
}
