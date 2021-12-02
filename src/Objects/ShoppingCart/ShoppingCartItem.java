package Objects.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItem {
    private final List<ShoppingItem> PR_LSI_ShoppingCart;
    private final ShoppingCartEnumeration PR_SCE_ShoppingCartType;

    public ShoppingCartItem(ShoppingCartEnumeration PR_SCE_ShoppingCartType) {
        this.PR_LSI_ShoppingCart = new ArrayList<>();
        this.PR_SCE_ShoppingCartType = PR_SCE_ShoppingCartType;
    }

    public boolean addShoppingItem(ShoppingItem TItem) {
        return PR_LSI_ShoppingCart.add(TItem);
    }

    public boolean removeShoppingItem(int TSINumber) {
        return PR_LSI_ShoppingCart.removeIf(T_SI_Item -> T_SI_Item.checkPRGItemID(TSINumber));
    }

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

    public ShoppingItem getShoppingItem(int TSINumber) {
        for (ShoppingItem T_SI_Item: PR_LSI_ShoppingCart) {
            if (T_SI_Item.checkPRGItemID(TSINumber)) {
                return T_SI_Item;
            }
        }
        return null;
    }

    public ShoppingCartEnumeration getPR_SCE_ShoppingCartType() {
        return PR_SCE_ShoppingCartType;
    }
}
