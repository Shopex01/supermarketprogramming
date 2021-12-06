package GUI;

import Objects.ShoppingCart.ShoppingCart;
import Objects.ShoppingCart.ShoppingCartEnumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Frontend extends JFrame {
	private JTable table_1;
	private JTable table_Warenkorb;
	private JTable table_WarenkorbListe;
	private JTable table_Produktliste;

    public Frontend() {

        //Default Cart --------------------------------------------------------------------------@@@@@
        Backend TBackend = new Backend();
        ShoppingCart TDefaultCart = new ShoppingCart(ShoppingCartEnumeration.STANDARD, "Warenkorb");
        TBackend.addShoppingCart(TDefaultCart);

        //GUI-Settings --------------------------------------------------------------------------@@@@@
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 412);
        JPanel TContentPane = new JPanel();
        TContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(TContentPane);
        TContentPane.setLayout(null);
        setResizable(false);

        //Tab-Panel -----------------------------------------------------------------------------@@@@@
        JTabbedPane TTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        TTabbedPane.setBounds(0, 0, 534, 325);
        TContentPane.add(TTabbedPane);

        //Tab "Produktseite" --------------------------------------------------------------------@@@@@
        JLayeredPane TProductLayer = new JLayeredPane();
        TTabbedPane.addTab("Produktseite", null, TProductLayer, null);

        JTextArea TProductLayer_TA_ShoppingCartValue = new JTextArea();
        TProductLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TProductLayer_TA_ShoppingCartValue.setEditable(false);
        TProductLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TProductLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TProductLayer.add(TProductLayer_TA_ShoppingCartValue);
        
        JScrollPane scrollPane_Produktliste = new JScrollPane();
        scrollPane_Produktliste.setBounds(10, 42, 509, 256);
        TProductLayer.add(scrollPane_Produktliste);
        
        table_Produktliste = new JTable();
        table_Produktliste.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Produkt", "Kategorie", "Preis"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        table_Produktliste.setShowVerticalLines(false);
        table_Produktliste.setShowHorizontalLines(false);
        table_Produktliste.setShowGrid(false);
        scrollPane_Produktliste.setViewportView(table_Produktliste);
        
        JLabel label_ProduktlisteTab_Preis = new JLabel("New label");
        label_ProduktlisteTab_Preis.setBounds(266, 11, 122, 22);
        TProductLayer.add(label_ProduktlisteTab_Preis);

        //Tab "Warenkorb-Liste" -----------------------------------------------------------------@@@@@
        JLayeredPane TShoppingCartListLayer = new JLayeredPane();
        TTabbedPane.addTab("Warenkorbliste", null, TShoppingCartListLayer, null);

        JTextArea TShoppingCartListLayer_TA_ShoppingCartValue = new JTextArea();
        TShoppingCartListLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TShoppingCartListLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartValue);

        JTextField TShoppingCartListLayer_TA_ShoppingCartNameField = new JTextField();
        TShoppingCartListLayer_TA_ShoppingCartNameField.setBounds(130, 42, 86, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartNameField);
        TShoppingCartListLayer_TA_ShoppingCartNameField.setColumns(10);

        //Warenkorb - Kategorie ------------------------------------------------------------------@@@@@
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

        

        
      //Default Cart einfuegen -------------------------------------------------------------------@@@@@
        TBackend.getModel().addElement(TDefaultCart.getListName());

        JButton TShoppingCartListLayer_B_NewShoppingCart = new JButton("Neuen Warenkorb erstellen");
        TShoppingCartListLayer_B_NewShoppingCart.addActionListener(e -> {
            ShoppingCartEnumeration ShopEnum = ShoppingCartEnumeration.valueOf(TShoppingCartListLayer_Choice.getSelectedItem());
            Backend.createCart(ShopEnum, TShoppingCartListLayer_TA_ShoppingCartNameField.getText());
            TShoppingCartListLayer_TA_ShoppingCartNameField.setText("");
            TShoppingCartListLayer_JL.repaint();
        });
        TShoppingCartListLayer_B_NewShoppingCart.setBounds(10, 68, 509, 23);
        TShoppingCartListLayer.add(TShoppingCartListLayer_B_NewShoppingCart);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 102, 509, 196);
        TShoppingCartListLayer.add(scrollPane_1);
        
        table_WarenkorbListe = new JTable();
        table_WarenkorbListe.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Warenkorbname", "Warenkorbart", "Preis"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        table_WarenkorbListe.setShowVerticalLines(false);
        table_WarenkorbListe.setShowHorizontalLines(false);
        table_WarenkorbListe.setShowGrid(false);
        scrollPane_1.setViewportView(table_WarenkorbListe);
        
        JLabel label_WarenkorbListeTab_NewName = new JLabel("New label");
        label_WarenkorbListeTab_NewName.setBounds(10, 42, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_NewName);
        
        JLabel label_WarenkorbListeTab_KorbArt = new JLabel("New label");
        label_WarenkorbListeTab_KorbArt.setBounds(264, 42, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_KorbArt);
        
        JLabel label_WarenkorbListeTab_Preis = new JLabel("New label");
        label_WarenkorbListeTab_Preis.setBounds(264, 11, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_Preis);

        //Tab "Warenkorb" ------------------------------------------------------------------------@@@@@
        JLayeredPane TShoppingCartLayer = new JLayeredPane();
        TTabbedPane.addTab("Warenkorb", null, TShoppingCartLayer, null);

        JTextField textField = new JTextField();
        textField.setBounds(370, 271, 149, 20);
        TShoppingCartLayer.add(textField);
        textField.setColumns(10);

        JTextArea TShoppingCartLayer_TA_ShoppingCartValue = new JTextArea();
        TShoppingCartLayer_TA_ShoppingCartValue.setForeground(new Color(0, 255, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setBackground(new Color(0, 0, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_ShoppingCartValue);
        
        JScrollPane scrollPane_Warenkorb = new JScrollPane();
        scrollPane_Warenkorb.setBounds(10, 43, 509, 220);
        TShoppingCartLayer.add(scrollPane_Warenkorb);
        
        table_Warenkorb = new JTable();
        table_Warenkorb.setShowGrid(false);
        table_Warenkorb.setShowHorizontalLines(false);
        table_Warenkorb.setShowVerticalLines(false);
        table_Warenkorb.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        	},
        	new String[] {
        		"Produkt", "Menge", "Preis"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_Warenkorb.setViewportView(table_Warenkorb);
        
        JLabel label_WarenkorbTab_Preis = new JLabel("New label");
        label_WarenkorbTab_Preis.setBounds(264, 12, 122, 22);
        TShoppingCartLayer.add(label_WarenkorbTab_Preis);
        
        JLabel label_WarenkorbTab_Gutschein = new JLabel("New label");
        label_WarenkorbTab_Gutschein.setBounds(248, 269, 122, 22);
        TShoppingCartLayer.add(label_WarenkorbTab_Gutschein);
        
        
        
        //Status Start	--------------------------------------------------------------------------@@@@@
        ScrollPane scrollPane_Status = new ScrollPane();
        scrollPane_Status.setBounds(0, 324, 534, 51);
        TContentPane.add(scrollPane_Status);
        
        TextField textField_Status = new TextField();
        textField_Status.setFont(UIManager.getFont("TextArea.font"));
        textField_Status.setBackground(Color.BLACK);
        textField_Status.setForeground(Color.GREEN);
        textField_Status.setEditable(false);
        textField_Status.setBounds(0, 0, 24, 21);
        scrollPane_Status.add(textField_Status);
        //Status End	--------------------------------------------------------------------------@@@@@
    }
}
