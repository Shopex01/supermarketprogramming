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

/**
 * Diese Klasse repräsentiert das Backend der GUI
 */
public class Backend {
	/**
	 * Model der Tabelle in dem Tab "Warenkorb-Liste"
	 */
	protected static final DefaultTableModel PT_SF_DTM_ShoppingCartListModel = new DefaultTableModel(new String[] {"ID","Warenkorbname", "Warenkorbart", "Preis"}, 0);
	/**
	 * Model der Tabelle in dem Tab "Produkte"
	 */
	protected static final DefaultTableModel PT_SF_DTM_ProductListModel = new DefaultTableModel(new String[] {"ID", "Kategorie","Produkt", "Preis","Weitere Eigenschaft"}, 0);
	/**
	 * Model der Tabele in dem Tab "Warenkorb"
	 */
	protected static final DefaultTableModel PT_SF_DTM_ShoppingCartModel = new DefaultTableModel(new String[] {"ID","Artikel", "Menge", "Preis", "Weitere Eigenschaft"}, 0);

	private static ArrayList<ShoppingCart> PR_S_AL_ShoppingCarts;
	private static ArrayList<Good> PR_S_AL_Goods;

	private int PR_I_ShoppingCartCounter;
	private ShoppingCart PR_SC_SelectedShoppingCart;
	/**
	 * Variable "Tageseinnahmen"
	 */
	private double PR_D_DailyOverallSellValue = 0;

	/**
	 * Format für alle Preiswerte
	 */
	public static final DecimalFormat PU_SF_DF_DoubleFormat = new DecimalFormat("0.00");

	/**
	 * Standard-Status "leer"
	 */
	protected String PT_S_StatusOverall = "";
	/**
	 * Status "Neuer Warenkorb"
	 */
	protected static String PT_S_S_StatusNewCart = "";
	/**
	 * Status "Neues ShoppingItem"
	 */
	protected static String PT_S_S_StatusNewShoppingItem = "";
	/**
	 * Status "ShoppingItem löschen"
	 */
	protected static String PT_S_S_StatusDeleteShoppingItem = "";
	/**
	 * Status-Boolean - Wert "Neues ShoppingItem"
	 */
	private static boolean PR_S_B_StatusNewShoppingItem =false;
	/**
	 * Status-Boolean - Wert "ShoppingItem löschen"
	 */
	private static boolean PR_S_B_StatusDeleteShoppingItem=false;
	/**
	 * Status-Boolean - Wert "Neuer Warenkorb"
	 */
	private static boolean PR_S_B_StatusNewCart = false;

	/**
	 * Erstellung des Backend-Objekts: Initialisierung, Erstellung aller Produkte in einer Liste, Reload der Models/Tabellen
	 */
	public Backend() {
		initialize();
		allGoods();
		refresh();
	}

	/**
	 * Initialisierung: Erstellung der Einkaufswagen-Liste, Einkaufswagen-ID-Counter initialisieren, Standard-Warenkorb erstellen und als aktuellen Warenkorb setzen
	 */
	private void initialize() {
		PR_S_AL_ShoppingCarts = new ArrayList<>();
		PR_I_ShoppingCartCounter = 0;
		PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
	}

	/**
	 * Erstellung der Produkte & Einfügen in der Produkt-Liste (nicht Frontend-Table)
	 */
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

	/**
	 * Aktualisierung der Produkt-Tabelle/Model & Warenkorb-Tabelle/Model
	 */
	private void refresh(){
		refreshGoodsTable(); //Refresh Produktliste
		refreshShoppingCartItemTable(); //Refresh Warenkorb
	}

	/**
	 * Aktualisierung der Produkt-Tabelle + Aufruf refreshShoppingCartTable()
	 */
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

	/**
	 * Aktualisierung der Warenkorb-Tabelle + Aufruf der refreshShoppingCartTable()
	 */
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

	/**
	 * Aktualisierung der Einkaufswagen-Liste - Tabelle
	 */
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

	/**
	 * Gibt das Produkt-Objekt aus dem Produkt-Array aus
	 * @param TID Produkt-ID
	 * @return Produkt (Typ: Good)
	 */
	private Good getGoodfromGoodArray(int TID) {
		for (Good G: PR_S_AL_Goods) {
			if (G.getPR_F_I_number()==TID) {
				return G;
			}
		}
		return null;
	}

	/**
	 * Gibt den aktuell ausgewählter Einkaufswagen aus!
	 * @return aktuell ausgewählter Einkaufswagen (Typ: ShoppingCart)
	 */
	public ShoppingCart getPR_SC_SelectedShoppingCart() {
		return PR_SC_SelectedShoppingCart;
	}

	/**
	 * Erstellt einen Einkaufswagen und gibt diese aus!
	 * @param category Einkaufswagen-Kategorie
	 * @param name Einkaufswagen-Name
	 * @param ID Einkaufswagen-ID
	 * @return Einkaufswagen-Objekt (Typ: ShoppingCart)
	 */
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

	/**
	 * Gibt den Status-String für die Status-Meldung (GUI Textfield unten) aus!
	 * @return Status-String (Typ: String)
	 */
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

