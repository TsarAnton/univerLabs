package domain;

public class Break extends Entity {
	private String break_notes;
	private String repair_notes;

	public String getBreak_notes() {
		return break_notes;
	}

	public void setBreak_notes(String break_notes) {
		this.break_notes = break_notes;
	}

	public String getRepair_notes() {
		return repair_notes;
	}

	public void setRepair_notes(String repair_notes) {
		this.repair_notes = repair_notes;
	}
}
