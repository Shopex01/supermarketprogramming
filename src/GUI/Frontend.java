package GUI;

import Objects.ShoppingCart.ShoppingCartEnumeration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.TableColumnModel;


public class Frontend extends JFrame {

    public Frontend() {
        //Object-Creation
        Backend TBackend = new Backend(); //Backend

        //Object-Creation - Tabs
        JTabbedPane TTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JLayeredPane TLayeredPane_GoodsTab = new JLayeredPane();
        JLayeredPane TLayeredPane_ShoppingCartListTab = new JLayeredPane();
        JLayeredPane TLayeredPane_ShoppingCartTab = new JLayeredPane();
        JPanel TContentPane = new JPanel();

        //Object-Creation - Tab "Produkte"
        JScrollPane TScrollPane_GoodsList = new JScrollPane();
        JTable TTable_GoodsList = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel TTableColumnModel_GoodsList = TTable_GoodsList.getColumnModel();
        JTextArea TTextArea_GoodsTab_ShoppingCartValue = new JTextArea(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
        JLabel TLabel_GoodsTab_CurrentShoppingCartPrice = new JLabel("Warenwert:");
        JLabel TLabel_GoodsTab_CurrentShoppingCart = new JLabel("Aktueller Warenkorb:");
        JLabel TLabel_GoodsTab_CurrentShoppingCartPriceValue = new JLabel(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());

        //Object-Creation - Tab "Warenkorb-Liste"
        JScrollPane TScrollPane_ShoppingCartListTab = new JScrollPane();
        JTable TTable_ShoppingCartList = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel TTableColumnModel_ShoppingCartList = TTable_ShoppingCartList.getColumnModel();
        JLabel TLabel_ShoppingCartListTab_CurrentShoppingCart = new JLabel("Aktueller Warenkorb:");
        JLabel TLabel_ShoppingCartListTab_CurrentShoppingCartPriceValue = new JLabel(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
        JTextArea TTextArea_ShoppingCartListTab_ShoppingCartValue = new JTextArea(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
        Choice TChoice_ShoppingCartListTab = new Choice();
        JButton TButton_ShoppingCartListTab_NewShoppingCart = new JButton("Neuen Warenkorb erstellen");
        JTextField TTextField_ShoppingCartListTab_ShoppingCartNameField = new JTextField();
        JLabel TLabel_ShoppingCartListTab_ShoppingCartName = new JLabel("Warenkorb-Name:");
        JLabel TLabel_ShoppingCartListTab_ShoppingCartType = new JLabel("Warenkorbart:");
        JLabel TLabel_ShoppingCartListTab_CurrentShoppingCartPrice = new JLabel("Warenwert:");

        //Object-Creation - Tab "Warenkorb"
        JScrollPane TScrollPane_ShoppingCartTab = new JScrollPane();
        JTable TTable_CurrentShoppingCart = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel TTableColumnModel_CurrentShoppingCart = TTable_CurrentShoppingCart.getColumnModel();
        JLabel TLabel_ShoppingCartTab_CurrentShoppingCart = new JLabel("Aktueller Warenkorb:");
        JLabel TLabel_ShoppingCartTab_CurrentShoppingCartPriceValue = new JLabel(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
        JTextArea TTextArea_ShoppingCartTab_DailyTakings = new JTextArea(TBackend.addToDailyTakings());
        JLabel TLabel_ShoppingCartTab_CurrentShoppingCartPrice = new JLabel("Warenwert:");
        JTextArea TTextArea_ShoppingCartTab_ShoppingCartValue = new JTextArea(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
        JLabel TLabel_ShoppingCartTab_DailyTakings = new JLabel("Tageseinnahmen");
        JButton TButton_ShoppingCartTab_PayCurrentShoppingCart = new JButton("Warenkorb bezahlen");

        //Object-Creation - TextField "Status"
        ScrollPane TScrollPane_Status = new ScrollPane();
        TextField TTextField_Status = new TextField();

        //GUI-Settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setBounds(100, 100, 550, 412);
        TContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(TContentPane);
        TContentPane.setLayout(null);
        TTabbedPane.setBounds(0, 0, 534, 325);
        TContentPane.add(TTabbedPane);

        //Tab "Produktseite"
        TTabbedPane.addTab("Produktseite", null, TLayeredPane_GoodsTab, null);

        //Tab "Produktseite" - TextArea - ShoppingCartValue
        TTextArea_GoodsTab_ShoppingCartValue.setForeground(Color.GREEN);
        TTextArea_GoodsTab_ShoppingCartValue.setEditable(false);
        TTextArea_GoodsTab_ShoppingCartValue.setBackground(Color.BLACK);
        TTextArea_GoodsTab_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TTextArea_GoodsTab_ShoppingCartValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        TLayeredPane_GoodsTab.add(TTextArea_GoodsTab_ShoppingCartValue);

        //Tab "Produktseite" - ScrollPane -------------------------------------------------------@@@@@
        TScrollPane_GoodsList.setBounds(10, 42, 509, 256);
        TLayeredPane_GoodsTab.add(TScrollPane_GoodsList);
        TScrollPane_GoodsList.setViewportView(TTable_GoodsList);

        //Tab "Produktseite" - Model&Table ------------------------------------------------------@@@@@
        TTable_GoodsList.setModel(Backend.PT_SF_DTM_ProductListModel);
        TTable_GoodsList.setShowVerticalLines(false);
        TTable_GoodsList.setShowHorizontalLines(false);
        TTable_GoodsList.setShowGrid(false);
        TTableColumnModel_GoodsList.removeColumn(TTableColumnModel_GoodsList.getColumn(0));
        TTable_GoodsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JTable Input = (JTable)e.getSource();
                    int TAmount = 0;
                    try {
                        TAmount = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Anzahl von: "+Input.getModel().getValueAt(Input.getSelectedRow(),2)+" hinzufügen?" ,"Produkt hinzufügen", JOptionPane.INFORMATION_MESSAGE, null, null, "1"));
                    } catch (Exception ignored) {
                    }
                    TBackend.addProductToShoppingCart(TBackend.getGood((int) Input.getModel().getValueAt(Input.getSelectedRow(),0)),TAmount);
                    TTextField_Status.setText(TBackend.statusOverall());
                    TTextArea_GoodsTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                    TTextArea_ShoppingCartListTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                    TTextArea_ShoppingCartTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                }
            }
        });

        //Tab "Produktseite" - Label "CurrentShoppingCart"
        TLabel_GoodsTab_CurrentShoppingCart.setBounds(12, 11, 125, 22);
        TLayeredPane_GoodsTab.add(TLabel_GoodsTab_CurrentShoppingCart);

        //Tab "Produktseite" - Label "CurrentShoppingCartPrice"
        TLabel_GoodsTab_CurrentShoppingCartPrice.setBounds(290, 11, 122, 22);
        TLayeredPane_GoodsTab.add(TLabel_GoodsTab_CurrentShoppingCartPrice);

        //Tab "Produktseite" - Label "CurrentShoppingCartPriceValue"
        TLabel_GoodsTab_CurrentShoppingCartPriceValue.setBounds(135, 11, 145, 22);
        TLayeredPane_GoodsTab.add(TLabel_GoodsTab_CurrentShoppingCartPriceValue);

        //Tab "Warenkorb-Liste"
        TTabbedPane.addTab("Warenkorbliste", null, TLayeredPane_ShoppingCartListTab, null);

        //Tab "Warenkorb-Liste" - TextArea "ShoppingCartValue"
        TTextArea_ShoppingCartListTab_ShoppingCartValue.setForeground(Color.GREEN);
        TTextArea_ShoppingCartListTab_ShoppingCartValue.setEditable(false);
        TTextArea_ShoppingCartListTab_ShoppingCartValue.setBackground(Color.BLACK);
        TTextArea_ShoppingCartListTab_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TTextArea_ShoppingCartListTab_ShoppingCartValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        TLayeredPane_ShoppingCartListTab.add(TTextArea_ShoppingCartListTab_ShoppingCartValue);

        //Tab "Warenkorb-Liste" - Label "CurrentShoppingCart"
        TLabel_ShoppingCartListTab_CurrentShoppingCart.setBounds(12,11,125,22);
        TLayeredPane_ShoppingCartListTab.add(TLabel_ShoppingCartListTab_CurrentShoppingCart);

        //Tab "Warenkorb-Liste" - Label "CurrentShoppingCartPriceValue"
        TLabel_ShoppingCartListTab_CurrentShoppingCartPriceValue.setBounds(135, 11, 145, 22);
        TLayeredPane_ShoppingCartListTab.add(TLabel_ShoppingCartListTab_CurrentShoppingCartPriceValue);

        //Tab "Warenkorb-Liste" - Choice
        TLayeredPane_ShoppingCartListTab.setLayer(TChoice_ShoppingCartListTab, 4);
        TChoice_ShoppingCartListTab.setBounds(387, 42, 132, 20);
        TLayeredPane_ShoppingCartListTab.add(TChoice_ShoppingCartListTab);
        TChoice_ShoppingCartListTab.add("STANDARD");
        TChoice_ShoppingCartListTab.add("ECONOMIC");
        TChoice_ShoppingCartListTab.add("U18");
        TChoice_ShoppingCartListTab.add("EMPLOYEE");
        TChoice_ShoppingCartListTab.add("SAVING");

        //Tab "Warenkorb-Liste" - Button "NewShoppingCart"
        TButton_ShoppingCartListTab_NewShoppingCart.setEnabled(false);
        TButton_ShoppingCartListTab_NewShoppingCart.addActionListener(e -> {
            Backend.createCart(ShoppingCartEnumeration.valueOf(TChoice_ShoppingCartListTab.getSelectedItem()), TTextField_ShoppingCartListTab_ShoppingCartNameField.getText(),TBackend.getPR_I_ShoppingCartCounter());
            TTextField_ShoppingCartListTab_ShoppingCartNameField.setText("");
            TButton_ShoppingCartListTab_NewShoppingCart.setEnabled(false);
            TTextField_Status.setText(TBackend.statusOverall());
            TTextArea_GoodsTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
            TTextArea_ShoppingCartListTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
            TTextArea_ShoppingCartTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
        });
        TButton_ShoppingCartListTab_NewShoppingCart.setBounds(10, 68, 509, 23);
        TLayeredPane_ShoppingCartListTab.add(TButton_ShoppingCartListTab_NewShoppingCart);

        //Tab "Warenkorb-Liste" - TextField "ShoppingCartNameField"
        TTextField_ShoppingCartListTab_ShoppingCartNameField.setBounds(130, 42, 86, 20);
        TLayeredPane_ShoppingCartListTab.add(TTextField_ShoppingCartListTab_ShoppingCartNameField);
        TTextField_ShoppingCartListTab_ShoppingCartNameField.setColumns(10);
        TTextField_ShoppingCartListTab_ShoppingCartNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                TButton_ShoppingCartListTab_NewShoppingCart.setEnabled(!TTextField_ShoppingCartListTab_ShoppingCartNameField.getText().isBlank());
            }
        });

        //Tab "Warenkorb-Liste" - ScrollPane
        TScrollPane_ShoppingCartListTab.setBounds(10, 102, 509, 196);
        TLayeredPane_ShoppingCartListTab.add(TScrollPane_ShoppingCartListTab);

        //Tab "Warenkorb-Liste" - Table&Model
        TTable_ShoppingCartList.setModel(Backend.PT_SF_DTM_ShoppingCartListModel);
        TTable_ShoppingCartList.setShowVerticalLines(false);
        TTable_ShoppingCartList.setShowHorizontalLines(false);
        TTable_ShoppingCartList.setShowGrid(false);
        TScrollPane_ShoppingCartListTab.setViewportView(TTable_ShoppingCartList);
        TTableColumnModel_ShoppingCartList.removeColumn(TTableColumnModel_ShoppingCartList.getColumn(0));
        TTable_ShoppingCartList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JTable Input = (JTable)e.getSource();
                    if (JOptionPane.showOptionDialog(null, "Wollen sie zum Warenkorb: " + Input.getValueAt(Input.getSelectedRow(), 0) + " wechseln?", "Warenkorb wechseln", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
                        TBackend.switchShoppingCart((int) Input.getModel().getValueAt(Input.getSelectedRow(), 0));
                        TLabel_GoodsTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
                        TLabel_ShoppingCartListTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
                        TLabel_ShoppingCartTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());

                        TTextArea_GoodsTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                        TTextArea_ShoppingCartListTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                        TTextArea_ShoppingCartTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                    }
                }
            }});

        //Tab "Warenkorb-Liste" - Label "ShoppingCartName"
        TLabel_ShoppingCartListTab_ShoppingCartName.setBounds(10, 42, 122, 22);
        TLayeredPane_ShoppingCartListTab.add(TLabel_ShoppingCartListTab_ShoppingCartName);

        //Tab "Warenkorb-Liste" - Label "ShoppingCartType"
        TLabel_ShoppingCartListTab_ShoppingCartType.setBounds(290, 42, 122, 22);
        TLayeredPane_ShoppingCartListTab.add(TLabel_ShoppingCartListTab_ShoppingCartType);

        //Tab "Warenkorb-Liste" - Label "CurrentShoppingCartPrice"
        TLabel_ShoppingCartListTab_CurrentShoppingCartPrice.setBounds(290, 11, 122, 22);
        TLayeredPane_ShoppingCartListTab.add(TLabel_ShoppingCartListTab_CurrentShoppingCartPrice);

        //Tab "Warenkorb"
        TTabbedPane.addTab("Warenkorb", null, TLayeredPane_ShoppingCartTab, null);

        //Tab "Warenkorb" - TextArea "DailyTakings"
        TTextArea_ShoppingCartTab_DailyTakings.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        TTextArea_ShoppingCartTab_DailyTakings.setEditable(false);
        TTextArea_ShoppingCartTab_DailyTakings.setBounds(387, 269, 132, 22);
        TTextArea_ShoppingCartTab_DailyTakings.setForeground(new Color(0, 255, 0));
        TTextArea_ShoppingCartTab_DailyTakings.setBackground(new Color(0, 0, 0));
        TLayeredPane_ShoppingCartTab.add(TTextArea_ShoppingCartTab_DailyTakings);

        //Tab "Warenkorb" - TextArea "ShoppingCartValue"
        TTextArea_ShoppingCartTab_ShoppingCartValue.setForeground(new Color(0, 255, 0));
        TTextArea_ShoppingCartTab_ShoppingCartValue.setBackground(new Color(0, 0, 0));
        TTextArea_ShoppingCartTab_ShoppingCartValue.setEditable(false);
        TTextArea_ShoppingCartTab_ShoppingCartValue.setBounds(387, 11, 132, 22);
        TTextArea_ShoppingCartTab_ShoppingCartValue.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        TLayeredPane_ShoppingCartTab.add(TTextArea_ShoppingCartTab_ShoppingCartValue);

        //Tab "Warenkorb" - Model&Table
        TTable_CurrentShoppingCart.setModel(Backend.PT_SF_DTM_ShoppingCartModel);
        TTable_CurrentShoppingCart.setShowGrid(false);
        TTable_CurrentShoppingCart.setShowHorizontalLines(false);
        TTable_CurrentShoppingCart.setShowVerticalLines(false);
        TTable_CurrentShoppingCart.setAutoCreateRowSorter(true);
        TTableColumnModel_CurrentShoppingCart.removeColumn(TTableColumnModel_CurrentShoppingCart.getColumn(0));
        TTable_CurrentShoppingCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    JTable Input = (JTable) e.getSource();

                    JPanel TPanel = new JPanel();
                    TPanel.add(new JLabel("Produkt-Anzahl von \""+Input.getModel().getValueAt(Input.getSelectedRow(),1)+"\" ändern?"));
                    JTextField TTextField = new JTextField(5);
                    TPanel.add(TTextField);

                    switch(JOptionPane.showOptionDialog(null,TPanel,"Produkt-Anzahl ändern / Produkt löschen",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,new Object[] {"Ändern", "Löschen", "Cancel"},null)) {
                        case 0 -> TBackend.changeShoppingItemAmount((int)Input.getModel().getValueAt(Input.getSelectedRow(),0),Integer.parseInt(TTextField.getText()));
                        case 1 -> {
                            TBackend.deleteShoppingItem((int)Input.getModel().getValueAt(Input.getSelectedRow(),0));
                            TTextField_Status.setText(TBackend.statusOverall());
                        }
                    }
                    TTextArea_GoodsTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                    TTextArea_ShoppingCartListTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                    TTextArea_ShoppingCartTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                }
            }
        });

        //Tab "Warenkorb" - ScrollPane
        TScrollPane_ShoppingCartTab.setBounds(10, 43, 509, 220);
        TLayeredPane_ShoppingCartTab.add(TScrollPane_ShoppingCartTab);
        TScrollPane_ShoppingCartTab.setViewportView(TTable_CurrentShoppingCart);

        //Tab "Warenkorb" - Label "CurrentShoppingCartPrice"
        TLabel_ShoppingCartTab_CurrentShoppingCartPrice.setBounds(290, 11, 122, 22);
        TLayeredPane_ShoppingCartTab.add(TLabel_ShoppingCartTab_CurrentShoppingCartPrice);

        //Tab "Warenkorb" - Label "DailyTakings"
        TLabel_ShoppingCartTab_DailyTakings.setBounds(248, 269, 122, 22);
        TLayeredPane_ShoppingCartTab.add(TLabel_ShoppingCartTab_DailyTakings);

        //Tab "Warenkorb" - Button "PayCurrentShoppingCart"
        TButton_ShoppingCartTab_PayCurrentShoppingCart.setBounds(10, 269, 175, 22);
        TLayeredPane_ShoppingCartTab.add(TButton_ShoppingCartTab_PayCurrentShoppingCart);
        TButton_ShoppingCartTab_PayCurrentShoppingCart.addActionListener(e -> {
            if (JOptionPane.showOptionDialog(null,"Wollen sie den Warenkorb wirklich bezahlen?","Warenkorb bezahlen",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null)==0) {
                TBackend.increasePR_D_DailyOverallSellValue(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValueDouble());
                TBackend.removeCurrentShoppingCart();
                TTextArea_GoodsTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                TTextArea_ShoppingCartListTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                TTextArea_ShoppingCartTab_ShoppingCartValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_LSI_ShoppingCartOverallValue());
                TLabel_GoodsTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
                TLabel_ShoppingCartListTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
                TLabel_ShoppingCartTab_CurrentShoppingCartPriceValue.setText(TBackend.getPR_SC_SelectedShoppingCart().getPR_S_Name());
                TTextArea_ShoppingCartTab_DailyTakings.setText(TBackend.addToDailyTakings());
            }
        });

        //Tab "Warenkorb" - Label "CurrentShoppingCart"
        TLabel_ShoppingCartTab_CurrentShoppingCart.setBounds(12,11,125,22);
        TLayeredPane_ShoppingCartTab.add(TLabel_ShoppingCartTab_CurrentShoppingCart);

        //Tab "Warenkorb" - Label "CurrentShoppingCartPriceValue"
        TLabel_ShoppingCartTab_CurrentShoppingCartPriceValue.setBounds(135, 11, 145, 22);
        TLayeredPane_ShoppingCartTab.add(TLabel_ShoppingCartTab_CurrentShoppingCartPriceValue);

        //TextField "Status"
        TScrollPane_Status.setBounds(0, 324, 534, 51);
        TContentPane.add(TScrollPane_Status);
        TTextField_Status.setFont(UIManager.getFont("TextArea.font"));
        TTextField_Status.setBackground(Color.BLACK);
        TTextField_Status.setForeground(Color.GREEN);
        TTextField_Status.setEditable(false);
        TTextField_Status.setBounds(0, 0, 24, 21);
        TScrollPane_Status.add(TTextField_Status);
        TTextField_Status.setText(TBackend.statusOverall());
    }
}
