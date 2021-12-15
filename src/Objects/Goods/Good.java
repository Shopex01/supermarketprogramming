package Objects.Goods;

/**
 * Diese Klasse repräsentiert das Produkt allgemein in einem Supermarkt!
 * @author Arthur Ladner
 * @version 1.0
 */
public class Good {

    private final int PR_F_I_number; //Good-ID
    private final GoodCategoryEnumerations PR_F_GCE_category; //Good-Category
    private final String PR_F_S_name; //Good-Name
    private final Double PR_F_D_purchasevalue; //Good-PurchasePrice
    private final Double PR_F_D_sellvalue; //Good-SellPrice
    private final GoodPropertyEnumerations PR_F_GPE_property; //Good-Property
    private final String PR_F_GPE_propertyValue;

    /**
     * Generiert ein Produkt anhand folgender Eigenschaften:
     * @param T_I_Num Produkt-ID
     * @param T_GCE_Cat Produkt-Kategorie (Lebensmittel, Haushaltsartikel, andere)
     * @param T_S_Name Produkt-Name
     * @param T_D_PPrice Produkt - Verkaufspreis
     * @param T_D_SPrice Produkt - Einkaufspreis
     * @param T_GPE_Prop Produkt - (weitere) Eigenschaft (Keine, Mindesthaltbarkeitsdatum, Recycling-Anteil, FSK-Kategorie)
     */
    public Good(int T_I_Num, GoodCategoryEnumerations T_GCE_Cat, String T_S_Name, Double T_D_PPrice, Double T_D_SPrice, GoodPropertyEnumerations T_GPE_Prop, String T_GPE_PropValue) {
        PR_F_I_number = T_I_Num;
        PR_F_GCE_category = T_GCE_Cat;
        PR_F_S_name = T_S_Name;
        PR_F_D_purchasevalue = T_D_PPrice;
        PR_F_D_sellvalue = T_D_SPrice;
        PR_F_GPE_property = T_GPE_Prop;
        PR_F_GPE_propertyValue = T_GPE_PropValue;
    }

    /**
     * Gibt die Produkt-ID aus!
     * @return Produkt-ID (Typ: Integer)
     */
    public int getPR_F_I_number() {
        return PR_F_I_number;
    }

    /**
     * Gibt die Produkt-Kategorie aus!
     * @return Produkt-Kategorie (Typ: GoodCategoryEnumerations)
     */
    public GoodCategoryEnumerations getPR_F_GCE_category() {
        return PR_F_GCE_category;
    }

    /**
     * Gibt den Produkt-Namen aus!
     * @return Produkt-Name (Typ: String)
     */
    public String getPR_F_S_name() {
        return PR_F_S_name;
    }

    /**
     * Gibt den Einkaufspreis des Produktes aus!
     * @return Produkt - Einkaufspreis (Typ: double)
     */
    public Double getPR_F_BD_purchasevalue() {
        return PR_F_D_purchasevalue;
    }
    /**
     * Gibt den Verkaufspreis des Produktes aus!
     * @return Produkt - Verkaufspreis (Typ: double)
     */
    public Double getPR_F_BD_sellvalue() {
        return PR_F_D_sellvalue;
    }

    /**
     * Gibt die (weitere) Eigenschaft des Produktes zurück!
     * @return Produkt - (weitere) Eigenschaft (Typ: GoodPropertyEnumerations)
     */
    public GoodPropertyEnumerations getPR_F_GPE_property() {
        return PR_F_GPE_property;
    }

    /**
     * Gibt den Wert der weiteren Eigenschaft des Produktes aus!
     * @return Wert der weiteren Eigenschaft (Typ: String)
     */
    public String getPR_F_GPE_propertyValue(){return PR_F_GPE_propertyValue;}

    /**
     * Gibt die weitere Eigenschaft im String-Format aus!
     * @return weitere Eigenschaft (Typ: String)
     */
    public String getPR_F_GPE_propertyString() {
        return switch (PR_F_GPE_property) {
            case EXPIRATIONDATE -> "MHD: ";
            case RECYCLING -> "Recycle-Anteil: ";
            case FSK -> "FSK: ";
            case NONE -> "Standard";
        };
    }

    /**
     * Gibt die Kategorie des Produktes im String-Format aus!
     * @return Produkt-Kategorie (Typ: String)
     */
    public String getPR_F_GCE_categoryString() {
        return switch (PR_F_GCE_category) {
            case FOOD -> "Lebensmittel";
            case HOUSEHOLD -> "Haushaltsartikel";
            case OTHER -> "Sonstige";
        };
    }

}