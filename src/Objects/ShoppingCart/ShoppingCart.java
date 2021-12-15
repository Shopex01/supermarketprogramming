package Objects.ShoppingCart;

import GUI.Backend;

import java.util.ArrayList;

public class ShoppingCart {
    private final int PR_F_I_ID;
	private final String PR_F_S_Name;
    protected ArrayList<ShoppingItem> PR_LSI_ShoppingCart;
    private final ShoppingCartEnumeration PR_SCE_ShoppingCartType;

    public ShoppingCart(ShoppingCartEnumeration PR_SCE_ShoppingCartType, String PR_S_Name, int PR_F_I_ID) {
        this.PR_LSI_ShoppingCart = new ArrayList<>();
        this.PR_SCE_ShoppingCartType = PR_SCE_ShoppingCartType;
        this.PR_F_S_Name = PR_S_Name;
        this.PR_F_I_ID = PR_F_I_ID;
    }

    public int getPR_F_I_ID() {
        return PR_F_I_ID;
    }

    public String getPR_S_Name() {
		return PR_F_S_Name;
	}

    public ShoppingCartEnumeration getPR_SCE_ShoppingCartType() {
        return PR_SCE_ShoppingCartType;
    }

    public String getPR_SCE_ShoppingCartTypeString() {
        return switch (PR_SCE_ShoppingCartType) {
            case ECONOMIC -> "Öko-Prinzip";
            case U18 -> "U18";
            case EMPLOYEE -> "Mitarbeiterkaufprogramm";
            case SAVING -> "Spar-Korb";
            case STANDARD -> "Standard";
        };
    }

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

    public String getPR_LSI_ShoppingCartOverallValue() {
        return Backend.PU_SF_DF_DoubleFormat.format(getPR_LSI_ShoppingCartOverallValueDouble()) +"EUR";
    }

    public ArrayList<ShoppingItem> getPR_LSI_ShoppingCart() {
        return PR_LSI_ShoppingCart;
    }

	public void addShoppingItem(ShoppingItem TItem) {
        PR_LSI_ShoppingCart.add(TItem);
    }

    public void removeShoppingItem(int TSINumber) {
        PR_LSI_ShoppingCart.removeIf(T_SI_Item -> T_SI_Item.checkPRGItemID(TSINumber));
    }

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
