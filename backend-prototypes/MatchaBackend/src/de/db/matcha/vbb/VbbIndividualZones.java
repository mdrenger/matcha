package de.db.matcha.vbb;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.db.matcha.structures.Zone;

public class VbbIndividualZones {
	public static final String BC = "BC";
	public static final String AB = "AB";
	public static final String ABC = "ABC";
	public static Zone A = new Zone();
	public static Zone B = new Zone();
	public static Zone C = new Zone();
	
	public static Set<Zone> ABTicketZoning = new HashSet<>();
	public static Set<Zone> BCTicketZoning = new HashSet<>();
	public static Set<Zone> ABCTicketZoning = new HashSet<>();
	
	public Set<Zone> allZones = new HashSet<Zone>();
	public Map<String,Set<Zone>> allTicketableZonecombination = new TreeMap<String,Set<Zone>>();
	
	{
		A.zoneId="VBB_A";
		A.stops = new HashSet<>();
		A.stops.add("Berlin Hauptbahnhof");
		A.stops.add("Berlin Ostbahnhof");
		B.zoneId="VBB_B";
		B.stops = new HashSet<>();
		B.stops.add("Berlin Spandau");
		B.stops.add("Berlin Lichtenberg");
		C.zoneId="VBB_C";
		C.stops = new HashSet<>();
		C.stops.add("Potsdam");
		C.stops.add("Berlin Schönefeld");
		allZones.add(A);
		allZones.add(B);
		allZones.add(C);
		ABCTicketZoning.add(A);
		ABCTicketZoning.add(B);
		ABCTicketZoning.add(C);
		ABTicketZoning.add(A);
		ABTicketZoning.add(B);
		BCTicketZoning.add(B);
		BCTicketZoning.add(C);
		allTicketableZonecombination.put(ABC,ABCTicketZoning);
		allTicketableZonecombination.put(AB,ABTicketZoning);
		allTicketableZonecombination.put(BC,BCTicketZoning);
	}
	
	public VbbIndividualZones(){
		
	}
	
	public Set<Zone> getZonesNeededForDestinationList(Set<String> destinations){
		Set<Zone> ret = new HashSet<Zone>();
		//here, we are very comfortable because all stations are in one zone only
		for(String dest : destinations){
			for(Zone z : allZones){
				if(z.stops.contains(dest)){
					ret.add(z);
				}
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		VbbIndividualZones z = new VbbIndividualZones();
		Set<String> example = new HashSet<String>();
		example.add("Berlin Ostbahnhof");
		example.add("Potsdam");
		System.out.println(example.size()+" places: "+example);
		
		Set<Zone> zones = z.getZonesNeededForDestinationList(example);
		System.out.println(zones.size()+" zones needed: "+zones);
		Map<String,Set<Zone>> ticketableZonings = z.getTicketableZoningPossibleForZoneList(zones);
		System.out.println(ticketableZonings.size()+" ticketable zone combinations: ");
		for(String key : ticketableZonings.keySet()){
			System.out.println(key+": "+ticketableZonings.get(key));
		}
	}

	public Map<String,Set<Zone>> getTicketableZoningPossibleForZoneList(Set<Zone> zones) {
		Map<String,Set<Zone>> ret = new TreeMap<String,Set<Zone>> ();
		
		for(String key : allTicketableZonecombination.keySet()){
			Set<Zone> ticketableZoning  =  allTicketableZonecombination.get(key);
			if(ticketableZoning.containsAll(zones)){
				ret.put(key,ticketableZoning);
			}
		}
		return ret;
	}
}
