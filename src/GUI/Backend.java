package GUI;

import Objects.Goods.Good;
import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;
import Objects.ShoppingCart.ShoppingItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static Objects.Goods.GoodCategoryEnumerations.*;
import static Objects.Goods.GoodCategoryEnumerations.OTHER;
import static Objects.Goods.GoodPropertyEnumerations.*;

public class Backend {

	protected static final DefaultTableModel model_wListe = new DefaultTableModel(new String[] {"ID","Warenkorbname", "Warenkorbart", "Preis"}, 0);
	protected static final DefaultTableModel model_pListe = new DefaultTableModel(new String[] {"ID", "Kategorie","Produkt", "Preis","Weitere Eigenschaft"}, 0);
	protected static final DefaultTableModel model_warenkorb = new DefaultTableModel(new String[] {"ID","Artikel", "Menge", "Preis"}, 0);

	private static ArrayList<ShoppingCart> PR_AL_shopCarts;
	private static ArrayList<Good> PR_AL_Goods;

	private ShoppingCart PR_SC_SelectedShoppingCart;
	private double PR_D_DailyOverallSellValue = 0;

	private String status_produkt = "";
	private String status_warenkorb = "";
	private String status_warenkorbliste = "";
	protected String statusOverall = "";
	protected static String cart_stat_newCart = "";
	protected static String cart_stat_newProd = "";
	protected static String cart_stat_deleteProd = "";
	private int PR_I_ShoppingCartCounter;

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
		refreshShoppingCartItemTable(); //Refresh Warenkorb
	}

	private void refreshGoodsTable() {
		int j = model_pListe.getRowCount();
		for(int i=0;i<j;i++){
			model_pListe.removeRow(0);
		}
		switch (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()) {
			case ECONOMIC -> {
				for(Good G: PR_AL_Goods){
					if (G.getPR_F_I_number()!=4 && G.getPR_F_I_number()!=8) {
						model_pListe.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),G.getPR_F_BD_sellvalue(),G.getPR_F_GPE_propertyString()});
					}
				}
			}
			case U18 -> {
				for(Good G: PR_AL_Goods){
					if (G.getPR_F_I_number()!=6 && G.getPR_F_I_number()!=11) {
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
		refreshShoppingCartTable();
	}

	private void refreshShoppingCartItemTable() {
		int j = model_warenkorb.getRowCount();
		for(int i=0;i<j;i++){
			model_warenkorb.removeRow(0);
		}
		for (ShoppingItem SI: PR_SC_SelectedShoppingCart.getPR_LSI_ShoppingCart()) {
			double TPrice = (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()==ShoppingCartEnumeration.EMPLOYEE) ? SI.getPR_G_Item().getPR_F_BD_purchasevalue() : SI.getPR_G_Item().getPR_F_BD_sellvalue();
			model_warenkorb.addRow(new Object[]{SI.getPR_G_Item().getPR_F_I_number(),SI.getPR_G_Item().getPR_F_S_name(),SI.getPR_I_Amount(),Double.toString(SI.getPR_I_Amount()*TPrice)});
		}
		refreshShoppingCartTable();
	}

	private void refreshShoppingCartTable() {
		saveCurrentShoppingCartInList();
		int j = model_wListe.getRowCount();
		for(int i=0;i<j;i++){
			model_wListe.removeRow(0);
		}
		for (ShoppingCart SC: PR_AL_shopCarts) {
			model_wListe.addRow(new Object[]{SC.getPR_F_I_ID(),SC.getPR_S_Name(),SC.getPR_SCE_ShoppingCartTypeString(),SC.getPR_LSI_ShoppingCartOverallValue()});
		}
	}

	private void initialize() {
		PR_AL_shopCarts = new ArrayList<>();
		PR_I_ShoppingCartCounter = 0;
		PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
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

	private Good getGoodfromArray(int TID) {
		for (Good G: PR_AL_Goods) {
			if (G.getPR_F_I_number()==TID) {
				return G;
			}
		}
		return null;
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

	protected static ShoppingCart createCart(ShoppingCartEnumeration category, String name, int ID) {
		if(name != null && !name.equals("")) {
			ShoppingCart cart = new ShoppingCart(category, name,ID);
			PR_AL_shopCarts.add(cart);
			model_wListe.addRow(new Object[]{cart.getPR_F_I_ID(),cart.getPR_S_Name(),cart.getPR_SCE_ShoppingCartTypeString(),cart.getPR_LSI_ShoppingCartOverallValue()});
			stat_newCart=true;
			cart_stat_newCart = (cart.getPR_S_Name());
			return cart;
		} else {
			return null;
		}
	}

	public String statusOverall(){
		if(stat_newCart){
			stat_newCart=false;
			return "Ihr Warenkorb: "+ cart_stat_newCart +" wurde erstellt.";
		} else if(stat_newProdInCart){
			stat_newProdInCart=false;
			return "Artikel "+cart_stat_newProd+" wurde in den Warenkorb "+ cart_stat_newCart +" hinzugefügt.";
		} else if(stat_deleteProdFromCart){
			stat_deleteProdFromCart=false;
			return "Artikel "+cart_stat_deleteProd+" wurde aus "+ cart_stat_newCart +" entfernt.";
		}
		return statusOverall;
	}

	public boolean switchShoppingCart(int ID) {
		if (saveCurrentShoppingCartInList()) {
			//Selektierter Warenkorb aus der ArrayList ziehen!
			for (ShoppingCart pr_al_shopCart : PR_AL_shopCarts) {
				if (pr_al_shopCart.getPR_F_I_ID() == ID) {
					PR_SC_SelectedShoppingCart = pr_al_shopCart;
					refresh();
					return true;
				}
			}
		}
		return false;
	}


	public boolean saveCurrentShoppingCartInList() {
		for (int i=0; i<PR_AL_shopCarts.size();i++) {
			//Aktueller Warenkorb in ArrayList speichern!
			if (PR_AL_shopCarts.get(i).getPR_F_I_ID() == PR_SC_SelectedShoppingCart.getPR_F_I_ID()) {
				PR_AL_shopCarts.set(i, PR_SC_SelectedShoppingCart);
				return true;
			}
		}
		return false;
	}

	public int getPR_I_ShoppingCartCounter() {
		return PR_I_ShoppingCartCounter++;
	}

	public void addProductToShoppingCart(Good TGood, int TAmount) {
		if (TAmount!=0) {
			for (ShoppingItem SI:PR_SC_SelectedShoppingCart.getPR_LSI_ShoppingCart()) {
				if (SI.getPR_G_Item()==TGood) {
					if (ControlShoppingCartValue(SI.getPR_G_Item().getPR_F_I_number(),TAmount,2)) {
						SI.setPR_I_Amount(SI.getPR_I_Amount()+TAmount);
						refreshShoppingCartItemTable();
						stat_newProdInCart=true;
						cart_stat_newProd=TGood.getPR_F_S_name();
					} else {
						JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
					}
					return;

				}
			}
			if (ControlShoppingCartValue(TGood.getPR_F_I_number(),TAmount,1)) {
				PR_SC_SelectedShoppingCart.addShoppingItem(new ShoppingItem(TGood,TAmount));
				refreshShoppingCartItemTable();
				stat_newProdInCart=true;
				cart_stat_newProd=TGood.getPR_F_S_name();
			} else {
				JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public Good getGood(int TID) {
		for (Good g: PR_AL_Goods) {
			if (g.getPR_F_I_number()==TID) {
				return g;
			}
		}
		return null;
	}

	public void changeShoppingItemAmount(int TSINumber, int TAmount){
		if (ControlShoppingCartValue(TSINumber,TAmount,3)) {
			PR_SC_SelectedShoppingCart.changeShoppingItemAmount(TSINumber,TAmount);
			refreshShoppingCartItemTable();
		} else {
			JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
		}
	}

	public void deleteShoppingItem(int TSINumber){
		PR_SC_SelectedShoppingCart.removeShoppingItem(TSINumber);
		stat_deleteProdFromCart = true;
		cart_stat_deleteProd = Objects.requireNonNull(getGoodfromArray(TSINumber)).getPR_F_S_name();
		cart_stat_newCart = PR_SC_SelectedShoppingCart.getPR_S_Name();
		refreshShoppingCartItemTable();
	}

	private boolean ControlShoppingCartValue(int TSINumber, int TAmount, int TOperation) {
		if (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()==ShoppingCartEnumeration.SAVING) {
			for (Good G : PR_AL_Goods) {
				if (G.getPR_F_I_number() == TSINumber) {
					ShoppingCart TShoppingCart = new ShoppingCart(ShoppingCartEnumeration.SAVING,"Clone",-1);
					for (ShoppingItem SI: PR_SC_SelectedShoppingCart.getPR_LSI_ShoppingCart()) {
						TShoppingCart.addShoppingItem(new ShoppingItem(new Good(SI.getPR_G_Item().getPR_F_I_number(),SI.getPR_G_Item().getPR_F_GCE_category(),SI.getPR_G_Item().getPR_F_S_name(),SI.getPR_G_Item().getPR_F_BD_purchasevalue(),SI.getPR_G_Item().getPR_F_BD_sellvalue(),SI.getPR_G_Item().getPR_F_GPE_property()),SI.getPR_I_Amount()));
					}
					switch (TOperation) {
						case 1 -> { //Hinzufügen von Item
							TShoppingCart.addShoppingItem(new ShoppingItem(G, TAmount));
						}
						case 2 -> { //Änderung der Anzahl eines Items (CurrentAmount + TAmount)
							for (ShoppingItem SI : TShoppingCart.getPR_LSI_ShoppingCart()) {
								if (SI.getPR_G_Item().getPR_F_I_number() == G.getPR_F_I_number()) {
									SI.setPR_I_Amount(SI.getPR_I_Amount() + TAmount);
								}
							}
						}
						case 3 -> { //Änderung der Anzahl eines Items (CurrentAmount = TAmount)
							for (ShoppingItem SI : TShoppingCart.getPR_LSI_ShoppingCart()) {
								if (SI.getPR_G_Item().getPR_F_I_number() == G.getPR_F_I_number()) {
									SI.setPR_I_Amount(SI.getPR_I_Amount());
								}
							}
						}
					}
					return !(TShoppingCart.getPR_LSI_ShoppingCartOverallValueDouble() > 50);
				}
			}
			return false;
		}
		return true;
	}

	public void removeCurrentShoppingCart() {
		PR_AL_shopCarts.removeIf(SC -> SC == PR_SC_SelectedShoppingCart);
		if(PR_AL_shopCarts.size()==0) {
			PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
		} else {
			PR_SC_SelectedShoppingCart = PR_AL_shopCarts.get(0);
		}
		refresh();
	}

	public void increasePR_D_DailyOverallSellValue(double Value){
		PR_D_DailyOverallSellValue += Value;
	}

	public double getPR_D_DailyOverallSellValue() {
		return PR_D_DailyOverallSellValue;
	}
}