	/**
	 * Wechselt den Einkaufswagen anhand der Einkaufswagen-ID
	 * @param ID neue Einkaufswagen-ID
	 */
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

	/**
	 * Speichert den aktuell ausgewählten Einkaufswagen in den Einkaufswagen-Array (nicht Model)
	 * @return Boolean-Wert (Wurde gespeichert oder nicht)
	 */
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

	/**
	 * Gibt den Einkaufswagen-Counter aus und erhöht diese um 1!
	 * @return Einkaufswagen-Counter
	 */
	public int getPR_I_ShoppingCartCounter() {
		return PR_I_ShoppingCartCounter++;
	}

	/**
	 * Fügt ein Produkt mit Anzahl in den Einkaufswagen hinzu!
	 * @param TGood Produkt
	 * @param TAmount Anzahl
	 */
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

	/**
	 * Löscht ein ShoppingItem aus dem aktuell ausgewähltem Einkaufskorb anhand der Produkt-ID
	 * @param TSINumber Produkt-ID
	 */
	public void deleteShoppingItem(int TSINumber){
		PR_SC_SelectedShoppingCart.removeShoppingItem(TSINumber);
		PR_S_B_StatusDeleteShoppingItem = true;
		PT_S_S_StatusDeleteShoppingItem = Objects.requireNonNull(getGoodfromGoodArray(TSINumber)).getPR_F_S_name();
		PT_S_S_StatusNewCart = PR_SC_SelectedShoppingCart.getPR_S_Name();
		refreshShoppingCartItemTable();
	}

	/**
	 * Ändert die Anzahl eines ShoppingItems aus dem aktuell ausgewähltem Einkaufskorb anhand der Produkt-ID
	 * @param TSINumber Produkt-ID
	 * @param TAmount  neue Anzahl
	 */
	public void changeShoppingItemAmount(int TSINumber, int TAmount){
		if (ControlShoppingCartValue(TSINumber,TAmount,3)) {
			PR_SC_SelectedShoppingCart.changeShoppingItemAmount(TSINumber,TAmount);
			refreshShoppingCartItemTable();
		} else {
			JOptionPane.showMessageDialog(null,"Der Warenwert des Warenkorbs überschreitet die Spar-Korb - Einstellung (maximal 50€)","Produkt hinzufügen fehlgeschlagen!",JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Entfernt das aktuell ausgewählte ShoppingCart und wählt den ShoppingCart aus der Liste auf Position 0 hinzu (wenn keines vorhanden, wird ein neuer Standard-Warenkorb erstellt)
	 */
	public void removeCurrentShoppingCart() {
		PR_S_AL_ShoppingCarts.removeIf(SC -> SC == PR_SC_SelectedShoppingCart);
		if(PR_S_AL_ShoppingCarts.size()==0) {
			PR_SC_SelectedShoppingCart = createCart(ShoppingCartEnumeration.STANDARD,"Standard-Warenkorb",getPR_I_ShoppingCartCounter());
		} else {
			PR_SC_SelectedShoppingCart = PR_S_AL_ShoppingCarts.get(0);
		}
		refresh();
	}

	/**
	 * Überprüft den Warenwert des aktuellen Warenkorbs (nur bei Kategorie: SAVING), ob der Warenwert 50€ überschreitet!
	 * @param TSINumber Produkt-ID (das hinzugefügt wird)
	 * @param TAmount Anzahl des Produktes
	 * @param TOperation 1: Ein Produkt wird in der Liste hinzugefügt (Produkt vorher nicht im Warenkorb); 2: Anzahl eines bereits vorhandenen Produktes ändern (Doppelklick Produkt in der Produkt-Liste; Addieren der Anzahl); 3: Anzahl eines bereits vorhandenen Produktes ändern (Doppelklick Produkt in der Warenkorb-Liste; Ersetzen der Anzahl);
	 * @return Boolean-Wert, ob der Warenkorbwert 50€ überschreitet oder nicht (True: keine Überschreitung, False: Überschreitung)
	 */
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

	/**
	 * Gibt das Produkt anhand der Produkt-ID aus dem Produkt-Array aus!
	 * @param TID Produkt-ID
	 * @return Produkt (Typ: Good)
	 */
	public Good getGood(int TID) {
		for (Good g: PR_S_AL_Goods) {
			if (g.getPR_F_I_number()==TID) {
				return g;
			}
		}
		return null;
	}

	/**
	 * Erhöht die Tageseinnahmen!
	 * @param Value Wert, um der sich die Tageseinnahmen erhöht werden soll!
	 */
	public void increasePR_D_DailyOverallSellValue(double Value){
		PR_D_DailyOverallSellValue += Value;
	}

	/**
	 * Gibt die Tageseinnahmen formatiert als String aus!
	 * @return Tageseinnahmen (Typ: String)
	 */
	public String addToDailyTakings(){
		if(PR_D_DailyOverallSellValue==0){
			return "0,00EUR";
		}
		return PU_SF_DF_DoubleFormat.format(PR_D_DailyOverallSellValue)+"EUR";
	}
}
