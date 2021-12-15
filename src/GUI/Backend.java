package GUI;

import Objects.Goods.Good;
import Objects.ShoppingCart.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import static Objects.Goods.GoodCategoryEnumerations.*;
import static Objects.Goods.GoodCategoryEnumerations.OTHER;
import static Objects.Goods.GoodPropertyEnumerations.*;

public class Backend {

	protected static final DefaultTableModel PT_SF_DTM_ShoppingCartListModel = new DefaultTableModel(new String[] {"ID","Warenkorbname", "Warenkorbart", "Preis"}, 0);
	protected static final DefaultTableModel PT_SF_DTM_ProductListModel = new DefaultTableModel(new String[] {"ID", "Kategorie","Produkt", "Preis","Weitere Eigenschaft"}, 0);
	protected static final DefaultTableModel PT_SF_DTM_ShoppingCartModel = new DefaultTableModel(new String[] {"ID","Artikel", "Menge", "Preis", "Weitere Eigenschaft"}, 0);

	private static ArrayList<ShoppingCart> PR_S_AL_ShoppingCarts;
	private static ArrayList<Good> PR_S_AL_Goods;

	private int PR_I_ShoppingCartCounter;
	private ShoppingCart PR_SC_SelectedShoppingCart;
	private double PR_D_DailyOverallSellValue = 0;

	public static final DecimalFormat PU_SF_DF_DoubleFormat = new DecimalFormat("0.00");

	protected String PT_S_StatusOverall = "";
	protected static String PT_S_S_StatusNewCart = "";
	protected static String PT_S_S_StatusNewShoppingItem = "";
	protected static String PT_S_S_StatusDeleteShoppingItem = "";
	private static boolean PR_S_B_StatusNewShoppingItem =false;
	private static boolean PR_S_B_StatusDeleteShoppingItem=false;
	private static boolean PR_S_B_StatusNewCart = false;

	public Backend() {
		initialize();
		allGoods();
		refresh();
	}

	private void initialize() {
		PR_S_AL_ShoppingCarts = new ArrayList<>();
		PR_I_ShoppingCartCounter = 0;
		PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
	}

	private void allGoods(){
		PR_S_AL_Goods = new ArrayList<>();

		PR_S_AL_Goods.add(new Good(1, FOOD, "Mineralwasser", 0.40, 0.89, EXPIRATIONDATE, "01.01.2030"));
		PR_S_AL_Goods.add(new Good(2, FOOD, "Toastbrot", 0.50, 1.99, EXPIRATIONDATE, "10.01.2022"));
		PR_S_AL_Goods.add(new Good(3, FOOD, "Butter", 0.39, 1.49, EXPIRATIONDATE, "31.06.2022"));
		PR_S_AL_Goods.add(new Good(4, FOOD, "Wurst", 0.69, 1.99, EXPIRATIONDATE,"31.12.2021"));
		PR_S_AL_Goods.add(new Good(5, FOOD, "Käse", 0.49, 1.29, EXPIRATIONDATE,"02.01.2022"));
		PR_S_AL_Goods.add(new Good(6, FOOD, "Flasche Wein", 2.30, 6.99, EXPIRATIONDATE,"05.07.2026"));
		PR_S_AL_Goods.add(new Good(7, HOUSEHOLD, "Klobürste", 0.99, 4.99, RECYCLING,"50%"));
		PR_S_AL_Goods.add(new Good(8, HOUSEHOLD, "Plastikbesteck", 0.05, 0.69, RECYCLING,"80%"));
		PR_S_AL_Goods.add(new Good(9, HOUSEHOLD, "Putzlappen", 0.15, 1.19, RECYCLING,"25%"));
		PR_S_AL_Goods.add(new Good(10, HOUSEHOLD, "Zahncreme", 0.50, 1.99, RECYCLING,"10%"));
		PR_S_AL_Goods.add(new Good(11, OTHER, "DVD Actionfilm", 0.99, 8.99, FSK,"16"));
		PR_S_AL_Goods.add(new Good(12, OTHER, "DVD Familienfilm", 0.89, 7.99, FSK,"12"));
	}

	private void refresh(){
		refreshGoodsTable(); //Refresh Produktliste
		refreshShoppingCartItemTable(); //Refresh Warenkorb
	}

