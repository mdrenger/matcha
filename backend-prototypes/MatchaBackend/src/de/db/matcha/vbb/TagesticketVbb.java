package de.db.matcha.vbb;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.db.matcha.SupportedLanguages;
import de.db.matcha.structures.AbstractTicket;
import de.db.matcha.structures.Zone;

public class TagesticketVbb extends AbstractTicket{
	public Map<String,Integer> priceInCent = new TreeMap<String,Integer>();
	
	public TagesticketVbb() {
		priceInCent.put(VbbIndividualZones.ABC, 1200);
		priceInCent.put(VbbIndividualZones.AB, 1000);
		priceInCent.put(VbbIndividualZones.BC, 900);
	}

	@Override
	public String toString() {
		return "TagesticketVbb [priceInCent=" + priceInCent + ", maxAdults=" + maxAdults + ", maxChildren="
				+ maxChildren + ", validityTime=" + validityTime + ", validityGeography=" + validityGeography
				+ ", validityProviderSelection=" + validityProviderSelection + ", validityChangingAndRedirecting="
				+ validityChangingAndRedirecting + ", ticketableZoneSelection=" + ticketableZoneSelection
				+ ", zonesFromTicketableSelection=" + zonesFromTicketableSelection + "]";
	}

	public String explainInLanguage(SupportedLanguages lang){
		if(lang.equals(SupportedLanguages.English)){
			return "Day-flat ticket incl. changes&transfers, any routes incl. roundtrips and deviations";
		}else if(lang.equals(SupportedLanguages.German)){
			return "Tageskarte incl. Umsteigen und Linienwechsel, beliebiege Fahrten inkl. Rundfahren, Rückfahrten und Umwege";
		}else if(lang.equals(SupportedLanguages.Japanese)){
			return "変更と移動を含むデイフラットチケット、 往復および偏差";
		}else{
			return "Please select English, German, or Japanese";
		}
	}
}
