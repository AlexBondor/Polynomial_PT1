import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *  The Main class represents the starting point of the application.
 *  
 *  It creates a new object of PolyGUI and adds event listeners on
 *  every button from GUI. It also calls methods of Polynomial and
 *  Operations classes in order to compute the desired operations on
 *  the provided polynomials.
 *     
 *  @author Alexandru Bondor
 */
@SuppressWarnings ("serial")
public class Main extends PolyGUI {

	private static PolyGUI frame;
	private static String contentPoly1 = "",
						  contentPoly2 = "";
	private static Polynomial poly1,
							  poly2,
							  result;
	private static Operations operations = new Operations ();

	public static void main (String[] args) {
		try {
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
			frame = new PolyGUI () {
				@Override
				public void paint (Graphics g) {
		            Dimension d    = getSize ();
		            boolean resize = d.height > 180;
		            d.width  = getWidth();
		            d.height = 180;
		            if (resize) {
		               Point p = getLocation();
		               setVisible  (false);
		               setSize     (d);
		               setLocation (p);
		               setVisible  (true);
		            }
		            super.paint (g);
				}
			};
			InitializeListeners ();
			frame.setVisible (true);
		} 
		catch (Exception e) {
			e.printStackTrace ();
		} 
	}
	
	private static void InitializeListeners () {
		
		//-------  BUTTONS LISTENERS  -------//

		frame.btnRand_1.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
                frame.textFieldPoly_1.setText (operations.getRandomPoly ().toString ());
                contentPoly1 = frame.textFieldPoly_1.getText ();
            }
        });
		
		frame.btnRand_2.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	frame.textFieldPoly_2.setText (operations.getRandomPoly ().toString ());
                contentPoly2 = frame.textFieldPoly_2.getText ();
            }
        });
		
		frame.btnAdd.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	poly1 = new Polynomial (contentPoly1);
            	poly2 = new Polynomial (contentPoly2); 
            	// if both poly's were successfully created
            	if (poly1.getStatus () && poly2.getStatus ()) {
            		if (poly1.getSize () != 0)
            			frame.textFieldPoly_1.setText (poly1.toString ());
            		if (poly2.getSize () != 0)
            			frame.textFieldPoly_2.setText (poly2.toString ());
            		result = operations.addPolynomials (poly1, poly2);
            		frame.textFieldResult.setText (result.toString ());
            	}
            	else {
            		frame.textFieldResult.setText ("An error occured. Check input spelling!");
            	}
            }
        });
		
		frame.btnSub.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	poly1 = new Polynomial (contentPoly1);
            	poly2 = new Polynomial (contentPoly2); 
            	if (poly1.getStatus () && poly2.getStatus ()) {
            		if (poly1.getSize () != 0)
            			frame.textFieldPoly_1.setText (poly1.toString ());
            		if (poly2.getSize () != 0)
            			frame.textFieldPoly_2.setText (poly2.toString ());
            		result = operations.subPolynomials (poly1, poly2);
            		frame.textFieldResult.setText (result.toString());
            	}
            	else {
            		frame.textFieldResult.setText ("An error occured. Check input spelling!");
            	}
            }
        });
		
		frame.btnMultiply.addActionListener  (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	poly1 = new Polynomial (contentPoly1);
            	poly2 = new Polynomial (contentPoly2); 
            	if (poly1.getStatus () && poly2.getStatus ()) {
            		if (poly1.getSize () != 0)
            			frame.textFieldPoly_1.setText (poly1.toString ());
            		if (poly2.getSize () != 0)
            			frame.textFieldPoly_2.setText (poly2.toString ());
            		result = operations.multiplyPolynomials (poly1, poly2);
            		if (result.getSize () == 0)
                		frame.textFieldResult.setText ("0");
            		else {
            			frame.textFieldResult.setText (result.toString());
            		}
            	}
            	else {
            		frame.textFieldResult.setText ("An error occured. Check input spelling!");
            	}
            }
        });
		
		frame.btnDeriv.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	Object[] options = {"Poly1", "Poly2"};
            	Polynomial poly;
			    int n = JOptionPane.showOptionDialog (
			    		frame,
				        "Which poly would you like to derivate?",
				        "A Silly Question",
				        JOptionPane.YES_NO_OPTION,
				        JOptionPane.QUESTION_MESSAGE,
				        null,
				        options,
				        options[0]);
			    if (n == 0 || n == 1){
				    if (n == 1) {
		            	poly = new Polynomial (contentPoly2);
				    }
				    else {
				    	poly = new Polynomial (contentPoly1);
				    }
	            	if (poly.getStatus ()) {
	            		result = operations.derivPolynomial (poly);
	            		frame.textFieldResult.setText (result.toString ());
	            	}
			    }
            }
        });
		
		frame.btnP1.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	String text = frame.textFieldResult.getText ();
            	if (!text.isEmpty () && !text.equalsIgnoreCase ("An error occured. Check input spelling!")) {
	        		contentPoly1 = frame.textFieldResult.getText ();
	        		frame.textFieldPoly_1.setText (contentPoly1);
            	}	
            }
        });
		
		frame.btnP2.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e)
            {
            	String text = frame.textFieldResult.getText ();
            	if (!text.isEmpty () && !text.equalsIgnoreCase ("An error occured. Check input spelling!")) {
	        		contentPoly2 = frame.textFieldResult.getText ();
	        		frame.textFieldPoly_2.setText (contentPoly2);
            	}	
            }
        });
		
		//-------  TEXTFIELD LISTENERS  -------//
		 
		frame.textFieldPoly_1.addFocusListener (new FocusListener () {
			@Override
			public void focusLost (FocusEvent e) {
				contentPoly1 = frame.textFieldPoly_1.getText ();
			}
			@Override
			public void focusGained (FocusEvent e) {}
		});
		
		frame.textFieldPoly_2.addFocusListener (new FocusListener () {
			@Override
			public void focusLost (FocusEvent e) {
				contentPoly2 = frame.textFieldPoly_2.getText ();
			}
			@Override
			public void focusGained (FocusEvent e) {}
		});
	}
}