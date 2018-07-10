package programme;

import klassen.DynGenArray;
import klassen.KontaktPrivat;

public class TesteAdressListenAnwendung {

	public static void main(String[] args) {

		DynGenArray<KontaktPrivat> dynArrPrivatKontakte = new DynGenArray<KontaktPrivat>(1);

		// Kontakte anlegen
		dynArrPrivatKontakte.put(1, new KontaktPrivat(1));
		dynArrPrivatKontakte.put(2, new KontaktPrivat(2));
		dynArrPrivatKontakte.put(3, new KontaktPrivat(3));
		dynArrPrivatKontakte.put(4, new KontaktPrivat(220));
		dynArrPrivatKontakte.put(5, new KontaktPrivat(221));
		dynArrPrivatKontakte.put(6, new KontaktPrivat(222));
		
		dynArrPrivatKontakte.put(7, new KontaktPrivat(1222));

		
		
		
		// Partner anlegen
		dynArrPrivatKontakte.get(1).partner.put(1, dynArrPrivatKontakte.get(2));
		dynArrPrivatKontakte.get(2).partner.put(1, dynArrPrivatKontakte.get(1));
		// Kinder anlegen
		dynArrPrivatKontakte.get(1).kind.put(1, dynArrPrivatKontakte.get(3));
		dynArrPrivatKontakte.get(2).kind.put(1, dynArrPrivatKontakte.get(3));
		
		dynArrPrivatKontakte.get(4).kind.put(1, dynArrPrivatKontakte.get(5));
		dynArrPrivatKontakte.get(4).kind.put(2, dynArrPrivatKontakte.get(6));
		//dynArrPrivatKontakte.get(1).kind.put(1, dynArrPrivatKontakte.get(3));

		
		// Kontakte Perönliche Daten eintragen
		dynArrPrivatKontakte.get(1).setVorname("Max");
		dynArrPrivatKontakte.get(1).setNachname("Mustermann");
		dynArrPrivatKontakte.get(1).setStaatsangehoerigkeit("Deutsch");
		dynArrPrivatKontakte.get(1).setTelefonNr(1, "02223-43234");
		dynArrPrivatKontakte.get(1).setTelefonNr(2, "0177-123456");
		dynArrPrivatKontakte.get(1).setTelefonNr(3, "0178-123456");
		dynArrPrivatKontakte.get(1).setTermin(1, "Geburtstag","05.01.1960");
		dynArrPrivatKontakte.get(1).setTermin(2, "Hochzeitstag","08.08.1988");
		dynArrPrivatKontakte.get(1).setTermin(3, "Spezialdatum","01.01.1900");
		dynArrPrivatKontakte.get(1).getTermin(3).terminAendern("Spezialdatum", "01.12.2015");
		
		dynArrPrivatKontakte.get(2).setVorname("Alexia");
		dynArrPrivatKontakte.get(2).setNachname("Mustermannn");
		dynArrPrivatKontakte.get(2).setStaatsangehoerigkeit("Deutsch");
		dynArrPrivatKontakte.get(2).setTermin(1, "Geburtstag","05.03.1960");
		dynArrPrivatKontakte.get(2).setTermin(2, "Hochzeitstag","08.08.1988");

		dynArrPrivatKontakte.get(3).setVorname("Jim");
		dynArrPrivatKontakte.get(3).setNachname("Knopf");
		dynArrPrivatKontakte.get(3).setStaatsangehoerigkeit("Deutsch");
		dynArrPrivatKontakte.get(3).setTermin(1, "Geburtstag","01.03.1993");

		dynArrPrivatKontakte.get(4).setVorname("Horst");
		dynArrPrivatKontakte.get(4).setNachname("Hacker");

		dynArrPrivatKontakte.get(5).setVorname("Tick");
		dynArrPrivatKontakte.get(5).setNachname("Mustermann");
		
		dynArrPrivatKontakte.get(6).setVorname("Trick");
		dynArrPrivatKontakte.get(6).setNachname("Mustermann");

		
		
		// Kontakte Ausgabe der Stammdaten
		System.out.println(dynArrPrivatKontakte.get(1).getKontaktId());
		System.out.println(dynArrPrivatKontakte.get(1).getVorname());
		System.out.println(dynArrPrivatKontakte.get(1).getNachname());
		System.out.println(dynArrPrivatKontakte.get(1).getStaatsangehoerigkeit());
		System.out.println(dynArrPrivatKontakte.get(1).getTelefonNr(1));
		//System.out.println(dynArray2.get(1).getTermin(1).getDatum());
		//System.out.println(dynArray2.get(1).getTermin(1).getTerminJahr());
		//System.out.println(dynArray2.get(1).getTermin(1).getTerminMonat());
		//System.out.println(dynArray2.get(1).getTermin(1).getBeschreibung());
		//System.out.println(dynArray2.get(1).getTermin(2).getDatum());
		//System.out.println(dynArray2.get(1).getTermin(2).getBeschreibung());
		System.out.println(dynArrPrivatKontakte.get(1).getTermin(1).getBeschreibung()+": "+dynArrPrivatKontakte.get(1).getTermin(1).getDatum());
		System.out.println(dynArrPrivatKontakte.get(1).getTermin(2).getBeschreibung()+": "+dynArrPrivatKontakte.get(1).getTermin(2).getDatum());
		System.out.println(dynArrPrivatKontakte.get(1).getTermin(3).getBeschreibung()+": "+dynArrPrivatKontakte.get(1).getTermin(3).getDatum());

		
		System.out.println(dynArrPrivatKontakte.get(2).getKontaktId());
		System.out.println(dynArrPrivatKontakte.get(2).getVorname());
		System.out.println(dynArrPrivatKontakte.get(2).getNachname());
		System.out.println(dynArrPrivatKontakte.get(2).getStaatsangehoerigkeit());
		System.out.println(dynArrPrivatKontakte.get(2).getTermin(1).getBeschreibung()+": "+dynArrPrivatKontakte.get(2).getTermin(1).getDatum());
		System.out.println(dynArrPrivatKontakte.get(2).getTermin(2).getBeschreibung()+": "+dynArrPrivatKontakte.get(2).getTermin(2).getDatum());

		System.out.println(dynArrPrivatKontakte.get(3).getKontaktId());
		System.out.println(dynArrPrivatKontakte.get(3).getVorname());
		System.out.println(dynArrPrivatKontakte.get(3).getNachname());
		System.out.println(dynArrPrivatKontakte.get(3).getStaatsangehoerigkeit());

		
		
		
		// Ausgabe Partnerschaften
		System.out.println("Partner: "+dynArrPrivatKontakte.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(1).getNachname() + " <--> "
				+ dynArrPrivatKontakte.get(1).partner.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(1).partner.get(1).getNachname());
		// Ausgabe Kinder von (1)
		System.out.println("Kinder: "+dynArrPrivatKontakte.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(1).getNachname()+ " <--> "
				+ dynArrPrivatKontakte.get(1).kind.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(1).kind.get(1).getNachname());
		// Ausgabe Kinder von (2)
		System.out.println("Kinder: "+dynArrPrivatKontakte.get(2).getVorname() + " "
				+ dynArrPrivatKontakte.get(2).getNachname()+ " <--> "
				+ dynArrPrivatKontakte.get(2).kind.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(2).kind.get(1).getNachname());
		// Ausgabe Kinder von (4)
		System.out.println("Kinder: "+dynArrPrivatKontakte.get(4).getVorname() + " "
				+ dynArrPrivatKontakte.get(4).getNachname()+ " <--> "
				+ dynArrPrivatKontakte.get(4).kind.get(1).getVorname() + " "
				+ dynArrPrivatKontakte.get(4).kind.get(1).getNachname());

		System.out.println("Kinder: "+dynArrPrivatKontakte.get(4).getVorname() + " "
				+ dynArrPrivatKontakte.get(4).getNachname()+ " <--> "
				+ dynArrPrivatKontakte.get(4).kind.get(2).getVorname() + " "
				+ dynArrPrivatKontakte.get(4).kind.get(2).getNachname());
		
		System.out.println("Anzahl Kontakte: " + dynArrPrivatKontakte.getElementCount());
	}

}
