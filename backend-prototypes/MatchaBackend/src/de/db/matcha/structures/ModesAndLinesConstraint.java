package de.db.matcha.structures;

public class ModesAndLinesConstraint {
	public static final String/*to become an enum*/ S_BAHN_ONLY = /*i18n*/"Only S-Bahn, no other trains, no trams, no buses, no boats";
	public static final String/*to become an enum*/ TRAM_AND_BUS_ONLY = /*i18n*/"Only trams and buses, no boats or trains";
	public static final String/*to become an enum*/ SBAHN_TRAM_BUS = /*i18n*/"Only S-Bahn, trams and buses, no boats and no long-distrance trains";
	public String constraint;
	@Override
	public String toString() {
		return "ModesAndLinesConstraint [constraint=" + constraint + "]";
	}
}
