package klassen;

public class Kontakt {
	private int pos = 0;
	private String vorname = null;
	private String nachname = null;

	public int getPos() {
		return pos;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Kontakt(int pos, String vorname, String nachname) {
		this.setPos(pos);
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
