package programme;

import klassen.Termin;

public class TesteTemin {

	public static void main(String[] args) {
	
		//Termin T = new Termin();
		//T.setDatum("01.07.2018");
		Termin T = new Termin("Geburtstag","24.12.1981");
		System.out.println(T.getDatum());
		System.out.println(T.getTerminJahr());
		System.out.println(T.getBeschreibung());

	}

}
