package no.hib.dat101.modell.brikke;

public enum Brikkefarge {
	RED(0), BLUE(1), GREEN(2), YELLOW(3);

	private Integer nr;


	private Brikkefarge(Integer nr) {
		this.nr = nr;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}
}
