package klassen;

public class KontaktGeschaeftlich extends Kontakt{
	private String firmenname;
	

	public String getFirmenname() {
		return firmenname;
	}


	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}


	public KontaktGeschaeftlich(int kontaktId, String firmenname) {
		super(kontaktId);
		this.firmenname = firmenname;
	}


	public KontaktGeschaeftlich(int kontaktId) {
		this(kontaktId, null);
	}

}
