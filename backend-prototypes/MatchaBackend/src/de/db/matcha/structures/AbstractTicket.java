package de.db.matcha.structures;

import java.util.Set;

/**
 * Describes an abstract ticket. Instances describe a ticket class (e.g. single-ride ticket VBB or 24h-Ticket elsewhere). 
 * An instance of this class does *not* represent a concrete ticket instance issued to an identity. 
 * @author MichaelKuperberg
 */
public class AbstractTicket {
	public int getMaxAdults(){
		return maxAdults;
	}
	public int maxAdults;
	public int maxChildren;
	public Timeconstraint validityTime;
	public RegionConstraint validityGeography;
	public ModesAndLinesConstraint validityProviderSelection;
	public DirectionalConstraint validityChangingAndRedirecting;
	
	public String ticketableZoneSelection;
	public Set<Zone> zonesFromTicketableSelection;
	@Override
	public String toString() {
		return "AbstractTicket [maxAdults=" + maxAdults + ", maxChildren=" + maxChildren + ", validityTime="
				+ validityTime + ", validityGeography=" + validityGeography + ", validityProviderSelection="
				+ validityProviderSelection + ", validityChangingAndRedirecting=" + validityChangingAndRedirecting
				+ ", ticketableZoneSelection=" + ticketableZoneSelection + ", zonesFromTicketableSelection="
				+ zonesFromTicketableSelection + "]";
	}
}
