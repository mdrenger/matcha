package de.db.matcha;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.db.matcha.structures.AbstractTicket;
import de.db.matcha.structures.DirectionalConstraint;
import de.db.matcha.structures.EarliestStart;
import de.db.matcha.structures.LatestEnd;
import de.db.matcha.structures.MaximumDuration;
import de.db.matcha.structures.ModesAndLinesConstraint;
import de.db.matcha.structures.RegionConstraint;
import de.db.matcha.structures.Timeconstraint;
import de.db.matcha.structures.Zone;
import de.db.matcha.vbb.EinzelticketVbb;
import de.db.matcha.vbb.TagesticketVbb;
import de.db.matcha.vbb.VbbIndividualZones;
import de.db.matcha.vbb.VbbTicketableZoneCombination;

public class MatchaPrototype {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EinzelticketVbb einzelticketVbb1Pers = new EinzelticketVbb(); // we will build an orderly constructor, for sure
		einzelticketVbb1Pers.maxAdults=1;
		einzelticketVbb1Pers.maxChildren=1;
		//initialized in constructor
//		einzelticketVbb1Pers.priceInCent.put(VbbIndividualZones.ABTicketZoning, 300 /*3€*/);
//		einzelticketVbb1Pers.priceInCent.put(VbbIndividualZones.BCTicketZoning, 300 /*3€*/);
//		einzelticketVbb1Pers.priceInCent.put(VbbIndividualZones.ABTicketZoning, 300 /*3€*/);
		einzelticketVbb1Pers.validityChangingAndRedirecting = new DirectionalConstraint();
		einzelticketVbb1Pers.validityChangingAndRedirecting.constraint = DirectionalConstraint.CHANGES_YES_RETURN_NO;
		einzelticketVbb1Pers.validityGeography = new RegionConstraint();
		einzelticketVbb1Pers.validityGeography.constraint=RegionConstraint.AB_ONLY;
		einzelticketVbb1Pers.validityProviderSelection = new ModesAndLinesConstraint();
		einzelticketVbb1Pers.validityProviderSelection.constraint = ModesAndLinesConstraint.SBAHN_TRAM_BUS;	
		einzelticketVbb1Pers.validityTime = new Timeconstraint();
		einzelticketVbb1Pers.validityTime.start = new EarliestStart();
		einzelticketVbb1Pers.validityTime.start.value = EarliestStart.AT_DAY_START;
		einzelticketVbb1Pers.validityTime.duration = new MaximumDuration();
		einzelticketVbb1Pers.validityTime.duration.duration_in_minutes = 90;
		einzelticketVbb1Pers.validityTime.end = new LatestEnd();
		einzelticketVbb1Pers.validityTime.end.value = LatestEnd.MINS_60_AFTER_VALIDATION;
		
		
		TagesticketVbb tagesticketVbb5Pers = new TagesticketVbb(); // we will build an orderly constructor, for sure
		tagesticketVbb5Pers.maxAdults=5;
		tagesticketVbb5Pers.maxChildren=2;
//		tagesticketVbb5Pers.priceInCent = 1000 /*3€*/; //initialized in constructor
		tagesticketVbb5Pers.validityChangingAndRedirecting = new DirectionalConstraint();
		tagesticketVbb5Pers.validityChangingAndRedirecting.constraint = DirectionalConstraint.CHANGES_YES_RETURN_YES;
		tagesticketVbb5Pers.validityGeography = new RegionConstraint();
		tagesticketVbb5Pers.validityGeography.constraint=RegionConstraint.ABC_ONLY;
		tagesticketVbb5Pers.validityProviderSelection = new ModesAndLinesConstraint();
		tagesticketVbb5Pers.validityProviderSelection.constraint = ModesAndLinesConstraint.SBAHN_TRAM_BUS;	
		tagesticketVbb5Pers.validityTime = new Timeconstraint();
		tagesticketVbb5Pers.validityTime.start = new EarliestStart();
		tagesticketVbb5Pers.validityTime.start.value = EarliestStart.AFTER_MORNING_RUSH_HOUR;
		tagesticketVbb5Pers.validityTime.duration = new MaximumDuration();
		tagesticketVbb5Pers.validityTime.duration.duration_in_minutes = 1440; //a whole day
		tagesticketVbb5Pers.validityTime.end = new LatestEnd();
		tagesticketVbb5Pers.validityTime.end.value = LatestEnd.MIDNIGHT_OF_STAMPING_DAY;
		
