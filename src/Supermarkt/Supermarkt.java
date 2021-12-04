package Supermarkt;

public class Supermarkt{	
	
	String status_produkt = " Artikel XY x1 in Warenkorb XY eingetragen.";
	String status_warenkorb = " Gutschein XY angewandt / Artikel XY entfernt.";
	String status_warenkorbliste = " Warenkorb XY ausgewählt.";
	
	public String output_produkt() {		
		return status_produkt;
	}

	public String output_warenkorb() {
		return status_warenkorb;
	}

	public String output_warenkorbliste() {
		return status_warenkorbliste;
	}
	
	
}
