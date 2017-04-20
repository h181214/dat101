package no.hib.dat101.klient;

import no.hib.dat101.grensesnitt.*;
import no.hib.dat101.modell.*;
import no.hib.dat101.modell.brikke.*;
import javax.persistence.*;
import java.util.*;

public class klient {

	private static Stigespill stigespill;

	public static void main(String[] args) {
		Scanner tast = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eclipselink");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.close();
		entityManagerFactory.close();
		tast.close();
	}

	public static void skrivSpillereFerdig(EntityManager em, Integer antall) {
		stigespill.setSpillere(new ArrayList<Spiller>(antall));
		Spiller s1 = new Spiller();
		Spiller s2 = new Spiller();

		s1.setNavn("Arne");
		s2.setNavn("Peder");
		s1.setBrikke(new Brikke(BrikkeFarge.finnBrikkeFarge(0), stigespill.getBrett().getRuteTab().get(0)));
		s2.setBrikke(new Brikke(BrikkeFarge.finnBrikkeFarge(1), stigespill.getBrett().getRuteTab().get(0)));
		s1.setStigespill_id(stigespill);
		s2.setStigespill_id(stigespill);
		stigespill.getSpillere().add(s1);
		stigespill.getSpillere().add(s2);

		try {
			em.getTransaction().begin();
			em.persist(s1);
			em.persist(s2);
			em.getTransaction().commit();
		} catch (RollbackException e) {
			em.getTransaction().rollback();
		}
	}

	public static void skrivSpillere(EntityManager em, Integer antall) {
		stigespill.setSpillere(new ArrayList<Spiller>(antall));
		for (int i = 0; i < antall; i++) {
			Spiller s = new Spiller();
			s.setNavn(stigespill.getUi().lesInnSpiller());
			s.setBrikke(new Brikke(stigespill.getUi().lesInnBrikkeFarge(), stigespill.getBrett().getRuteTab().get(0)));
			s.setStigespill_id(stigespill);
			stigespill.getSpillere().add(s);

			try {
				em.getTransaction().begin();
				em.persist(s);
				em.getTransaction().commit();
			} catch (RollbackException e) {
				em.getTransaction().rollback();
			}
		}
	}
	public static Brett hentBrett(EntityManager em, Integer brett_id) {
		return em.find(Brett.class, brett_id);
	}

	public static void hentRuter(EntityManager em, Brett brett_id) {
		stigespill.getBrett()
				.setRuteTab((List<Rute>) em
						.createQuery(//
								"SELECT r FROM Rute r WHERE r.brett_id = :brett")//
						.setParameter("brett", brett_id)//
						.getResultList());//
	}


	public static void hentSpillere(EntityManager em, Integer antall) {
		for (int i = 0; i < antall; i++) {
			stigespill.getSpillere().add(hentEnSpiller(em, i));
		}
	}

	private static Spiller hentEnSpiller(EntityManager em, Integer spiller_id) {
		return em.find(Spiller.class, spiller_id);
	}

	public static void hentLogg(EntityManager em, Stigespill stiges) {
		stigespill.setLogger((List<Logg>) em.createQuery(//
				"SELECT DISTINCT l FROM Logg l, Spiller s, Stigespill st WHERE l.spiller = s AND s.stigespill_id = :st ORDER BY l.logg_id")//
				.setParameter("st", stiges)//
				.getResultList());//

		for (Logg l : stigespill.getLogger()) {
			System.out.println(l.toString());
		}
	}

	public static void skrivLogg(EntityManager em) {
		for (int i = 0; i < stigespill.getLogger().size(); i++) {
			try {
				em.getTransaction().begin();
				em.persist(stigespill.getLogger().get(i));
				em.getTransaction().commit();
			} catch (RollbackException e) {
				em.getTransaction().rollback();
			}
		}
	}

	public static Stigespill hentStigeSpill(EntityManager em, Integer stigespill_id) {
		return em.find(Stigespill.class, stigespill_id);
	}

	public static void skrivStigespill(EntityManager em) {
		try {
			em.getTransaction().begin();
			em.persist(stigespill);
			em.getTransaction().commit();
		} catch (RollbackException e) {
			em.getTransaction().rollback();
		}
	}
}
