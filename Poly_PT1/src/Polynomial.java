import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  The Polynomial class represents a class which defines a List of Monomials.
 *  
 *  It supports multiple operations like:
 *  - getStatus ()        - returns the status of poly (ready or not)
 *  - getSize ()          - returns the number of elements of poly
 *  - getMonomial (int i) - returns monomial on position i in poly
 *  - simplifyMe ()       - adds coefficients of monomials having the same degree
 *  - toString ()         - returns the stringified poly
 *  - get/setVariable ()  - get/set the variable name of the poly
 *  - add (Monomial m)    - adds Monomial m to poly
 *  - getPoly ()          - returns the poly
 *     
 *  @author Alexandru Bondor
 */
public class Polynomial {
	
	private List <Monomial> poly        = new ArrayList <Monomial> (); // holds a list of monomials
	private Parser          parser      = new Parser ();               // used to fill the poly with parsed input
	private Operations      operations  = new Operations ();           // see simplifyMe ();
	private boolean         ready       = false;                       // flag to know that poly was successfully created
	private char            variable    = '\0';                        // program supports usage of any variable name
	
	/**
	 * Polynomial constructor without parameters
	 */
	public Polynomial () {
		this ("");
	}
	
	/**
	 * Polynomial constructor with parameters
	 * @param content String to be parsed then filled
	 * 		  in private List <Monomial> poly
	 */
	public Polynomial (String content) {
		// if content successfully parsed
		if (parser.parseString (content)) {
			// fill poly with parsed input
			poly = parser.getPoly ();
			// set variable name
			setVariable (parser.getVariable ());
			if (poly.size () != 0) {
				// sort descending the items of the poly by degree
				Collections.sort (poly, poly.get (0).DEGREE_ORDER);
			}
			// by applying simplification on x + x
			// results 2x
			simplifyMe ();
			ready = true;
		}
	}
	
	/**
	 * @return true  if poly successfully created
	 * 		   false if poly creation failed
	 */
	public boolean getStatus () {
		return ready;
	}
	
	/**
	 * @return int number of elements in poly
	 */
	public int getSize () {
		return poly.size ();
	}
	
	/**
	 * Returns the Monomial from position i in poly
	 * 
	 * @param i int position of monomial in poly
	 * @return Monomial m from position i in poly
	 */
	public Monomial getMonomial (int i) {
		return poly.get (i);
	}
	
	/**
	 * Adds coefficients of monomials having the same degree.
	 * From 2 monomials of same degree this method creates a single
	 * Monomial. The 2 monomials having the same degree are deleted
	 * from poly and the created Monomial is added on position of the
	 * second removed monomial.
	 */
	public void simplifyMe () {
		Monomial term, m1, m2;
		for (int i = 0; i < poly.size () - 1; i++) {
			m1 = poly.get (i);
			m2 = poly.get (i + 1);
			if (m1.getDegree () == m2.getDegree ()) {
				term = operations.addMonomials (m1, m2);
				poly.add (i, term);
				poly.remove (i + 2);
				poly.remove (i + 1);
				i--;
			}
			else
			{
				if (m2.getCoefficient () == 0) {
					poly.remove (i + 1);
					i--;
				}
				if (m1.getCoefficient () == 0) {
					poly.remove (i);
					i--;
				}
			}
		}
	}
	
	/**
	 * Computes the stringified poly applying a set
	 * of rules over the Monomials of the poly
	 * 
	 * @return result String representing stringified poly
	 */
	public String toString () {
		String result = "";
		Monomial m;
		int c, d;
		char s = getVariable ();
		for (int i = 0; i < poly.size (); i++) {
			m = poly.get (i);
			c = m.getCoefficient ();
			d = m.getDegree ();
			if (c > 0 && i != 0) {
				result = result.concat ("+");
			}
			if (c == 1 && d == 0) {
				result = result.concat ("1");
			}
			if (c != 1) {
				if (c == -1 && d != 0) {
					result = result.concat ("-");
				}
				else {
					result = result.concat (String.valueOf (c));
				}
			}
			if (d != 0) {
				if (d == 1 && c != 0) {
					result = result.concat (String.valueOf (s));
				}
				else {
					if (c != 0) {
						result = result.concat (s + "^" + String.valueOf (d));
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param c char to set variable to
	 */
	public void setVariable (char c) {
		variable = c;
	}
	
	/**
	 * @return variable char representing variable name for the current poly
	 */
	public char getVariable () {
		return variable;
	}
	
	/**
	 * @param m Monomial to be added to poly
	 */
	public void add (Monomial m) {
		poly.add (m);
	}
	
	/**
	 * @return List <Monomial> representing current poly
	 */
	public List<Monomial> getPoly () {
		return poly;
	}
}