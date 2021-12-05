package GUI;

import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.*;
import java.util.ArrayList;

public class Backend {

	private static final DefaultListModel<String> PR_Model = new DefaultListModel<>();
	private static ArrayList<ShoppingCart> PR_S_shopCarts;
	private String status_produkt = "";
	private String status_warenkorb = "";
	private String status_warenkorbliste = "";
	
	public String output_produkt() {		
		return status_produkt;
	}

	public String output_warenkorb() {
		return status_warenkorb;
	}

	public String output_warenkorbliste() {
		return status_warenkorbliste;
	}

	public Backend() {
		PR_S_shopCarts = new ArrayList<>();
	}

	public DefaultListModel<String> getModel() {
		return PR_Model;
	}

	protected static boolean createCart(ShoppingCartEnumeration category, String name) {
		if(name != null) {
			ShoppingCart cart = new ShoppingCart(category, name);
			PR_S_shopCarts.add(cart);
			PR_Model.addElement(cart.getListName());
		} else {
			return false;
		}
		return true;
	}

	public boolean addShoppingCart(ShoppingCart TObject) {
		return PR_S_shopCarts.add(TObject);
	}
}
