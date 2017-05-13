package de.db.matcha.vbb;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.db.matcha.SupportedLanguages;
import de.db.matcha.structures.AbstractTicket;
import de.db.matcha.structures.Zone;

public class EinzelticketVbb extends AbstractTicket{
	public Map<String,Integer> priceInCent = new TreeMap<String,Integer>();
	
	public EinzelticketVbb() {
		priceInCent.put(VbbIndividualZones.ABC, 500);
		priceInCent.put(VbbIndividualZones.AB, 400);
		priceInCent.put(VbbIndividualZones.BC, 300);
	}

	@Override
	public String toString() {
		return "EinzelticketVbb [priceInCent=" + priceInCent + ", maxAdults=" + maxAdults + ", maxChildren="
				+ maxChildren + ", validityTime=" + validityTime + ", validityGeography=" + validityGeography
				+ ", validityProviderSelection=" + validityProviderSelection + ", validityChangingAndRedirecting="
				+ validityChangingAndRedirecting + ", ticketableZoneSelection=" + ticketableZoneSelection
				+ ", zonesFromTicketableSelection=" + zonesFromTicketableSelection + "]";
	}
	
	public String explainInLanguage(SupportedLanguages lang){
		if(lang.equals(SupportedLanguages.English)){
			return "Single ride incl. changes&transfers, using shortest/fastest route, no roundtrips, no deviations";
		}else if(lang.equals(SupportedLanguages.German)){
			return "Einfache Fahrt incl. Umsteigen und Linienwechsel, kürzeste bzw. schnellste Route, keine Rundfahren, keine Rückfahrten, keine Umwege";
		}else if(lang.equals(SupportedLanguages.Japanese)){
			return "変更とラインの変更、最短または最速ルートを含む一つの方法として、ノーリターン旅行、ない回り道を切り捨てません";
		}else{
			return "Please select English, German, or Japanese";
		}
	}
}