	private void refreshGoodsTable() {
		int j = PT_SF_DTM_ProductListModel.getRowCount();
		for(int i=0;i<j;i++){
			PT_SF_DTM_ProductListModel.removeRow(0);
		}
		switch (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()) {
			case ECONOMIC -> {
				for(Good G: PR_S_AL_Goods){
					if (G.getPR_F_I_number()!=4 && G.getPR_F_I_number()!=8) {
						PT_SF_DTM_ProductListModel.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),PU_SF_DF_DoubleFormat.format(G.getPR_F_BD_sellvalue())+"EUR",G.getPR_F_GPE_propertyString()+G.getPR_F_GPE_propertyValue()});
					}
				}
			}
			case U18 -> {
				for(Good G: PR_S_AL_Goods){
					if (G.getPR_F_I_number()!=6 && G.getPR_F_I_number()!=11) {
						PT_SF_DTM_ProductListModel.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),PU_SF_DF_DoubleFormat.format(G.getPR_F_BD_sellvalue())+"EUR",G.getPR_F_GPE_propertyString()+G.getPR_F_GPE_propertyValue()});
					}
				}
			}
			case EMPLOYEE -> {
				for(Good G: PR_S_AL_Goods){
					PT_SF_DTM_ProductListModel.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),PU_SF_DF_DoubleFormat.format(G.getPR_F_BD_purchasevalue())+"EUR",G.getPR_F_GPE_propertyString()+G.getPR_F_GPE_propertyValue()});
				}
			}
			case STANDARD,SAVING -> {
				for(Good G: PR_S_AL_Goods){
					PT_SF_DTM_ProductListModel.addRow(new Object[]{G.getPR_F_I_number(), G.getPR_F_GCE_categoryString(), G.getPR_F_S_name(),PU_SF_DF_DoubleFormat.format(G.getPR_F_BD_sellvalue())+"EUR",G.getPR_F_GPE_propertyString()+G.getPR_F_GPE_propertyValue()});
				}
			}
		}
		refreshShoppingCartTable();
	}

	private void refreshShoppingCartItemTable() {
		int j = PT_SF_DTM_ShoppingCartModel.getRowCount();
		for(int i=0;i<j;i++){
			PT_SF_DTM_ShoppingCartModel.removeRow(0);
		}
		for (ShoppingItem SI: PR_SC_SelectedShoppingCart.getPR_LSI_ShoppingCart()) {
			double TPrice = (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()==ShoppingCartEnumeration.EMPLOYEE) ? SI.getPR_G_Item().getPR_F_BD_purchasevalue() : SI.getPR_G_Item().getPR_F_BD_sellvalue();
			PT_SF_DTM_ShoppingCartModel.addRow(new Object[]{SI.getPR_G_Item().getPR_F_I_number(),SI.getPR_G_Item().getPR_F_S_name(),SI.getPR_I_Amount(),PU_SF_DF_DoubleFormat.format(SI.getPR_I_Amount()*TPrice)+"EUR", SI.getPR_G_Item().getPR_F_GPE_propertyString()+SI.getPR_G_Item().getPR_F_GPE_propertyValue()});
		}
		refreshShoppingCartTable();
	}

	private void refreshShoppingCartTable() {
		saveCurrentShoppingCartInList();
		int j = PT_SF_DTM_ShoppingCartListModel.getRowCount();
		for(int i=0;i<j;i++){
			PT_SF_DTM_ShoppingCartListModel.removeRow(0);
		}
		for (ShoppingCart SC: PR_S_AL_ShoppingCarts) {
			PT_SF_DTM_ShoppingCartListModel.addRow(new Object[]{SC.getPR_F_I_ID(),SC.getPR_S_Name(),SC.getPR_SCE_ShoppingCartTypeString(),PU_SF_DF_DoubleFormat.format(SC.getPR_LSI_ShoppingCartOverallValueDouble())+"EUR"});
		}
	}

	private Good getGoodfromGoodArray(int TID) {
		for (Good G: PR_S_AL_Goods) {
			if (G.getPR_F_I_number()==TID) {
				return G;
			}
		}
		return null;
	}

	public ShoppingCart getPR_SC_SelectedShoppingCart() {
		return PR_SC_SelectedShoppingCart;
	}

	protected static ShoppingCart createCart(ShoppingCartEnumeration category, String name, int ID) {
		if(name != null && !name.equals("")) {
			ShoppingCart cart = new ShoppingCart(category, name,ID);
			PR_S_AL_ShoppingCarts.add(cart);
			PT_SF_DTM_ShoppingCartListModel.addRow(new Object[]{cart.getPR_F_I_ID(),cart.getPR_S_Name(),cart.getPR_SCE_ShoppingCartTypeString(),cart.getPR_LSI_ShoppingCartOverallValue()});
			PR_S_B_StatusNewCart=true;
			PT_S_S_StatusNewCart = (cart.getPR_S_Name());
			return cart;
		} else {
			return null;
		}
	}

	public String statusOverall(){
		if(PR_S_B_StatusNewCart){
			PR_S_B_StatusNewCart=false;
			return "Ihr Warenkorb: "+ PT_S_S_StatusNewCart +" wurde erstellt.";
		} else if(PR_S_B_StatusNewShoppingItem){
			PR_S_B_StatusNewShoppingItem=false;
			return "Artikel "+PT_S_S_StatusNewShoppingItem+" wurde in den Warenkorb "+PT_S_S_StatusNewCart+" hinzugefügt.";
		} else if(PR_S_B_StatusDeleteShoppingItem){
			PR_S_B_StatusDeleteShoppingItem=false;
			return "Artikel "+PT_S_S_StatusDeleteShoppingItem+" wurde aus "+PT_S_S_StatusNewCart+" entfernt.";
		}
		return PT_S_StatusOverall;
	}

	public void switchShoppingCart(int ID) {
		if (saveCurrentShoppingCartInList()) {
			//Selektierter Warenkorb aus der ArrayList ziehen!
			for (ShoppingCart pr_al_shopCart : PR_S_AL_ShoppingCarts) {
				if (pr_al_shopCart.getPR_F_I_ID() == ID) {
					PR_SC_SelectedShoppingCart = pr_al_shopCart;
					refresh();
				}
			}
		}
	}


	public boolean saveCurrentShoppingCartInList() {
		for (int i=0; i<PR_S_AL_ShoppingCarts.size();i++) {
			//Aktueller Warenkorb in ArrayList speichern!
			if (PR_S_AL_ShoppingCarts.get(i).getPR_F_I_ID() == PR_SC_SelectedShoppingCart.getPR_F_I_ID()) {
				PR_S_AL_ShoppingCarts.set(i, PR_SC_SelectedShoppingCart);
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
						PR_S_B_StatusNewShoppingItem=true;
						PT_S_S_StatusNewShoppingItem=TGood.getPR_F_S_name();
					} else {
						JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
					}
					return;

				}
			}
			if (ControlShoppingCartValue(TGood.getPR_F_I_number(),TAmount,1)) {
				PR_SC_SelectedShoppingCart.addShoppingItem(new ShoppingItem(TGood,TAmount));
				refreshShoppingCartItemTable();
				PR_S_B_StatusNewShoppingItem=true;
				PT_S_S_StatusNewShoppingItem=TGood.getPR_F_S_name();
			} else {
				JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void deleteShoppingItem(int TSINumber){
		PR_SC_SelectedShoppingCart.removeShoppingItem(TSINumber);
		PR_S_B_StatusDeleteShoppingItem = true;
		PT_S_S_StatusDeleteShoppingItem = Objects.requireNonNull(getGoodfromGoodArray(TSINumber)).getPR_F_S_name();
		PT_S_S_StatusNewCart = PR_SC_SelectedShoppingCart.getPR_S_Name();
		refreshShoppingCartItemTable();
	}

	public void changeShoppingItemAmount(int TSINumber, int TAmount){
		if (ControlShoppingCartValue(TSINumber,TAmount,3)) {
			PR_SC_SelectedShoppingCart.changeShoppingItemAmount(TSINumber,TAmount);
			refreshShoppingCartItemTable();
		} else {
			JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
		}
	}

	public void removeCurrentShoppingCart() {
		PR_S_AL_ShoppingCarts.removeIf(SC -> SC == PR_SC_SelectedShoppingCart);
		if(PR_S_AL_ShoppingCarts.size()==0) {
			PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
		} else {
			PR_SC_SelectedShoppingCart = PR_S_AL_ShoppingCarts.get(0);
		}
		refresh();
	}

	private boolean ControlShoppingCartValue(int TSINumber, int TAmount, int TOperation) {
		if (PR_SC_SelectedShoppingCart.getPR_SCE_ShoppingCartType()==ShoppingCartEnumeration.SAVING) {
			for (Good G : PR_S_AL_Goods) {
				if (G.getPR_F_I_number() == TSINumber) {
					ShoppingCart TShoppingCart = new ShoppingCart(ShoppingCartEnumeration.SAVING,"Clone",-1);
					for (ShoppingItem SI: PR_SC_SelectedShoppingCart.getPR_LSI_ShoppingCart()) {
						TShoppingCart.addShoppingItem(new ShoppingItem(new Good(SI.getPR_G_Item().getPR_F_I_number(),SI.getPR_G_Item().getPR_F_GCE_category(),SI.getPR_G_Item().getPR_F_S_name(),SI.getPR_G_Item().getPR_F_BD_purchasevalue(),SI.getPR_G_Item().getPR_F_BD_sellvalue(),SI.getPR_G_Item().getPR_F_GPE_property(),SI.getPR_G_Item().getPR_F_GPE_propertyValue()),SI.getPR_I_Amount()));
					}
					switch (TOperation) {
						case 1 -> TShoppingCart.addShoppingItem(new ShoppingItem(G, TAmount)); //Hinzufügen von Item
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

	public Good getGood(int TID) {
		for (Good g: PR_S_AL_Goods) {
			if (g.getPR_F_I_number()==TID) {
				return g;
			}
		}
		return null;
	}

	public void increasePR_D_DailyOverallSellValue(double Value){
		PR_D_DailyOverallSellValue += Value;
	}

	public String addToDailyTakings(){
		if(PR_D_DailyOverallSellValue==0){
			return "0,00EUR";
		}
		return PU_SF_DF_DoubleFormat.format(PR_D_DailyOverallSellValue)+"EUR";
	}
}
