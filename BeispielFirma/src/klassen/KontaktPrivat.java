package klassen;


public class KontaktPrivat extends Kontakt {

	private String vorname=null;
	private String nachname=null;
	private String staatsangehoerigkeit=null;
	private DynGenArray<Termin> termin = new DynGenArray<Termin>(1);
	private DynGenArray<String> telefonNr = new DynGenArray<String>(1);
	public DynGenArray<KontaktPrivat> partner = new DynGenArray<KontaktPrivat>(1);
	public DynGenArray<KontaktPrivat> kind = new DynGenArray<KontaktPrivat>(1);
	
	public void setTermin(int pos, String strBeschreibung,String strDatum) {
		Termin t = new Termin();
		t.setDatum(strDatum);
		t.setBeschreibung(strBeschreibung);
		this.termin.put(pos, t);
	}

	public Termin getTermin(int pos) {
		return this.termin.get(pos);
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getStaatsangehoerigkeit() {
		return staatsangehoerigkeit;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setStaatsangehoerigkeit(String staatsangehoerigkeit) {
		this.staatsangehoerigkeit = staatsangehoerigkeit;
	}

	public String getTelefonNr(int pos) {
		return this.telefonNr.get(pos);
	}

	public void setTelefonNr(int pos, String telefonNr) {
		this.telefonNr.put(pos, telefonNr);
	}
	
	public KontaktPrivat(int kontaktId, String vorname, String nachname) {
		super(kontaktId);
		this.setVorname(vorname);
		this.setNachname(nachname);
	}

	public KontaktPrivat(int kontaktId) {
		this(kontaktId, null, null);
	}

	@Override
	public String toString() {
		return "KontakId: " + getKontaktId() + " Vorname: " + this.vorname + " Nachname: " + this.nachname;

	}

}
