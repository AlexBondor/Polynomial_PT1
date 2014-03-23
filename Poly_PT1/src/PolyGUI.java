import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *  The PolyGUI class represents a class which defines the GUI of the program.
 *     
 *  @author Alexandru Bondor
 */
@SuppressWarnings("serial")
public class PolyGUI extends JFrame {

	protected JPanel contentPane;
	
	protected JTextField textFieldPoly_1,
				   		 textFieldPoly_2,
			   		 	 textFieldResult;
	
	protected JButton btnRand_1,
					  btnRand_2,
					  btnAdd,
					  btnSub,
					  btnMultiply,
					  btnDeriv,
					  btnP1,
					  btnP2;
	protected JLabel  lblPoly_1,
				      lblPoly_2,
				      lblResult;
	protected GridBagLayout      gbl_contentPane;
	protected GridBagConstraints gbc_lblPoly_1,
						         gbc_lblPoly_2,
						         gbc_lblResult,
						         gbc_textFieldPoly_1,
						         gbc_textFieldPoly_2,
						         gbc_textFieldResult,
						         gbc_btnRand_1,
						         gbc_btnRand_2,
						         gbc_btnAdd,
						         gbc_btnSub,
						         gbc_btnMultiply,
						         gbc_btnDeriv,
						         gbc_btnP1,
						         gbc_btnP2;

