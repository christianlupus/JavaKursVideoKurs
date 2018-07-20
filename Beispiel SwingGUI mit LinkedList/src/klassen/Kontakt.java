package klassen;

public class Kontakt {
	private String vorname = null;
	private String nachname = null;

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Kontakt(String vorname, String nachname) {
		this.setVorname(vorname);
		this.setNachname(nachname);
	}

	@Override
	public String toString() {
		return " Vorname: " + this.vorname + " Nachname: " + this.nachname;

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Kontakt)) {
			return false;
		}
		Kontakt o = (Kontakt) obj;
		if (this.getVorname() != o.getVorname()) {
			return false;
		}
		if (this.getNachname() != o.getNachname()) {
			return false;
		}
		return true;
	}

}
