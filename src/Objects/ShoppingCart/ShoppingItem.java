package Objects.ShoppingCart;

import Objects.Goods.Good;

/**
 * Diese Klasse stellt einen Produkt mit Anzahl dar!
 * @author Arthur Ladner
 * @version 1.0
 */
public class ShoppingItem {
    private final Good PR_G_Item;
    private int PR_I_Amount;

    /**
     * Dieser Konstruktor erstellt ein ShoppingItem anhand 2 Parameter her!
     * @param T_G_Item Produkt
     * @param T_I_Amount Anzahl
     */
    public ShoppingItem(Good T_G_Item, int T_I_Amount) {
       PR_G_Item = T_G_Item;
       PR_I_Amount = T_I_Amount;
    }

    /**
     * Gibt die Anzahl des Produktes aus!
     * @return Anzahl (Typ: Integer)
     */
    public int getPR_I_Amount() {
        return PR_I_Amount;
    }

    /**
     * Setzt die Anzahl des Produktes!
     * @param PR_I_Amount Neue Anzahl des Produktes
     */
    public void setPR_I_Amount(int PR_I_Amount) {
        this.PR_I_Amount = PR_I_Amount;
    }

    /**
     * Gibt das Produkt aus!
     * @return Produkt (Typ: Good)
     */
    public Good getPR_G_Item() { return PR_G_Item; }

    /**
     * Überprüft eine gegebene Produkt-ID mit der Produkt-ID des Objektes "ShoppingItem" und gibt je nach Ergebnis ein Boolean-Wert aus!
     * @param T_ID Produkt-ID
     * @return Boolean-Wert
     */
    public boolean checkPRGItemID(int T_ID) {
        return T_ID == PR_G_Item.getPR_F_I_number();
    }
}
