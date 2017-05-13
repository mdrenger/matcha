package de.db.matcha.structures;

public class DirectionalConstraint {
	public static final String/*to become an enum*/ CHANGES_YES_RETURN_NO = /*i18n*/"Within the validity, only single trip without detours, no return";
	public static final String/*to become an enum*/ CHANGES_YES_RETURN_YES = /*i18n*/"Within the validity, any number of trips incl. detours and return";
	public String constraint;
	@Override
	public String toString() {
		return "DirectionalConstraint [constraint=" + constraint + "]";
	}
}
