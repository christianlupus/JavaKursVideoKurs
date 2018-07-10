package klassen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Termin {
	
	private LocalDate datum;
	private String beschreibung;

	public String getDatum() {
        DateTimeFormatter format;		
 		format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return this.datum.format(format);
		//return this.datum;
	}
	public void setDatum(String strDatum) {
	       DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
	    	        FormatStyle.MEDIUM).withLocale(Locale.GERMAN); 
		this.datum = LocalDate.parse(strDatum, germanFormatter);
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public int getTerminJahr() {
		return this.datum.getYear();
	}
	
	public int getTerminMonat() {
		return this.datum.getMonthValue();
	}
	
	
	public void terminAendern(String beschreibung,String strDatum){
		setBeschreibung(beschreibung);
		setDatum(strDatum);
	}
	
	
	public Termin(String beschreibung,String strDatum) {
		super();
		setBeschreibung(beschreibung);
		setDatum(strDatum);
		
	}

	public Termin() {
		
	}
	
	
	
}
