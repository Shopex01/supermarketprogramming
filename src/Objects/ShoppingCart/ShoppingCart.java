package Objects.ShoppingCart;

import GUI.Backend;

import java.util.ArrayList;

/**
 * Diese Klasse repräsentiert einen Einkaufswagen dar!
 *  @author Arthur Ladner
 *  @version 1.0
 */
public class ShoppingCart {
    private final int PR_F_I_ID; //ID des Einkaufswagens
    private final String PR_F_S_Name; //Name des Einkaufswagens
    /**
     * Liste des Typs "ShoppingItem", der alle Produkte mit dazugehöriger Anzahl
     */
    protected ArrayList<ShoppingItem> PR_LSI_ShoppingCart;
    private final ShoppingCartEnumeration PR_SCE_ShoppingCartType; //Kategorie des Einkaufswagens (Standard, Öko-Prinzip, U18, Mitarbeiterkaufprogramm, Spar-Korb)

    /**
     * Erstellt ein Einkaufswagen nach folgenden Parametern:
     * @param PR_SCE_ShoppingCartType Typ des Einkaufswagens
     * @param PR_S_Name Name des Einkaufswagens
     * @param PR_F_I_ID ID des Einkaufswagens
     */
    public ShoppingCart(ShoppingCartEnumeration PR_SCE_ShoppingCartType, String PR_S_Name, int PR_F_I_ID) {
        this.PR_LSI_ShoppingCart = new ArrayList<>();
        this.PR_SCE_ShoppingCartType = PR_SCE_ShoppingCartType;
        this.PR_F_S_Name = PR_S_Name;
        this.PR_F_I_ID = PR_F_I_ID;
    }

    /**
    * Gibt die ID des Einkaufswagens zurück!
    * @return ID (Typ: Integer)
    */
    public int getPR_F_I_ID() {
        return PR_F_I_ID;
    }

   /**
    * Gibt den Namen des Einkaufswagens zurück!
    * @return Name (Typ: String)
    */
    public String getPR_S_Name() {
		return PR_F_S_Name;
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
     * Gibt den Warenwert des Einkaufswagens zurück!
     * @return Warenwert (Typ: Double)
     */
    public double getPR_LSI_ShoppingCartOverallValueDouble() {
        if (PR_LSI_ShoppingCart.isEmpty())
        {
            return 0;
        }
        else {
            double OValue = 0;
            for (ShoppingItem i: PR_LSI_ShoppingCart) {
                if (PR_SCE_ShoppingCartType==ShoppingCartEnumeration.EMPLOYEE) {
                    OValue += i.getPR_G_Item().getPR_F_BD_purchasevalue()*i.getPR_I_Amount();
                } else {
                    OValue += i.getPR_G_Item().getPR_F_BD_sellvalue()*i.getPR_I_Amount();
                }
            }
            return OValue;
        }
    }

   /**
    * Gibt den Gesamtwert des Einkaufswagens zurück!
    * @return Gesamtwert (Typ: String)
    */
    public String getPR_LSI_ShoppingCartOverallValue() {
        return Backend.PU_SF_DF_DoubleFormat.format(getPR_LSI_ShoppingCartOverallValueDouble()) +"EUR";
    }

    /**
     * Gibt die ShoppingItem-Liste zurück!
     * @return ShoppingItem-Liste (Typ: ShoppingItem - ArrayList)
     */
    public ArrayList<ShoppingItem> getPR_LSI_ShoppingCart() {
        return PR_LSI_ShoppingCart;
    }

    /**
     * Fügt ein ShoppingItem in der ShoppingItem-Liste hinzu!
     * @param TItem ShoppingItem
     */
	public void addShoppingItem(ShoppingItem TItem) {
        PR_LSI_ShoppingCart.add(TItem);
    }

    /**
     * Entfernt ein Shopping-Item aus der ShoppingItem-Liste anhand der Produkt-Nummer(ID)
     * @param TSINumber Produkt-Nummer(ID)
     */
    public void removeShoppingItem(int TSINumber) {
        PR_LSI_ShoppingCart.removeIf(T_SI_Item -> T_SI_Item.checkPRGItemID(TSINumber));
    }

    /**
     * Ändert die Anzahl eines Shopping-Items aus der ShoppingItem-Liste anhand der Produkt-Nummer(ID)
     * @param TSINumber Produkt-Nummer(ID)
     * @param TSIAmount neue Anzahl
     */
    public void changeShoppingItemAmount(int TSINumber, int TSIAmount) {
        for (ShoppingItem T_SI_Item: PR_LSI_ShoppingCart) {
            if (T_SI_Item.checkPRGItemID(TSINumber)) {
                T_SI_Item.setPR_I_Amount(TSIAmount);
                PR_LSI_ShoppingCart.set(PR_LSI_ShoppingCart.indexOf(T_SI_Item),T_SI_Item);
                return;
            }
        }
    }

}
