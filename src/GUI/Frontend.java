package GUI;

import Objects.ShoppingCart.ShoppingCartEnumeration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class Frontend extends JFrame {
	private final JTable table_Warenkorb = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
	private final JTable table_WarenkorbListe = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
	private final JTable table_Produktliste = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Frontend() {

        //Object-Creation -----------------------------------------------------------------------@@@@@
        Backend TBackend = new Backend();
        JTabbedPane TTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JLayeredPane TProductLayer = new JLayeredPane();
        JTextArea TProductLayer_TA_ShoppingCartValue = new JTextArea();
        JScrollPane scrollPane_Produktliste = new JScrollPane();
        TableColumnModel tcm = table_Produktliste.getColumnModel();
        JLabel label_ProduktlisteTab_Preis = new JLabel("Warenwert:");
        JLabel label_ProduktlisteTab_aktWarenkorbLabel = new JLabel("Aktueller Warenkorb:");
        JLabel label_ProduktlisteTab_aktWarenkorbValueLabel = new JLabel("Standard-Warenkorb123");
        JLayeredPane TShoppingCartListLayer = new JLayeredPane();
        ScrollPane scrollPane_Status = new ScrollPane();
        JTextArea TShoppingCartListLayer_TA_ShoppingCartValue = new JTextArea();
        Choice TShoppingCartListLayer_Choice = new Choice();
        TextField textField_Status = new TextField();
        JScrollPane scrollPane_1 = new JScrollPane();
        JButton TShoppingCartListLayer_B_NewShoppingCart = new JButton("Neuen Warenkorb erstellen");
        JTextField TShoppingCartListLayer_TA_ShoppingCartNameField = new JTextField();
        JLabel label_WarenkorbListeTab_NewName = new JLabel("Warenkorb-Name:");
        JLabel label_WarenkorbListeTab_KorbArt = new JLabel("Warenkorbart:");
        JLabel label_WarenkorbListeTab_Preis = new JLabel("Warenwert:");
        JLayeredPane TShoppingCartLayer = new JLayeredPane();
        JLabel label_WarenkorbTab_Preis = new JLabel("Warenwert:");
        JTextField textField = new JTextField();
        JTextArea TShoppingCartLayer_TA_ShoppingCartValue = new JTextArea();
        JScrollPane scrollPane_Warenkorb = new JScrollPane();
        JLabel label_WarenkorbTab_Gutschein = new JLabel("Gutscheincode");
        JButton TShoppingCartListLayer_B_PayCurrentShoppingCart = new JButton("Warenkorb bezahlen");

        //GUI-Settings --------------------------------------------------------------------------@@@@@
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 412);
        JPanel TContentPane = new JPanel();
        TContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(TContentPane);
        TContentPane.setLayout(null);
        setResizable(false);

        //Tab-Panel -----------------------------------------------------------------------------@@@@@

        TTabbedPane.setBounds(0, 0, 534, 325);
        TContentPane.add(TTabbedPane);

        //Tab "Produktseite" --------------------------------------------------------------------@@@@@
        TTabbedPane.addTab("Produktseite", null, TProductLayer, null);

        //Tab "Produktseite" - TextArea - ShoppingCartValue -------------------------------------@@@@@
        TProductLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TProductLayer_TA_ShoppingCartValue.setEditable(false);
        TProductLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TProductLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TProductLayer.add(TProductLayer_TA_ShoppingCartValue);

        //Tab "Produktseite" - ScrollPane -------------------------------------------------------@@@@@

        scrollPane_Produktliste.setBounds(10, 42, 509, 256);
        TProductLayer.add(scrollPane_Produktliste);

        //Tab "Produktseite" - Model&Table ------------------------------------------------------@@@@@
        table_Produktliste.setModel(Backend.model_pListe);
        table_Produktliste.setShowVerticalLines(false);
        table_Produktliste.setShowHorizontalLines(false);
        table_Produktliste.setShowGrid(false);
        scrollPane_Produktliste.setViewportView(table_Produktliste);
        tcm.removeColumn(tcm.getColumn(0));

        label_ProduktlisteTab_Preis.setBounds(290, 11, 122, 22);
        TProductLayer.add(label_ProduktlisteTab_Preis);

        label_ProduktlisteTab_aktWarenkorbLabel.setBounds(12, 11, 125, 22);
        TProductLayer.add(label_ProduktlisteTab_aktWarenkorbLabel);
        label_ProduktlisteTab_aktWarenkorbValueLabel.setBounds(135, 11, 145, 22);
        TProductLayer.add(label_ProduktlisteTab_aktWarenkorbValueLabel);

        //Tab "Warenkorb-Liste" -----------------------------------------------------------------@@@@@

        TTabbedPane.addTab("Warenkorbliste", null, TShoppingCartListLayer, null);

        TShoppingCartListLayer_TA_ShoppingCartValue.setForeground(Color.GREEN);
        TShoppingCartListLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBackground(Color.BLACK);
        TShoppingCartListLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartValue);

        //Warenkorb - Kategorie ------------------------------------------------------------------@@@@@

        TShoppingCartListLayer.setLayer(TShoppingCartListLayer_Choice, 4);
        TShoppingCartListLayer_Choice.setBounds(387, 42, 132, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_Choice);
        TShoppingCartListLayer_Choice.add("STANDARD");
        TShoppingCartListLayer_Choice.add("ECONOMIC");
        TShoppingCartListLayer_Choice.add("U18");
        TShoppingCartListLayer_Choice.add("EMPLOYEE");
        TShoppingCartListLayer_Choice.add("SAVING");

        //Status Start	--------------------------------------------------------------------------@@@@@

        scrollPane_Status.setBounds(0, 324, 534, 51);
        TContentPane.add(scrollPane_Status);
        textField_Status.setFont(UIManager.getFont("TextArea.font"));
        textField_Status.setBackground(Color.BLACK);
        textField_Status.setForeground(Color.GREEN);
        textField_Status.setEditable(false);
        textField_Status.setBounds(0, 0, 24, 21);
        scrollPane_Status.add(textField_Status);

        TShoppingCartListLayer_B_NewShoppingCart.setEnabled(false);
        TShoppingCartListLayer_B_NewShoppingCart.addActionListener(e -> {
            Backend.createCart(ShoppingCartEnumeration.valueOf(TShoppingCartListLayer_Choice.getSelectedItem()), TShoppingCartListLayer_TA_ShoppingCartNameField.getText());
            TShoppingCartListLayer_TA_ShoppingCartNameField.setText("");
            textField_Status.setText(TBackend.statusOverall());
        });
        TShoppingCartListLayer_B_NewShoppingCart.setBounds(10, 68, 509, 23);
        TShoppingCartListLayer.add(TShoppingCartListLayer_B_NewShoppingCart);

        TShoppingCartListLayer_TA_ShoppingCartNameField.setBounds(130, 42, 86, 20);
        TShoppingCartListLayer.add(TShoppingCartListLayer_TA_ShoppingCartNameField);
        TShoppingCartListLayer_TA_ShoppingCartNameField.setColumns(10);
        TShoppingCartListLayer_TA_ShoppingCartNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                TShoppingCartListLayer_B_NewShoppingCart.setEnabled(!TShoppingCartListLayer_TA_ShoppingCartNameField.getText().isBlank());
            }
        });

        //Status End	--------------------------------------------------------------------------@@@@@

        
      //Default Cart einfuegen -------------------------------------------------------------------@@@@@


        scrollPane_1.setBounds(10, 102, 509, 196);
        TShoppingCartListLayer.add(scrollPane_1);

        table_WarenkorbListe.setModel(Backend.model_wListe);
        table_WarenkorbListe.setShowVerticalLines(false);
        table_WarenkorbListe.setShowHorizontalLines(false);
        table_WarenkorbListe.setShowGrid(false);
        scrollPane_1.setViewportView(table_WarenkorbListe);
        table_WarenkorbListe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JTable Input = (JTable)e.getSource();
                    if(JOptionPane.showOptionDialog(null,"Wollen sie zum Warenkorb:"+Input.getValueAt(Input.getSelectedRow(),0)+" wechseln?","Warenkorb wechseln",1,3,null,null,null)==0) {

                    }
                }
            }
        });

        label_WarenkorbListeTab_NewName.setBounds(10, 42, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_NewName);

        label_WarenkorbListeTab_KorbArt.setBounds(290, 42, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_KorbArt);

        label_WarenkorbListeTab_Preis.setBounds(290, 11, 122, 22);
        TShoppingCartListLayer.add(label_WarenkorbListeTab_Preis);

        //Tab "Warenkorb" ------------------------------------------------------------------------@@@@@
        TTabbedPane.addTab("Warenkorb", null, TShoppingCartLayer, null);

        textField.setBounds(370, 271, 149, 20);
        TShoppingCartLayer.add(textField);
        textField.setColumns(10);

        TShoppingCartLayer_TA_ShoppingCartValue.setForeground(new Color(0, 255, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setBackground(new Color(0, 0, 0));
        TShoppingCartLayer_TA_ShoppingCartValue.setEditable(false);
        TShoppingCartLayer_TA_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TShoppingCartLayer.add(TShoppingCartLayer_TA_ShoppingCartValue);

        scrollPane_Warenkorb.setBounds(10, 43, 509, 220);
        TShoppingCartLayer.add(scrollPane_Warenkorb);

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
        	final Class[] columnTypes = new Class[] {
        		String.class, String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        scrollPane_Warenkorb.setViewportView(table_Warenkorb);

        label_WarenkorbTab_Preis.setBounds(264, 12, 122, 22);
        TShoppingCartLayer.add(label_WarenkorbTab_Preis);

        label_WarenkorbTab_Gutschein.setBounds(248, 269, 122, 22);
        TShoppingCartLayer.add(label_WarenkorbTab_Gutschein);

        TShoppingCartListLayer_B_PayCurrentShoppingCart.setBounds(10, 269, 175, 22);
        TShoppingCartLayer.add(TShoppingCartListLayer_B_PayCurrentShoppingCart);

    }
}
