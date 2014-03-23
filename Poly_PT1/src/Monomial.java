import java.util.Comparator;

/**
 *  The Monomial class represents a term of a polynomial.
 *  
 *  It supports the get/set operations for both the coefficient
 *  and the degree of the monomial.
 *  
 *  It also provides two types of instantiation: simple call
 *  of Monomial () initializes both coefficient and degree with 0,
 *  while Monomial (int c, int d) initializes coefficient with 'c'
 *  and degree with 'd'.
 *     
 *  @author Alexandru Bondor
 */
public class Monomial {
	
	private int coefficient; // coefficient of the Monomial
	private int degree;      // degree of the Monomial
	public final Comparator <Monomial> DEGREE_ORDER = new MonomialOrder (); // used for sorting the Monomials
	
	/**
	 * Sets the rule of comparison between two monomials.
	 */
	private class MonomialOrder implements Comparator <Monomial> {
    	/**
    	 * @param m1 first monomial
    	 * @param m2 second monomial
    	 * @return  1 if m1.degree < m2.degree;
    	 * @return  0 if m1.degree == m2.degree;
    	 * @return -1 if m1.degree > m2.degree;
    	 */
        public int compare (Monomial m1, Monomial m2) {
            double d = m1.degree - m2.degree;
            if (d <  0) return  1;
            if (d == 0) return  0;
            else        return -1;
        }
    }
	
	/**
	 * Monomial constructor without parameters;
	 * Calls this (0, 0);
	 */
	public Monomial () {
		this (0, 0);
	}
	
	/**
	 * Monomial constructor with parameters
	 * 
	 * @param c int coefficient of the Monomial
	 * @param d int degree of the Monomial
	 */
	public Monomial (int c, int d) {
		coefficient = c;
		degree      = d;
	}
	
	/**
	 * Sets the coefficient of the Monomial
	 * 
	 *  @param     c int value to set the coefficient
	 *  @return void
	 */
	public void setCoefficient (int c) {
		coefficient = c;
	}
	
	/**
	 * Returns the coefficient of the Monomial
	 * 
	 *  @return coefficient int value of the coefficient
	 */
	public int getCoefficient () {
		return coefficient;
	}
	
	/**
	 * Sets the degree of the Monomial
	 * 
	 *  @param     d int value to set the degree
	 *  @return void
	 */	
	public void setDegree (int d) {
		degree = d;
	}
	
	/**
	 * Returns the degree of the Monomial
	 * 
	 *  @return degree int value of the degree
	 */	
	public int getDegree () {
		return degree;
	}

}