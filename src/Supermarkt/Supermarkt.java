package Supermarkt;

import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.*;
import java.util.ArrayList;

public class Supermarkt{

	private static final DefaultListModel<String> model = new DefaultListModel<>();
	private static ArrayList<ShoppingCart> shopCarts;
	String status_produkt = "";
	String status_warenkorb = "";
	String status_warenkorbliste = "";
	
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
