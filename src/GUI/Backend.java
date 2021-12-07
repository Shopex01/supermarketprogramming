package GUI;

import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Backend {

	//private static final DefaultListModel<String> PR_Model = new DefaultListModel<>();
	protected static final DefaultTableModel model_wListe = new DefaultTableModel(new String[] {"Warenkorbname", "Warenkorbart", "Preis"}, 0);
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

	public DefaultTableModel getModel() {
		return model_wListe;
	}

	public Integer getShoppingCartListLength(){
		return PR_S_shopCarts.size();
	}

	protected static boolean createCart(ShoppingCartEnumeration category, String name) {
		if(name != null) { //Test
			ShoppingCart cart = new ShoppingCart(category, name);
			PR_S_shopCarts.add(cart);
			model_wListe.addRow(new Object[]{cart.getPR_S_Name(),cart.getPR_SCE_ShoppingCartTypeString(),cart.getPR_LSI_ShoppingCartOverallValue()});	//addElement(cart.getListName());
		} else {
			return false;
		}
		return true;
	}

	public boolean addShoppingCart(ShoppingCart TObject) {
		return PR_S_shopCarts.add(TObject);
	}
}
