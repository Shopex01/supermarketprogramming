package GUI;

import Objects.Goods.Good;
import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static Objects.Goods.GoodCategoryEnumerations.*;
import static Objects.Goods.GoodCategoryEnumerations.OTHER;
import static Objects.Goods.GoodPropertyEnumerations.*;

public class Backend {

	protected static final DefaultTableModel model_wListe = new DefaultTableModel(new String[] {"Warenkorbname", "Warenkorbart", "Preis"}, 0);
	protected static final DefaultTableModel model_pListe = new DefaultTableModel(new String[] {"Artikel", "Kategorie", "Preis"}, 0);
	protected static final DefaultTableModel model_warenkorb = new DefaultTableModel(new String[] {"Artikel", "Menge", "Preis"}, 0);

	private static ArrayList<ShoppingCart> PR_S_shopCarts;
	private String status_produkt = "";
	private String status_warenkorb = "";
	private String status_warenkorbliste = "";
	protected String statusOverall = "";
	protected static String cart_stat_newCart = "";
	protected static String cart_stat_newProd = "";
	protected static String cart_stat_deleteProd = "";

	private static boolean stat_newProdInCart =false;
	private static boolean stat_deleteProdFromCart=false;
	private static boolean stat_newCart = false;
	
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
		if(name != null) {
			ShoppingCart cart = new ShoppingCart(category, name);
			PR_S_shopCarts.add(cart);
			model_wListe.addRow(new Object[]{cart.getPR_S_Name(),cart.getPR_SCE_ShoppingCartTypeString(),cart.getPR_LSI_ShoppingCartOverallValue()});	//addElement(cart.getListName());
			cart_stat_newCart = (cart.getPR_S_Name());
		} else {
			stat_newCart=false;
			return false;
		}
		stat_newCart=true;
		return true;
	}

	public boolean addShoppingCart(ShoppingCart TObject) {
		return PR_S_shopCarts.add(TObject);
	}

	public String statusOverall(){
		if(stat_newCart==true){
			return "Ihr Warenkorb: "+ cart_stat_newCart +" wurde erstellt.";
		} else if(stat_newProdInCart==true){
			return "Artikel XY wurde in den Warenkorb "+ cart_stat_newCart +" hinzugefügt.";
		} else if(stat_deleteProdFromCart==true){
			return "Artikel XY wurde aus "+ cart_stat_newCart +" entfernt.";
		}
		return statusOverall;
	}

	public static ArrayList<Good> allGoods(){
		ArrayList<Good> goods = new ArrayList();

		goods.add(new Good(1, FOOD, "Mineralwasser", 0.40, 0.89, EXPIRATIONDATE));
		goods.add(new Good(2, FOOD, "Toastbrot", 0.50, 1.99, EXPIRATIONDATE));
		goods.add(new Good(3, FOOD, "Butter", 0.39, 1.49, EXPIRATIONDATE));
		goods.add(new Good(4, FOOD, "Wurst", 0.69, 1.99, EXPIRATIONDATE));
		goods.add(new Good(5, FOOD, "Käse", 0.49, 1.29, EXPIRATIONDATE));
		goods.add(new Good(6, FOOD, "Flasche Wein", 2.30, 6.99, EXPIRATIONDATE));
		goods.add(new Good(7, HOUSEHOLD, "Klobürste", 0.99, 4.99, RECYCLING));
		goods.add(new Good(8, HOUSEHOLD, "Plastikbesteck", 0.05, 0.69, RECYCLING));
		goods.add(new Good(9, HOUSEHOLD, "Putzlappen", 0.15, 1.19, RECYCLING));
		goods.add(new Good(10, HOUSEHOLD, "Zahncreme", 0.50, 1.99, RECYCLING));
		goods.add(new Good(11, OTHER, "DVD Actionfilm", 0.99, 8.99, FSK));
		goods.add(new Good(12, OTHER, "DVD Familienfilm", 0.89, 7.99, FSK));

		/*for (Good i : goods) {
			model_pListe.addRow(new Object[]{});
		}*/
		return goods;
	}

}
