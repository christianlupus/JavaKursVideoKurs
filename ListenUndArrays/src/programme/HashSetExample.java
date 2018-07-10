package programme;

import java.util.HashSet;

import klassen.Kontakt;

public class HashSetExample {
	public static void main(String[] args) {
		HashSet<Kontakt> set = new HashSet<Kontakt>();
		set.add(new Kontakt(101, "Max", "Mustermann"));
		set.add(new Kontakt(102, "Donna", "Wetter"));
		set.add(new Kontakt(103, "Fred", "Feuerstein"));
		set.add(new Kontakt(104, "Werner", "Brösel"));

		for (Kontakt kont : set) {
			System.out.println("[" + kont.getKontaktId() + "]"
					+ kont.getNachname() + "." + kont.getVorname());
		}
		System.out.println("---");
		System.out.println("Anzahl Einträge: " + set.size());
		System.out.println("---");

		for (Kontakt kont : set) {
			if (kont.getKontaktId() == 104) { // ????
				set.remove(kont);
			}
		}

		for (Kontakt kont : set) {
			System.out.println("[" + kont.getKontaktId() + "]"
					+ kont.getNachname() + "." + kont.getVorname());
		}
		System.out.println("---");
		System.out.println("Anzahl Einträge: " + set.size());
		System.out.println("---");

	}
}