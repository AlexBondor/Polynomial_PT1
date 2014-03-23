import java.util.Collections;
import java.util.Random;

/**
 *  The Polynomial class represents a class which defines a List of Monomials.
 *  
 *  It supports multiple operations like:
 *  - addPolynomials (Polynomial poly1, Polynomial poly2)      - returns result = poly1 + poly2
 *  - subPolynomials (Polynomial poly1, Polynomial poly2)      - returns result = poly1 - poly2
 *  - multiplyPolynomials (Polynomial poly1, Polynomial poly2) - returns result = poly1 * poly2
 *  - derivPolynomial (Polynomial poly1)                       - returns the derivate of poly1
 *  - getRandomPoly ()                                         - returns random poly
 *  - derivMonomial (Monomial m)                               - returns Monomial result = m'
 *  - addMonomials (Monomial m1, Monomial m2)                  - returns Monomial m = m1 + m2
 *  - multiplyMonomials (Monomial m1, Monomial m2)             - returns Monomial m = m1 * m2
 *  - getRandomMonomial ()                                     - returns a random Monomial
 *     
 *  @author Alexandru Bondor
 */
public class Operations {
	
	/**
	 * Performs an addition of poly1 with poly2 and
	 * returns their sum as Polynomial.
	 * @param poly1 augend of addition
	 * @param poly2 addend of addition
	 * @return Polynomial sum = poly1 + poly2
	 */
	public Polynomial addPolynomials (Polynomial poly1, Polynomial poly2) {
		Polynomial result = new Polynomial ();
		if (Character.isLetter (poly1.getVariable ()))
			result.setVariable (poly1.getVariable ());
		else
			result.setVariable (poly2.getVariable ());
		Monomial m = new Monomial();
		int i;
		for (i = 0; i < poly1.getSize (); i++) {
			m = poly1.getMonomial (i);
			result.add (m);
		}
		for (i = 0; i < poly2.getSize (); i++) {
			m = poly2.getMonomial (i);
			result.add (m);
		}
		if (result.getSize () != 0) {
			Collections.sort (result.getPoly (), result.getMonomial (0).DEGREE_ORDER);
		}
		else {
			result.add(new Monomial (0, 0));
		}
		// clear terms with coefficient 0
		result.simplifyMe ();
		return result;
	}
	
	/**
	 * Performs the subtraction of poly1 by poly2 and
	 * returns their difference as Polynomial.
	 * @param poly1 minuend of subtraction
	 * @param poly2 subtrahend of subtraction
	 * @return Polynomial difference = poly1 - poly2
	 */
	public Polynomial subPolynomials (Polynomial poly1, Polynomial poly2) {
		Polynomial result = new Polynomial ();
		if (Character.isLetter (poly1.getVariable ()))
			result.setVariable (poly1.getVariable ());
		else
			result.setVariable (poly2.getVariable ());
		int elements = poly2.getSize ();
		for (int i = 0; i < elements; i++) {
			poly2.getMonomial (i).setCoefficient (-poly2.getMonomial (i).getCoefficient ());
		}
		result = addPolynomials (poly1, poly2);
		if (result.getSize () != 0) {
			Collections.sort(result.getPoly (), result.getMonomial (0).DEGREE_ORDER);
		}
		else {
			result.add (new Monomial (0, 0));
		}
		result.simplifyMe ();
		// clear terms with coefficient 0
		return result;
	}

	/**
	 * Performs the multiplication of poly1 with poly2 and
	 * returns their product as Polynomial.
	 * @param poly1 multiplicand of multiplication
	 * @param poly2 multiplier of multiplication
	 * @return Polynomial multiplication = poly1 * poly2
	 */
	public Polynomial multiplyPolynomials (Polynomial poly1, Polynomial poly2) {
		Polynomial result = new Polynomial ();
		if (Character.isLetter (poly1.getVariable ()))
			result.setVariable (poly1.getVariable ());
		else
			result.setVariable(poly2.getVariable ());
		int i1,
			i2,
			s1 = poly1.getSize (),
			s2 = poly2.getSize ();
		Monomial mul = new Monomial (),
				 m1 = new Monomial (),
				 m2 = new Monomial ();
		for (i1 = 0; i1 < s1; i1++) {
			m1 = poly1.getMonomial (i1);
			for (i2 = 0; i2 < s2; i2++) {
				m2 = poly2.getMonomial (i2);
				mul = multiplyMonomials (m1, m2);
				result.add (mul);
			}
		}
		if (result.getSize () != 0) {
			Collections.sort (result.getPoly (), result.getMonomial (0).DEGREE_ORDER);
		}
		else {
			result.add (new Monomial (0, 0));
		}
		result.simplifyMe ();
		return result;
	}
	
