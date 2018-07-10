package klassen;

public class Kontakt {

	private int kontaktId = 0;
	private String vorname = null;
	private String nachname = null;

	public int getKontaktId() {
		return kontaktId;
	}

	public void setKontaktId(int kontaktId) {
		this.kontaktId = kontaktId;
	}

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

	public Kontakt(int kontaktId, String vorname, String nachname) {
		this.setKontaktId(kontaktId);
		this.setVorname(vorname);
		this.setNachname(nachname);

	}

}
