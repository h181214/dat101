package no.hib.dat101.modell.brikke;

import no.hib.dat101.modell.*;

public class Brikke {
	private Brikkefarge farge;
	private Rute posisjon;

	public Brikke() {
		this(null, null);
	}

	public Brikke(Brikkefarge farge, Rute posisjon) {
		this.farge = farge;
		this.posisjon = posisjon;
	}

	public Brikkefarge getFarge() {
		return farge;
	}

	public void setFarge(Brikkefarge farge) {
		this.farge = farge;
	}

	public Rute getPosisjon() {
		return posisjon;
	}

	public void setPosisjon(Rute posisjon) {
		this.posisjon = posisjon;
	}
}