	/**
	 * Create the frame.
	 */
	public PolyGUI () {
		setTitle ("Poly Computation");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setBounds (100, 100, 500, 180);
		setMinimumSize (new Dimension(500, 180));
		
		contentPane = new JPanel ();
		contentPane.setBorder (new EmptyBorder (5, 5, 5, 5));
		setContentPane (contentPane);
		gbl_contentPane = new GridBagLayout ();
		gbl_contentPane.columnWidths = new int[] {46, 80, 80, 80, 80, 47, 47, 0};
		gbl_contentPane.rowHeights = new int[] {31, 31, 30, 30, 0};
		gbl_contentPane.columnWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout (gbl_contentPane);
		
		//--------  LABELS HERE  --------//
		
		lblPoly_1 = new JLabel ("Poly 1:");
		lblPoly_1.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_lblPoly_1 = new GridBagConstraints();
		gbc_lblPoly_1.fill = GridBagConstraints.BOTH;
		gbc_lblPoly_1.insets = new Insets (0, 0, 5, 5);
		gbc_lblPoly_1.gridx = 0;
		gbc_lblPoly_1.gridy = 0;
		contentPane.add (lblPoly_1, gbc_lblPoly_1);

		lblPoly_2 = new JLabel ("Poly 2:");
		lblPoly_2.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_lblPoly_2 = new GridBagConstraints ();
		gbc_lblPoly_2.fill = GridBagConstraints.BOTH;
		gbc_lblPoly_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblPoly_2.gridx = 0;
		gbc_lblPoly_2.gridy = 1;
		contentPane.add (lblPoly_2, gbc_lblPoly_2);

		lblResult = new JLabel ("Result:");
		lblResult.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.fill = GridBagConstraints.BOTH;
		gbc_lblResult.insets = new Insets (0, 0, 0, 5);
		gbc_lblResult.gridx = 0;
		gbc_lblResult.gridy = 3;
		contentPane.add (lblResult, gbc_lblResult);
		
		
		//--------  BUTTONS HERE  --------//
		
		btnRand_1 = new JButton ("Rand");
		btnRand_1.setToolTipText ("Sets a random polynomial for 'Poly 1'.");
		btnRand_1.setBackground (Color.GRAY);
		btnRand_1.setFont(new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnRand_1 = new GridBagConstraints();
		gbc_btnRand_1.gridwidth = 2;
		gbc_btnRand_1.fill = GridBagConstraints.BOTH;
		gbc_btnRand_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnRand_1.gridx = 5;
		gbc_btnRand_1.gridy = 0;
		contentPane.add (btnRand_1, gbc_btnRand_1);
		
		btnRand_2 = new JButton ("Rand");
		btnRand_2.setToolTipText ("Sets a random polynomial for 'Poly 2'.");
		btnRand_2.setBackground (Color.GRAY);
		btnRand_2.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnRand_2 = new GridBagConstraints();
		gbc_btnRand_2.gridwidth = 2;
		gbc_btnRand_2.fill = GridBagConstraints.BOTH;
		gbc_btnRand_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnRand_2.gridx = 5;
		gbc_btnRand_2.gridy = 1;
		contentPane.add (btnRand_2, gbc_btnRand_2);
		
		btnAdd = new JButton ("Add");
		btnAdd.setToolTipText ("Performs addition of the two provided polyinomials.");
		btnAdd.setBackground (UIManager.getColor ("Menu.selectionBackground"));
		btnAdd.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnAdd = new GridBagConstraints ();
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets (0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 2;
		contentPane.add (btnAdd, gbc_btnAdd);
		
		btnSub = new JButton ("Sub");
		btnSub.setToolTipText ("Performs subtraction of the two provided polyinomials. (Poly1 - Poly2)");
		btnSub.setBackground (UIManager.getColor("Menu.selectionBackground"));
		btnSub.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnSub = new GridBagConstraints();
		gbc_btnSub.fill = GridBagConstraints.BOTH;
		gbc_btnSub.insets = new Insets(0, 0, 5, 5);
		gbc_btnSub.gridx = 2;
		gbc_btnSub.gridy = 2;
		contentPane.add (btnSub, gbc_btnSub);
		
		btnMultiply = new JButton ("Multiply");
		btnMultiply.setToolTipText ("Performs multiplication of the two provided polyinomials.");
		btnMultiply.setBackground(UIManager.getColor ("Menu.selectionBackground"));
		btnMultiply.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnMultiply = new GridBagConstraints ();
		gbc_btnMultiply.fill = GridBagConstraints.BOTH;
		gbc_btnMultiply.insets = new Insets(0, 0, 5, 5);
		gbc_btnMultiply.gridx = 3;
		gbc_btnMultiply.gridy = 2;
		contentPane.add (btnMultiply, gbc_btnMultiply);
		
		btnDeriv = new JButton ("Deriv");
		btnDeriv.setToolTipText ("Performs derivation of the specified polyinomial.");
		btnDeriv.setBackground (UIManager.getColor("Menu.selectionBackground"));
		btnDeriv.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		gbc_btnDeriv = new GridBagConstraints ();
		gbc_btnDeriv.fill = GridBagConstraints.BOTH;
		gbc_btnDeriv.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeriv.gridx = 4;
		gbc_btnDeriv.gridy = 2;
		contentPane.add (btnDeriv, gbc_btnDeriv);
		
		btnP1 = new JButton("P1");
		btnP1.setToolTipText ("Sets Poly1 to Result.");
		btnP1.setBackground(UIManager.getColor("Button.darkShadow"));
		btnP1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_btnP1 = new GridBagConstraints();
		gbc_btnP1.insets = new Insets(0, 0, 0, 5);
		gbc_btnP1.gridx = 5;
		gbc_btnP1.gridy = 3;
		contentPane.add(btnP1, gbc_btnP1);
		
		btnP2 = new JButton("P2");
		btnP2.setToolTipText ("Sets Poly2 to Result.");
		btnP2.setBackground(UIManager.getColor("Button.darkShadow"));
		btnP2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc_btnP2 = new GridBagConstraints();
		gbc_btnP2.gridx = 6;
		gbc_btnP2.gridy = 3;
		contentPane.add(btnP2, gbc_btnP2);

		//--------  TEXTFIELDS HERE  --------//
		
		textFieldPoly_1 = new JTextField ();
		textFieldPoly_1.setFont (new Font("Tahoma", Font.PLAIN, 15));
		textFieldPoly_1.setColumns (10);
		gbc_textFieldPoly_1 = new GridBagConstraints ();
		gbc_textFieldPoly_1.fill = GridBagConstraints.BOTH;
		gbc_textFieldPoly_1.insets = new Insets (0, 0, 5, 5);
		gbc_textFieldPoly_1.gridwidth = 4;
		gbc_textFieldPoly_1.gridx = 1;
		gbc_textFieldPoly_1.gridy = 0;
		contentPane.add (textFieldPoly_1, gbc_textFieldPoly_1);

		textFieldPoly_2 = new JTextField ();
		textFieldPoly_2.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		textFieldPoly_2.setColumns (10);
		gbc_textFieldPoly_2 = new GridBagConstraints ();
		gbc_textFieldPoly_2.fill = GridBagConstraints.BOTH;
		gbc_textFieldPoly_2.insets = new Insets (0, 0, 5, 5);
		gbc_textFieldPoly_2.gridwidth = 4;
		gbc_textFieldPoly_2.gridx = 1;
		gbc_textFieldPoly_2.gridy = 1;
		contentPane.add (textFieldPoly_2, gbc_textFieldPoly_2);

		textFieldResult = new JTextField ();
		textFieldResult.setToolTipText("Result will be displayed here.");
		textFieldResult.setEditable (false);
		textFieldResult.setFont (new Font ("Tahoma", Font.PLAIN, 15));
		textFieldResult.setColumns (10);
		gbc_textFieldResult = new GridBagConstraints ();
		gbc_textFieldResult.fill = GridBagConstraints.BOTH;
		gbc_textFieldResult.insets = new Insets (0, 0, 0, 5);
		gbc_textFieldResult.gridwidth = 4;
		gbc_textFieldResult.gridx = 1;
		gbc_textFieldResult.gridy = 3;
		contentPane.add (textFieldResult, gbc_textFieldResult);
	}
}