	/**
	 * Performs the derivation on poly1 and returns
	 * the result
	 * @param poly1 Polinomial to be differentiated
	 * @return result of derivation of poly1
	 */
	public Polynomial derivPolynomial (Polynomial poly1) {
		Polynomial result = new Polynomial ();
		result.setVariable (poly1.getVariable ());
		int elements = poly1.getSize ();
		for (int i = 0; i < elements; i++) {
			result.add (derivMonomial (poly1.getMonomial (i)));
		}
		if (result.getSize() != 0) {
			Collections.sort(result.getPoly (), result.getMonomial (0).DEGREE_ORDER);
		}
		else {
			result.add (new Monomial (0, 0));
		}
		result.simplifyMe ();
		return result;
	}
	
	/**
	 * @return random Polynomial
	 */
	public Polynomial getRandomPoly () {
		Polynomial result = new Polynomial ();
		Monomial m  = new Monomial ();
		Random rand = new Random ();
		int  elements = rand.nextInt (5) + 1;
		for (int i = 0; i < elements; i++) {
			m = getRandomMonomial ();
			result.add (m);
		}
		if (result.getSize () != 0) {
			Collections.sort (result.getPoly (), result.getMonomial (0).DEGREE_ORDER);
		}
		else {
			result.add(new Monomial (0, 0));
		}
		result.setVariable ('x');
		result.simplifyMe ();
		return result;
	}
	
	/**
	 * Performs the derivation on Monomial m and returns
	 * the result
	 * @param m Monomial to be differentiated
	 * @return result of derivation of m
	 */
	private Monomial derivMonomial (Monomial m) {
		Monomial deriv = new Monomial ();
		int c = m.getCoefficient ();
		int d = m.getDegree ();
		if (d == 0)
			return deriv;
		deriv.setCoefficient (c * d);
		deriv.setDegree (d - 1);
		return deriv;
	}
	
	/**
	 * Performs an addition of m1 with m2 and
	 * returns their sum as Monomial.
	 * @param m1 augend of addition
	 * @param m2 addend of addition
	 * @return Monomial sum = m1 + m2
	 */
	public Monomial addMonomials (Monomial m1, Monomial m2) {
		Monomial sum = new Monomial ();
		int c1 = m1.getCoefficient ();
		int c2 = m2.getCoefficient ();
		sum.setDegree (m1.getDegree ());
		sum.setCoefficient (c1 + c2);
		return sum;
	}
	
	/**
	 * Performs the multiplication of m1 with m2 and
	 * returns their product as Monomial.
	 * @param m1 multiplicand of multiplication
	 * @param m2 multiplier of multiplication
	 * @return Monomial result = m1 * m2
	 */
	private Monomial multiplyMonomials (Monomial m1, Monomial m2) {
		Monomial mul = new Monomial ();
		int c1 = m1.getCoefficient ();
		int c2 = m2.getCoefficient ();
		int d1 = m1.getDegree ();
		int d2 = m2.getDegree ();
		mul.setDegree (d1 + d2);
		mul.setCoefficient (c1 * c2);
		return mul;
	}

	/**
	 * @return random Monomial
	 */
	private Monomial getRandomMonomial () {
		Monomial m = new Monomial ();
		Random rand = new Random();
		// random coefficient between -25 and 24
		int c = rand.nextInt (50) - 25;
		// random degree between 0 and 9
		int d = rand.nextInt (10);
		m.setCoefficient (c);
		m.setDegree (d);
		return m;
	}
}