package de.db.matcha.structures;

public class RegionConstraint {
	public static final String/*to become an enum*/ AB_ONLY = /*i18n*/"Only zones A and B, no C";
	public static final String/*to become an enum*/ BC_ONLY = /*i18n*/"Only zones B and C, no A";
	public static final String/*to become an enum*/ ABC_ONLY = /*i18n*/"All zones (A and B and C)";
	public String constraint;
	public boolean isEqualOrBetter(RegionConstraint a, RegionConstraint b){
		if(a==null|| b==null|| a.constraint==null || b.constraint==null){
			return false;
		}
		if(a.constraint.equals(b.constraint)) return true;
		if(a.constraint.equals(ABC_ONLY)) return true;
		return false;
	}
	@Override
	public String toString() {
		return "RegionConstraint [constraint=" + constraint + "]";
	}
}
