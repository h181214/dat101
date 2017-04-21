package no.hib.dat101.modell;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Klasse for � fremstille ett spille brett
 *
 */
@Entity
@Table(name = "brett", schema = "kristoffer_stigespill")
public class Brett {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer brett_id;
	@Column(name = "navn")
	private String navn;

	@Transient
	private List<Rute> ruteTab;
	
	@Transient
	private final Integer ANTALL_RUTER = 101;

	/**
	 * Konstrukt�r for brett
	 */
	public Brett() {
		ruteTab = new ArrayList<>(ANTALL_RUTER);
	}
	
	public void settOppBrett() {
		for (int i = 0; i < ANTALL_RUTER; i++) {
			Rute r = new Rute(i);
			ruteTab.add(r);
		}
	}

	public Rute finnRute(Rute aktuell, Integer distanse) {
		Integer num = aktuell.getRute_nr() + distanse;
		if (num > ANTALL_RUTER - 1) {
			num = aktuell.getRute_nr();
		}
		return ruteTab.get(num);
	}


	public void nyPlass(Rute rute, Spiller spiller) {
		spiller.getBrikke().setPosisjon(rute);
	}

	public Integer getBrett_id() {
		return brett_id;
	}

	public void setBrett_id(Integer brett_id) {
		this.brett_id = brett_id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public List<Rute> getRuteTab() {
		
		
		return ruteTab;
	}

	public void setRuteTab(List<Rute> ruteTab) {
		this.ruteTab = ruteTab;
	}

	public Integer getANTALL_RUTER() {
		return ANTALL_RUTER;
	}

}