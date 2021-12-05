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

	public Supermarkt() {
		shopCarts = new ArrayList<>();

	}

	public DefaultListModel<String> getModel() {
		return model;
	}
	protected static boolean createCart(ShoppingCartEnumeration category, String name) {
		if(name != null) {
			ShoppingCart cart = new ShoppingCart(category, name);
			shopCarts.add(cart);
			model.addElement(cart.getListName());
		} else {
			return false;
		}
		return true;
	}
}
