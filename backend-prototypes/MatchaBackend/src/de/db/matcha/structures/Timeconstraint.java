package de.db.matcha.structures;

public class Timeconstraint {
	public EarliestStart start;
	public LatestEnd end;
	public MaximumDuration duration;
	@Override
	public String toString() {
		return "Timeconstraint [start=" + start + ", end=" + end + ", duration=" + duration + "]";
	}
}
