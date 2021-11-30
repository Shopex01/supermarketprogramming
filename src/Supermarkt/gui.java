package Supermarkt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.List;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JPopupMenu;


public class gui extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public gui() {
		
		Supermarkt out = new Supermarkt();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 534, 373);
		contentPane.add(tabbedPane);
		
		JLayeredPane produktLayer = new JLayeredPane();
		tabbedPane.addTab("Produktseite", null, produktLayer, null);
		
		JTextArea textArea = new JTextArea(out.output_produkt());
		textArea.setEditable(false);
		textArea.setFont(UIManager.getFont("TextArea.font"));
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 299, 509, 35);
		produktLayer.add(textArea);
		
		JList list_2_2 = new JList();
		list_2_2.setBounds(10, 42, 509, 246);
		produktLayer.add(list_2_2);
		
		JTextArea txtrAktuellerWarenkorbXy_1 = new JTextArea();
		txtrAktuellerWarenkorbXy_1.setText("Warenkorb XY");
		txtrAktuellerWarenkorbXy_1.setEditable(false);
		txtrAktuellerWarenkorbXy_1.setBounds(10, 11, 110, 20);
		produktLayer.add(txtrAktuellerWarenkorbXy_1);
		
		JTextArea textArea_1_2 = new JTextArea();
		textArea_1_2.setForeground(Color.GREEN);
		textArea_1_2.setEditable(false);
		textArea_1_2.setBackground(Color.BLACK);
		textArea_1_2.setBounds(387, 11, 132, 22);
		produktLayer.add(textArea_1_2);
		
		JTextArea txtrWarenkorbwert_2 = new JTextArea();
		txtrWarenkorbwert_2.setText("Warenkorbwert");
		txtrWarenkorbwert_2.setEditable(false);
		txtrWarenkorbwert_2.setBounds(267, 11, 110, 20);
		produktLayer.add(txtrWarenkorbwert_2);
		
		JLayeredPane warenkorbListLayer = new JLayeredPane();
		tabbedPane.addTab("Warenkorbliste", null, warenkorbListLayer, null);
		
		JTextArea textArea_2 = new JTextArea(out.status_warenkorbliste);
		textArea_2.setEditable(false);
		textArea_2.setWrapStyleWord(true);
		textArea_2.setForeground(Color.GREEN);
		textArea_2.setFont(UIManager.getFont("TextArea.font"));
		textArea_2.setBackground(Color.BLACK);
		textArea_2.setBounds(10, 299, 509, 35);
		warenkorbListLayer.add(textArea_2);
		
		JList list_2_1 = new JList();
		list_2_1.setBounds(10, 94, 509, 194);
		warenkorbListLayer.add(list_2_1);
		
		JTextArea txtrAktuellerWarenkorbXy_2 = new JTextArea();
		txtrAktuellerWarenkorbXy_2.setText("Warenkorb XY");
		txtrAktuellerWarenkorbXy_2.setEditable(false);
		txtrAktuellerWarenkorbXy_2.setBounds(10, 11, 110, 20);
		warenkorbListLayer.add(txtrAktuellerWarenkorbXy_2);
		
		JTextArea textArea_1_1 = new JTextArea();
		textArea_1_1.setForeground(Color.GREEN);
		textArea_1_1.setEditable(false);
		textArea_1_1.setBackground(Color.BLACK);
		textArea_1_1.setBounds(387, 11, 132, 22);
		warenkorbListLayer.add(textArea_1_1);
		
		JTextArea txtrWarenkorbwert_1 = new JTextArea();
		txtrWarenkorbwert_1.setText("Warenkorbwert");
		txtrWarenkorbwert_1.setEditable(false);
		txtrWarenkorbwert_1.setBounds(267, 11, 110, 20);
		warenkorbListLayer.add(txtrWarenkorbwert_1);
		
		JTextArea txtrWarenkorbname = new JTextArea();
		txtrWarenkorbname.setEditable(false);
		txtrWarenkorbname.setText("Warenkorbname");
		txtrWarenkorbname.setBounds(10, 42, 110, 20);
		warenkorbListLayer.add(txtrWarenkorbname);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 42, 86, 20);
		warenkorbListLayer.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrWarenkorbkategorie = new JTextArea();
		txtrWarenkorbkategorie.setText("Warenkorbkategorie");
		txtrWarenkorbkategorie.setEditable(false);
		txtrWarenkorbkategorie.setBounds(226, 42, 151, 20);
		warenkorbListLayer.add(txtrWarenkorbkategorie);
		
		Choice choice = new Choice();
		warenkorbListLayer.setLayer(choice, 4);
		choice.setBounds(387, 42, 132, 20);
		warenkorbListLayer.add(choice);
		
		JButton btnNewButton = new JButton("Neuen Warenkorb erstellen");
		btnNewButton.setBounds(10, 68, 509, 23);
		warenkorbListLayer.add(btnNewButton);
		choice.add("Öko-Prinzip");
		choice.add("U18");
		choice.add("Mitarbeiterkaufprogramm");
		choice.add("Spar-Korb");
		
		JLayeredPane warenkorbLayer = new JLayeredPane();
		tabbedPane.addTab("Warenkorb", null, warenkorbLayer, null);
		
		JList list_2 = new JList();
		list_2.setBounds(10, 42, 509, 218);
		warenkorbLayer.add(list_2);
		
		JTextArea txtrAktuellerWarenkorbXy = new JTextArea();
		txtrAktuellerWarenkorbXy.setText("Warenkorb XY");
		txtrAktuellerWarenkorbXy.setEditable(false);
		txtrAktuellerWarenkorbXy.setBounds(10, 11, 110, 20);
		warenkorbLayer.add(txtrAktuellerWarenkorbXy);
		
		textField = new JTextField();
		textField.setBounds(370, 271, 149, 20);
		warenkorbLayer.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnGutscheincode = new JTextPane();
		txtpnGutscheincode.setEditable(false);
		txtpnGutscheincode.setBackground(new Color(255, 255, 255));
		txtpnGutscheincode.setText("Gutscheincode");
		txtpnGutscheincode.setBounds(250, 271, 110, 20);
		warenkorbLayer.add(txtpnGutscheincode);
		
		JTextArea textArea_2_1 = new JTextArea(out.status_warenkorb);
		textArea_2_1.setEditable(false);
		textArea_2_1.setWrapStyleWord(true);
		textArea_2_1.setForeground(Color.GREEN);
		textArea_2_1.setFont(UIManager.getFont("TextArea.font"));
		textArea_2_1.setBackground(Color.BLACK);
		textArea_2_1.setBounds(10, 299, 509, 35);
		warenkorbLayer.add(textArea_2_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(new Color(0, 255, 0));
		textArea_1.setBackground(new Color(0, 0, 0));
		textArea_1.setEditable(false);
		textArea_1.setBounds(387, 11, 132, 22);
		warenkorbLayer.add(textArea_1);
		
		JTextArea txtrWarenkorbwert = new JTextArea();
		txtrWarenkorbwert.setText("Warenkorbwert");
		txtrWarenkorbwert.setEditable(false);
		txtrWarenkorbwert.setBounds(267, 11, 110, 20);
		warenkorbLayer.add(txtrWarenkorbwert);
	}
}
