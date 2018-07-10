package klassen;

public class Kontakt implements Cloneable  {

	private int kontaktId;

	public int getKontaktId() {
		return kontaktId;
	}

	public void setKontaktId(int kontaktId) {
		this.kontaktId = kontaktId;
	}

	public Kontakt(int kontaktId) {
		super();
		this.kontaktId = kontaktId;
	}
	
	@Override
   public String toString(){
		return "KontakId: " + this.kontaktId;

   }
	
	@Override
	public Kontakt clone() {
		Kontakt klon = null;
		try {
			klon = (Kontakt) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return klon;

	}
	
	 
}
