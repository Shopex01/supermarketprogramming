package GUI;

import Objects.Goods.Good;
import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

import static Objects.Goods.GoodCategoryEnumerations.*;
import static Objects.Goods.GoodCategoryEnumerations.OTHER;
import static Objects.Goods.GoodPropertyEnumerations.*;

public class Backend {

	protected static final DefaultTableModel model_wListe = new DefaultTableModel(new String[] {"Warenkorbname", "Warenkorbart", "Preis"}, 0);
	protected static final DefaultTableModel model_pListe = new DefaultTableModel(new String[] {"ID", "Kategorie","Produkt", "Preis","Weitere Eigenschaft"}, 0);
	protected static final DefaultTableModel model_warenkorb = new DefaultTableModel(new String[] {"Artikel", "Menge", "Preis"}, 0);

	private static ArrayList<ShoppingCart> PR_AL_shopCarts;
	private static ArrayList<Good> PR_AL_Goods;

	private ShoppingCart PR_SC_SelectedShoppingCart;

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
		initialize();
		allGoods();
		refresh();
	}

	private void refresh(){
		refreshGoodsTable(); //Refresh Produktliste
		refreshShoppingCartTable(); //Refresh Warenkorb
	}

	private void refreshGoodsTable() {
		for(int i=0;i<model_pListe.getRowCount();i++){
			model_pListe.removeRow(i);
		}
		switch (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()) {
			case ECONOMIC -> {
				for(Good G: PR_AL_Goods){
					if (Arrays.stream(new int[] {4, 8}).anyMatch(value -> value!=G.getPR_F_I_number())) {
						model_pListe.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),G.getPR_F_BD_sellvalue(),G.getPR_F_GPE_propertyString()});
					}
				}
			}
			case U18 -> {
				for(Good G: PR_AL_Goods){
					if (Arrays.stream(new int[] {6, 11}).anyMatch(value -> value!=G.getPR_F_I_number())) {
						model_pListe.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),G.getPR_F_BD_sellvalue(),G.getPR_F_GPE_propertyString()});
					}
				}
			}
			case EMPLOYEE -> {
				for(Good G: PR_AL_Goods){
					model_pListe.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),G.getPR_F_BD_purchasevalue(),G.getPR_F_GPE_propertyString()});
				}
			}
			case STANDARD,SAVING -> {
				for(Good G: PR_AL_Goods){
					model_pListe.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),G.getPR_F_BD_sellvalue(),G.getPR_F_GPE_propertyString()});
				}
			}
		}
	}

	private void refreshShoppingCartTable() {

	}

	private void initialize() {
		PR_AL_shopCarts = new ArrayList<>();
		PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb");
	}

	private void allGoods(){
		PR_AL_Goods = new ArrayList();

		PR_AL_Goods.add(new Good(1, FOOD, "Mineralwasser", 0.40, 0.89, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(2, FOOD, "Toastbrot", 0.50, 1.99, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(3, FOOD, "Butter", 0.39, 1.49, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(4, FOOD, "Wurst", 0.69, 1.99, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(5, FOOD, "Käse", 0.49, 1.29, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(6, FOOD, "Flasche Wein", 2.30, 6.99, EXPIRATIONDATE));
		PR_AL_Goods.add(new Good(7, HOUSEHOLD, "Klobürste", 0.99, 4.99, RECYCLING));
		PR_AL_Goods.add(new Good(8, HOUSEHOLD, "Plastikbesteck", 0.05, 0.69, RECYCLING));
		PR_AL_Goods.add(new Good(9, HOUSEHOLD, "Putzlappen", 0.15, 1.19, RECYCLING));
		PR_AL_Goods.add(new Good(10, HOUSEHOLD, "Zahncreme", 0.50, 1.99, RECYCLING));
		PR_AL_Goods.add(new Good(11, OTHER, "DVD Actionfilm", 0.99, 8.99, FSK));
		PR_AL_Goods.add(new Good(12, OTHER, "DVD Familienfilm", 0.89, 7.99, FSK));
	}

	public DefaultTableModel getModel() {
		return model_wListe;
	}

	public Integer getShoppingCartListLength(){
		return PR_AL_shopCarts.size();
	}

	public ShoppingCart getPR_SC_SelectedShoppingCart() {
		return PR_SC_SelectedShoppingCart;
	}

	public void setPR_SC_SelectedShoppingCart(ShoppingCart PR_SC_SelectedShoppingCart) {
		this.PR_SC_SelectedShoppingCart = PR_SC_SelectedShoppingCart;
	}

	protected static ShoppingCart createCart(ShoppingCartEnumeration category, String name) {
		if(name != null && !name.equals("")) {
			ShoppingCart cart = new ShoppingCart(category, name);
			PR_AL_shopCarts.add(cart);
			model_wListe.addRow(new Object[]{cart.getPR_S_Name(),cart.getPR_SCE_ShoppingCartTypeString(),cart.getPR_LSI_ShoppingCartOverallValue()});
			stat_newCart=true;
			cart_stat_newCart = (cart.getPR_S_Name());
			return cart;
		} else {
			stat_newCart=false;
			return null;
		}
	}

	public String statusOverall(){
		if(stat_newCart){
			return "Ihr Warenkorb: "+ cart_stat_newCart +" wurde erstellt.";
		} else if(stat_newProdInCart){
			return "Artikel XY wurde in den Warenkorb "+ cart_stat_newCart +" hinzugefügt.";
		} else if(stat_deleteProdFromCart){
			return "Artikel XY wurde aus "+ cart_stat_newCart +" entfernt.";
		}
		return statusOverall;
	}

}
