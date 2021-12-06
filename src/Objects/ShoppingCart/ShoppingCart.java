package Objects.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert einen Einkaufswagen dar!
 *  @author Arthur Ladner
 *  @version 1.0
 */
public class ShoppingCart {

	private final String PR_S_Name; //Name des Einkaufswagens
    private final List<ShoppingItem> PR_LSI_ShoppingCart; //Liste des Typs "ShoppingItem", der alle Produkte mit dazugehöriger Anzahl
    private final ShoppingCartEnumeration PR_SCE_ShoppingCartType; //Kategorie des Einkaufswagens (Standard, Öko-Prinzip, U18, Mitarbeiterkaufprogramm, Spar-Korb)

    /**
     * Erstellt ein Einkaufswagen nach folgenden Parametern:
     * @param PR_SCE_ShoppingCartType Typ des Einkaufswagens
     * @param PR_S_Name Name des Einkaufswagens
     */
    public ShoppingCart(ShoppingCartEnumeration PR_SCE_ShoppingCartType, String PR_S_Name) {
        this.PR_LSI_ShoppingCart = new ArrayList<>();
        this.PR_SCE_ShoppingCartType = PR_SCE_ShoppingCartType;
        this.PR_S_Name = PR_S_Name;
    }

    /**
     * Gibt den Namen des Einkaufswagens zurück!
     * @return Name (Typ: String)
     */
    public String getPR_S_Name() {
		return PR_S_Name;
	}

    /**
     * Fügt ein ShoppingItem (Produkt mit Anzahl) in den Einkaufskorb hinzu!
     * @param TItem ShoppingItem (Produkt mit Anzahl)
     * @return Boolean-Wert, ob das ShoppingItem erfolgreich in der Liste hinzugefügt wurde!
     */
	public boolean addShoppingItem(ShoppingItem TItem) {
        return PR_LSI_ShoppingCart.add(TItem);
    }

    /**
     * Entfernt ein ShoppingItem (Produkt mit Anzahl) aus dem Einkaufskorb anhand der Produkt-ID!
     * @param TSINumber Produkt-ID
     * @return Boolean-Wert, ob das ShoppingItem erfolgreich in der Liste entfernt wurde!
     */
    public boolean removeShoppingItem(int TSINumber) {
        return PR_LSI_ShoppingCart.removeIf(T_SI_Item -> T_SI_Item.checkPRGItemID(TSINumber));
    }

    /**
     * Ändert die Anzahl eines ShoppingItems (Produkt mit Anzahl) aus dem Einkaufskorb anhand der Produkt-ID und der übergebenen Anzahl!
     * @param TSINumber Produkt-ID
     * @param TSIAmount neue Produkt - Anzahl
     * @return Boolean-Wert, ob die Anzahl des ShoppingItems erfolgreich verändert wurde!
     */
    public boolean changeShoppingItemAmount(int TSINumber,int TSIAmount) {
        for (ShoppingItem T_SI_Item: PR_LSI_ShoppingCart) {
            if (T_SI_Item.checkPRGItemID(TSINumber)) {
                T_SI_Item.setPR_I_Amount(TSIAmount);
                PR_LSI_ShoppingCart.set(PR_LSI_ShoppingCart.indexOf(T_SI_Item),T_SI_Item);
                return true;
            }
        }
        return false;
    }

    /**
     * Gibt das ShoppingItem (Produkt mit Anzahl) anhand der Produkt-ID aus!
     * @param TSINumber Produkt-ID
     * @return Produkt mit Anzahl (Typ: ShoppingItem)
     */
    public ShoppingItem getShoppingItem(int TSINumber) {
        for (ShoppingItem T_SI_Item: PR_LSI_ShoppingCart) {
            if (T_SI_Item.checkPRGItemID(TSINumber)) {
                return T_SI_Item;
            }
        }
        return null;
    }

    /**
     * Gibt die Kategorie des Einkaufswagens zurück!
     * @return Kategorie (Typ: ShoppingCartEnumeration)
     */
    public ShoppingCartEnumeration getPR_SCE_ShoppingCartType() {
        return PR_SCE_ShoppingCartType;
    }

    /**
     * Gibt die Kategorie des Einkaufswagens als Text aus!
     * @return Einkaufswagen-Kategorie (Typ: String)
     */
    public String getPR_SCE_ShoppingCartTypeString() {
        return switch (PR_SCE_ShoppingCartType) {
            case ECONOMIC -> "Öko-Prinzip";
            case U18 -> "U18";
            case EMPLOYEE -> "Mitarbeiterkaufprogramm";
            case SAVING -> "Spar-Korb";
            case STANDARD -> "Standard";
        };
    }

    /**
     * Gibt den Gesamtwert des Einkaufswagens zurück!
     * @return Gesamtwert (Typ: String)
     */
    public String getPR_LSI_ShoppingCartOverallValue() {
        if (PR_LSI_ShoppingCart.isEmpty())
        {
            return "0.00 EUR";
        }
        else {
            Double OValue = (double) 0;
            for (ShoppingItem i: PR_LSI_ShoppingCart) {
                if (PR_SCE_ShoppingCartType==ShoppingCartEnumeration.EMPLOYEE) {
                    OValue += i.getPR_G_Item().getPR_F_D_purchasevalue();
                } else {
                    OValue += i.getPR_G_Item().getPR_F_D_sellvalue();
                }
            }
            return OValue +" EUR";
        }
    }

    /**
     * Baut einen String kombiniert mit dem Namen, der Kategorie und dem Gesamtwert des Einkaufswagens zusammen!
     * @return Bezeichnung des Einkaufswagens (Name - Kategorie - Gesamtwert) (Typ: String)
     */
    public String getListName() {
        return PR_S_Name + " " + getPR_SCE_ShoppingCartTypeString() + " - " + getPR_LSI_ShoppingCartOverallValue();
    }
}
