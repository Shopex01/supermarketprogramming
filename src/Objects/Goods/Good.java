package Objects.Goods;

import java.math.BigDecimal;
import java.util.Currency;

public class Good {

    private final int PR_F_I_number; //Good-ID
    private final GoodCategoryEnumerations PR_F_GCE_category; //Good-Category
    private final String PR_F_S_name; //Good-Name
    private final BigDecimal PR_F_BD_purchasevalue; //Good-PurchasePrice
    private final BigDecimal PR_F_BD_sellvalue; //Good-SellPrice
    private final Currency PR_F_C_currency; //Good-Currency
    private final GoodPropertyEnumerations PR_F_GPE_property; //Good-Property

    public Good(int T_I_Num, GoodCategoryEnumerations T_GCE_Cat, String T_S_Name, BigDecimal T_BD_PPrice, BigDecimal T_BD_SPrice, GoodPropertyEnumerations T_GPE_Prop) {
        PR_F_I_number = T_I_Num;
        PR_F_GCE_category = T_GCE_Cat;
        PR_F_S_name = T_S_Name;
        PR_F_BD_purchasevalue = T_BD_PPrice;
        PR_F_BD_sellvalue = T_BD_SPrice;
        PR_F_GPE_property = T_GPE_Prop;
        PR_F_C_currency = Currency.getInstance("EUR");
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

    public BigDecimal getPR_F_BD_purchasevalue() {
        return PR_F_BD_purchasevalue;
    }

    public BigDecimal getPR_F_BD_sellvalue() {
        return PR_F_BD_sellvalue;
    }

    public Currency getPR_F_C_currency() {
        return PR_F_C_currency;
    }

    public GoodPropertyEnumerations getPR_F_GPE_property() {
        return PR_F_GPE_property;
    }
}
