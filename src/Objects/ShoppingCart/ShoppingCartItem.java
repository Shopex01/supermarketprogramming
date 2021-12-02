package Objects.ShoppingCart;

import java.util.List;

public class ShoppingCartItem {
    private List<ShoppingItem> PR_LSI_ShoppingCart;
    private final ShoppingCartEnumeration PR_SCE_ShoppingCartType;

    public ShoppingCartItem(List<ShoppingItem> PR_LSI_ShoppingCart, ShoppingCartEnumeration PR_SCE_ShoppingCartType) {
        this.PR_LSI_ShoppingCart = PR_LSI_ShoppingCart;
        this.PR_SCE_ShoppingCartType = PR_SCE_ShoppingCartType;
    }

    public List<ShoppingItem> getPR_LSI_ShoppingCart() {
        return PR_LSI_ShoppingCart;
    }

    public ShoppingCartEnumeration getPR_SCE_ShoppingCartType() {
        return PR_SCE_ShoppingCartType;
    }

    public void setPR_LSI_ShoppingCart(List<ShoppingItem> PR_LSI_ShoppingCart) {
        this.PR_LSI_ShoppingCart = PR_LSI_ShoppingCart;
    }
}