		Set<AbstractTicket> availableTickets = new HashSet<AbstractTicket>();
		availableTickets.add(einzelticketVbb1Pers);
		availableTickets.add(tagesticketVbb5Pers);
		
		TicketRequirement requirement1 = new TicketRequirement();
		requirement1.maxNumberOfPersons = 2;
		Set<AbstractTicket> suitableTickets1 = selectFrom(availableTickets,requirement1);
		System.out.println("Requirement 1: "+requirement1);
		System.out.println("\n"+"Candidates ("+availableTickets.size()+" available tickets): "+availableTickets);
		System.out.println("\n"+"Suitable tickets ("+suitableTickets1.size()+"): "+suitableTickets1);
		
		TicketRequirement requirement2 = new TicketRequirement();
		requirement2.maxNumberOfPersons = 2;
		requirement2.placesToCover = new TreeSet<String>();
		requirement2.placesToCover.add("Berlin Ostbahnhof");
		requirement2.placesToCover.add("Potsdam");
		Set<AbstractTicket> suitableTickets2 = selectFrom(availableTickets,requirement2);
		System.out.println("\n\n"+"Requirement 2: "+requirement2);
		System.out.println("\n"+"Candidates ("+availableTickets.size()+" available tickets): "+availableTickets);
		System.out.println("\n"+"Suitable tickets ("+suitableTickets2.size()+"): "+suitableTickets2);
		
		System.err.println("Stops covered by the first ticket from those found for requirement 2: ");
		System.err.println(getAllStationsCoveredBySetOfzones(suitableTickets2.iterator().next().zonesFromTicketableSelection));
		
		
	}

	/**
	 * Currently only checking for the number of persons, only 
	 * @param availableTickets
	 * @param requirement
	 * @return
	 */
	private static Set<AbstractTicket> selectFrom(Set<AbstractTicket> availableTickets, TicketRequirement requirement) {
		Set<AbstractTicket> candidates = new HashSet<AbstractTicket>();
		for(AbstractTicket c : availableTickets){
			if(c.maxAdults>=requirement.maxNumberOfPersons){
				candidates.add(c);
			}
		}
		
		if(requirement.placesToCover!=null && !requirement.placesToCover.isEmpty()){
			VbbIndividualZones z = new VbbIndividualZones();
			Set<Zone> zones = z.getZonesNeededForDestinationList(requirement.placesToCover);
			System.out.println(zones.size()+" zones needed: "+zones);
			Map<String,Set<Zone>> ticketableZonings = z.getTicketableZoningPossibleForZoneList(zones);
			System.out.println(ticketableZonings.size()+" ticketable zone combinations: ");
			for(String key : ticketableZonings.keySet()){
				System.out.println(key+": "+ticketableZonings.get(key));
			}
			String keyToFirstTicketableZoning = ticketableZonings.keySet().iterator().next();
			//for testing purposes: taking only the first ticketable zoning (not even checking if it exists...) TODO improve
			for(AbstractTicket c : candidates){
				c.ticketableZoneSelection = keyToFirstTicketableZoning;
				c.zonesFromTicketableSelection = ticketableZonings.get(c.ticketableZoneSelection);
			}
		}
		return candidates;
	}
	
	public Set<String> getAllStationCoveredByTicketableZoneCombination(VbbTicketableZoneCombination combination){
		Set<String> stations = new HashSet<String>();
		for(Zone z : combination.zonesWithinCombination){
			for(String s : z.stops){
				stations.add(s);
			}
		}
		return stations;
	}
	
	public static Set<String> getAllStationsCoveredBySetOfzones(Set<Zone> zones){
		Set<String> stations = new HashSet<String>();
		for(Zone z : zones){
			for(String s : z.stops){
				stations.add(s);
			}
		}
		return stations;
	}
}
