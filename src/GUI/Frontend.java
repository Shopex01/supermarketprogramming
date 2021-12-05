package GUI;

import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frontend extends JFrame {

    public Frontend() {

        //Default Cart
        Backend TBackend = new Backend();
        ShoppingCart TDefaultCart = new ShoppingCart(ShoppingCartEnumeration.STANDARD, "NMame");
        TBackend.addShoppingCart(TDefaultCart);

        //GUI-Settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 412);
        JPanel TContentPane = new JPanel();
        TContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(TContentPane);
        TContentPane.setLayout(null);
        setResizable(false);

        //Tab-Panel
        JTabbedPane TTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        TTabbedPane.setBounds(0, 0, 534, 373);
        TContentPane.add(TTabbedPane);

        //Tab "Produktseite"
        JLayeredPane TProductLayer = new JLayeredPane();
        TTabbedPane.addTab("Produktseite", null, TProductLayer, null);

        JTextArea TProductLayer_TA_Status = new JTextArea(TBackend.output_produkt());
        TProductLayer_TA_Status.setEditable(false);
        TProductLayer_TA_Status.setFont(UIManager.getFont("TextArea.font"));
        TProductLayer_TA_Status.setForeground(Color.GREEN);
        TProductLayer_TA_Status.setBackground(Color.BLACK);
        TProductLayer_TA_Status.setWrapStyleWord(true);
        TProductLayer_TA_Status.setBounds(10, 299, 509, 35);
        TProductLayer.add(TProductLayer_TA_Status);

        JList TProductLayer_JList = new JList();
        TProductLayer_JList.setBounds(10, 42, 509, 246);
        TProductLayer.add(TProductLayer_JList);

        JTextArea TProductLayer_TA_RecentShoppingCart = new JTextArea();
        TProductLayer_TA_RecentShoppingCart.setText("Warenkorb XY");
        TProductLayer_TA_RecentShoppingCart.setEditable(false);
        TProductLayer_TA_RecentShoppingCart.setBounds(10, 11, 110, 20);
        TProductLayer.add(TProductLayer_TA_RecentShoppingCart);

        JTextArea TProductLayer_TA_ShoppingCartValue = new JTextArea();
        TProductLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TProductLayer_TA_ShoppingCartValue.setEditable(false);
        TProductLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TProductLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TProductLayer.add(TProductLayer_TA_ShoppingCartValue);

        JTextArea TProductLayer_TA_ShoppingCartLabel = new JTextArea();
        TProductLayer_TA_ShoppingCartLabel.setText("Warenkorbwert");
        TProductLayer_TA_ShoppingCartLabel.setEditable(false);
        TProductLayer_TA_ShoppingCartLabel.setBounds(267, 11, 110, 20);
        TProductLayer.add(TProductLayer_TA_ShoppingCartLabel);

        //Tab "Warenkorb-Liste"
        JLayeredPane TShoppingCartListLayer = new JLayeredPane();
        TTabbedPane.addTab("Warenkorbliste", null, TShoppingCartListLayer, null);

        JTextArea TShoppingCartListLayer_TA_Status = new JTextArea(TBackend.status_warenkorbliste);
        TShoppingCartListLayer_TA_Status.setEditable(false);
        TShoppingCartListLayer_TA_Status.setWrapStyleWord(true);
        TShoppingCartListLayer_TA_Status.setForeground(Color.GREEN);
        TShoppingCartListLayer_TA_Status.setFont(UIManager.getFont("TextArea.font"));
        TShoppingCartListLayer_TA_Status.setBackground(Color.BLACK);
        TShoppingCartListLayer_TA_Status.setBounds(10, 299, 509, 35);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_Status);

        JTextArea TShoppingCartListLayer_TA_RecentShoppingCart = new JTextArea();
        TShoppingCartListLayer_TA_RecentShoppingCart.setText("Warenkorb XY");
        TShoppingCartListLayer_TA_RecentShoppingCart.setEditable(false);
        TShoppingCartListLayer_TA_RecentShoppingCart.setBounds(10, 11, 110, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_RecentShoppingCart);

        JTextArea TShoppingCartListLayer_TA_ShoppingCartValue = new JTextArea();
        TShoppingCartListLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TShoppingCartListLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartValue);

        JTextArea TShoppingCartListLayer_TA_ShoppingCartLabel = new JTextArea();
        TShoppingCartListLayer_TA_ShoppingCartLabel.setText("Warenkorbwert");
        TShoppingCartListLayer_TA_ShoppingCartLabel.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartLabel.setBounds(267, 11, 110, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartLabel);

        JTextArea TShoppingCartListLayer_TA_ShoppingCartName = new JTextArea();
        TShoppingCartListLayer_TA_ShoppingCartName.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartName.setText("Warenkorbname");
        TShoppingCartListLayer_TA_ShoppingCartName.setBounds(10, 42, 110, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartName);

        JTextField TShoppingCartListLayer_TA_ShoppingCartNameField = new JTextField();
        TShoppingCartListLayer_TA_ShoppingCartNameField.setBounds(130, 42, 86, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartNameField);
        TShoppingCartListLayer_TA_ShoppingCartNameField.setColumns(10);

        JTextArea TShoppingCartListLayer_TA_ShoppingCartCategory = new JTextArea();
        TShoppingCartListLayer_TA_ShoppingCartCategory.setText("Warenkorbkategorie");
        TShoppingCartListLayer_TA_ShoppingCartCategory.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartCategory.setBounds(226, 42, 151, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartCategory);

        //Warenkorb - Kategorie
        Choice TShoppingCartListLayer_Choice = new Choice();
        TShoppingCartListLayer.setLayer(TShoppingCartListLayer_Choice, 4);
        TShoppingCartListLayer_Choice.setBounds(387, 42, 132, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_Choice);
        TShoppingCartListLayer_Choice.add("STANDARD");
        TShoppingCartListLayer_Choice.add("ECONOMIC");
        TShoppingCartListLayer_Choice.add("U18");
        TShoppingCartListLayer_Choice.add("EMPLOYEE");
        TShoppingCartListLayer_Choice.add("SAVING");

        JList TShoppingCartListLayer_JL = new JList();
        TShoppingCartListLayer_JL.setBounds(20, 102, 1, 1);
        TShoppingCartListLayer.add(TShoppingCartListLayer_JL);
        
        JList<String> TShoppingCartListLayer_JL_1 = new JList<>(TBackend.getModel()); //Warenkorb-Liste Tab
        TBackend.getModel().addElement(TDefaultCart.getListName()); //Default Cart einfügen
        TShoppingCartListLayer_JL_1.setBounds(10, 102, 499, 186);
        TShoppingCartListLayer.add(TShoppingCartListLayer_JL_1);

        JButton TShoppingCartListLayer_B_NewShoppingCart = new JButton("Neuen Warenkorb erstellen");
        TShoppingCartListLayer_B_NewShoppingCart.addActionListener(e -> {
            ShoppingCartEnumeration ShopEnum = ShoppingCartEnumeration.valueOf(TShoppingCartListLayer_Choice.getSelectedItem());
            Backend.createCart(ShopEnum, TShoppingCartListLayer_TA_ShoppingCartNameField.getText());
            TShoppingCartListLayer_TA_ShoppingCartNameField.setText("");
            TShoppingCartListLayer_JL.repaint();
        });
        TShoppingCartListLayer_B_NewShoppingCart.setBounds(10, 68, 509, 23);
        TShoppingCartListLayer.add(TShoppingCartListLayer_B_NewShoppingCart);

        //Tab "Warenkorb"
        JLayeredPane TShoppingCartLayer = new JLayeredPane();
        TTabbedPane.addTab("Warenkorb", null, TShoppingCartLayer, null);

        JList TShoppingCartLayer_JList = new JList();
        TShoppingCartLayer_JList.setBounds(10, 42, 509, 218);
        TShoppingCartLayer.add(TShoppingCartLayer_JList);

        JTextArea TShoppingCartLayer_TA_RecentShoppingCart = new JTextArea();
        TShoppingCartLayer_TA_RecentShoppingCart.setText("Warenkorb XY");
        TShoppingCartLayer_TA_RecentShoppingCart.setEditable(false);
        TShoppingCartLayer_TA_RecentShoppingCart.setBounds(10, 11, 110, 20);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_RecentShoppingCart);

        JTextField textField = new JTextField();
        textField.setBounds(370, 271, 149, 20);
        TShoppingCartLayer.add(textField);
        textField.setColumns(10);

        JTextPane TShoppingCartLayer_TP_CouponCode = new JTextPane();
        TShoppingCartLayer_TP_CouponCode.setEditable(false);
        TShoppingCartLayer_TP_CouponCode.setBackground(new Color(255, 255, 255));
        TShoppingCartLayer_TP_CouponCode.setText("Gutscheincode");
        TShoppingCartLayer_TP_CouponCode.setBounds(250, 271, 110, 20);
        TShoppingCartLayer.add(TShoppingCartLayer_TP_CouponCode);

        JTextArea TShoppingCartLayer_TA_Status = new JTextArea(TBackend.status_warenkorb);
        TShoppingCartLayer_TA_Status.setEditable(false);
        TShoppingCartLayer_TA_Status.setWrapStyleWord(true);
        TShoppingCartLayer_TA_Status.setForeground(Color.GREEN);
        TShoppingCartLayer_TA_Status.setFont(UIManager.getFont("TextArea.font"));
        TShoppingCartLayer_TA_Status.setBackground(Color.BLACK);
        TShoppingCartLayer_TA_Status.setBounds(10, 299, 509, 35);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_Status);

        JTextArea TShoppingCartLayer_TA_ShoppingCartValue = new JTextArea();
        TShoppingCartLayer_TA_ShoppingCartValue.setForeground(new Color(0, 255, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setBackground(new Color(0, 0, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_ShoppingCartValue);

        JTextArea TShoppingCartLayer_TA_ShoppingCartLabel = new JTextArea();
        TShoppingCartLayer_TA_ShoppingCartLabel.setText("Warenkorbwert");
        TShoppingCartLayer_TA_ShoppingCartLabel.setEditable(false);
        TShoppingCartLayer_TA_ShoppingCartLabel.setBounds(267, 11, 110, 20);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_ShoppingCartLabel);
    }

}
