package no.hib.dat101.grensesnitt;

import java.util.List;
import java.util.Scanner;

import no.hib.dat101.modell.*;
import no.hib.dat101.modell.brikke.*;

public class UI {
	private Scanner tast = new Scanner(System.in);

	public Integer lesAntallSpillere() {
		System.out.print("\tOppgi antall spillere: ");
		return tast.nextInt();
	}

	public String lesInnSpiller() {
		System.out.print("\tOppgi navn på spiller: ");
		return tast.next();
	}

	public Brikkefarge lesInnBrikkeFarge() {
		System.out.print("\t0 - red\n\t1 - blue\n\t2 - green\n\t3 - yellow\n\tOppgi fargekode: ");
		Integer fargeNr = tast.nextInt();
		Brikkefarge farge = new Brikkefarge(farge);
		return farge;
	}

	public String infoOmTrekk(Logg logg) {
		return logg.toString();
	}

	public void vinner(Stigespill stigespill) {
		System.out.println("\t" + stigespill.getVinner().getNavn() + " vant denne runden!");
	}

	public Integer velgBrett() {
		System.out.print(
				"\n\tOppgi brettet du vil spille på:\n\t1 - Brett nr 1 er vanlig\n\t2 - Brett nr 2 har stiger og slanger\n\tValg: ");
		return tast.nextInt();
	}

	public void antallRuter(Brett brett) {
		System.out.println("\n\tAlle rutene er hentet, det er: " + (brett.getRuteTab().size()));
	}

	public void antallSpillere(List<Spiller> spillere) {
		System.out.println("\n\tAntall spillere " + spillere.size());
	}

}