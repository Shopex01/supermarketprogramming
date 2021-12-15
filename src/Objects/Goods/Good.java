package Objects.Goods;

public class Good {

    private final int PR_F_I_number; //Good-ID
    private final GoodCategoryEnumerations PR_F_GCE_category; //Good-Category
    private final String PR_F_S_name; //Good-Name
    private final Double PR_F_D_purchasevalue; //Good-PurchasePrice
    private final Double PR_F_D_sellvalue; //Good-SellPrice
    private final GoodPropertyEnumerations PR_F_GPE_property; //Good-Property
    private final String PR_F_GPE_propertyValue;

    public Good(int T_I_Num, GoodCategoryEnumerations T_GCE_Cat, String T_S_Name, Double T_BD_PPrice, Double T_BD_SPrice, GoodPropertyEnumerations T_GPE_Prop, String T_GPE_PropValue) {
        PR_F_I_number = T_I_Num;
        PR_F_GCE_category = T_GCE_Cat;
        PR_F_S_name = T_S_Name;
        PR_F_D_purchasevalue = T_BD_PPrice;
        PR_F_D_sellvalue = T_BD_SPrice;
        PR_F_GPE_property = T_GPE_Prop;
        PR_F_GPE_propertyValue = T_GPE_PropValue;
    }

    public int getPR_F_I_number() {
        return PR_F_I_number;
    }

    public GoodCategoryEnumerations getPR_F_GCE_category() {
        return PR_F_GCE_category;
    }

    public String getPR_F_S_name() {
        return PR_F_S_name;
    }

    public Double getPR_F_BD_purchasevalue() {
        return PR_F_D_purchasevalue;
    }

    public Double getPR_F_BD_sellvalue() {
        return PR_F_D_sellvalue;
    }

    public GoodPropertyEnumerations getPR_F_GPE_property() {
        return PR_F_GPE_property;
    }

    public String getPR_F_GPE_propertyValue(){return PR_F_GPE_propertyValue;}

    public String getPR_F_GPE_propertyString() {
        return switch (PR_F_GPE_property) {
            case EXPIRATIONDATE -> "MHD: ";
            case RECYCLING -> "Recycle-Anteil: ";
            case FSK -> "FSK: ";
            case NONE -> "Standard";
        };
    }

    public String getPR_F_GCE_categoryString() {
        return switch (PR_F_GCE_category) {
            case FOOD -> "Lebensmittel";
            case HOUSEHOLD -> "Haushaltsartikel";
            case OTHER -> "Sonstige";
        };
    }

}