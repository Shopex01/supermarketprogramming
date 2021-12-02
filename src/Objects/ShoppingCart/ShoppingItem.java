package Objects.ShoppingCart;

import Objects.Goods.Good;

public class ShoppingItem {
    private final Good PR_G_Item;
    private int PR_I_Amount;

    public ShoppingItem(Good T_G_Item, int T_I_Amount) {
       PR_G_Item = T_G_Item;
       PR_I_Amount = T_I_Amount;
    }

    public int getPR_I_Amount() {
        return PR_I_Amount;
    }

    public void setPR_I_Amount(int PR_I_Amount) {
        this.PR_I_Amount = PR_I_Amount;
    }

    public Good getPR_G_Item() { return PR_G_Item; }

    public boolean checkPRGItemID(int T_ID) {
        return T_ID == PR_G_Item.getPR_F_I_number();
    }
}
