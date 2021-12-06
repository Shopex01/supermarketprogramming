package Objects.Goods;

import java.util.Currency;

/**
 * This class represents the good in a supermarket in general!
 * @author Arthur Ladner
 * @version 1.0
 */
public class Good {

    private final int PR_F_I_number; //Good-ID
    private final GoodCategoryEnumerations PR_F_GCE_category; //Good-Category
    private final String PR_F_S_name; //Good-Name
    private final Double PR_F_D_purchasevalue; //Good-PurchasePrice
    private final Double PR_F_D_sellvalue; //Good-SellPrice
    private final Currency PR_F_C_currency; //Good-Currency
    private final GoodPropertyEnumerations PR_F_GPE_property; //Good-Property

    /**
     * Generates a good based on the following parameters
     * @param T_I_Num Good - ID
     * @param T_GCE_Cat Good - Category (food, household, other)
     * @param T_S_Name Good - Name
     * @param T_D_PPrice Good - Purchase-Value
     * @param T_D_SPrice Good - Sell-Price
     * @param T_GPE_Prop Good - Property (none, expiration-date, recycling, fsk)
     */
    public Good(int T_I_Num, GoodCategoryEnumerations T_GCE_Cat, String T_S_Name, Double T_D_PPrice, Double T_D_SPrice, GoodPropertyEnumerations T_GPE_Prop) {
        PR_F_I_number = T_I_Num;
        PR_F_GCE_category = T_GCE_Cat;
        PR_F_S_name = T_S_Name;
        PR_F_D_purchasevalue = T_D_PPrice;
        PR_F_D_sellvalue = T_D_SPrice;
        PR_F_GPE_property = T_GPE_Prop;
        PR_F_C_currency = Currency.getInstance("EUR");
    }

    /**
     * @return Good-ID (Type: Integer)
     */
    public int getPR_F_I_number() {
        return PR_F_I_number;
    }

    /**
     * @return Good - Category (Type: GoodCategoryEnumerations)
     */
    public GoodCategoryEnumerations getPR_F_GCE_category() {
        return PR_F_GCE_category;
    }

    /**
     * @return Good - Name (Type: String)
     */
    public String getPR_F_S_name() {
        return PR_F_S_name;
    }

    /**
     * @return Good - purchase value (Type: double)
     */
    public Double getPR_F_D_purchasevalue() {
        return PR_F_D_purchasevalue;
    }

    /**
     * @return Good - sell value (Type: double)
     */
    public Double getPR_F_D_sellvalue() {
        return PR_F_D_sellvalue;
    }

    /**
     * @return Good - Currency (Type: Currency)
     */
    public Currency getPR_F_C_currency() {
        return PR_F_C_currency;
    }

    /**
     * @return Good - property (Type: GoodPropertyEnumerations)
     */
    public GoodPropertyEnumerations getPR_F_GPE_property() {
        return PR_F_GPE_property;
    }
}
