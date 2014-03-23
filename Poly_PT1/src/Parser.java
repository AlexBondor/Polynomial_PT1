import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  The Parser class represents a tool used to parse the input.
 *  
 *  It supports multiple operations like:
 *  - isValid       (String input) - validates the input
 *  - parseString   (String input) - parses the String to List<Monomial>
 *  - formatTerm    (String term)  - applies a template over the specified term
 *  - decomposeTerm (String term)  - gets a String and returns a Monomial
 *  - get/setVariable () - program supports usage of any variable name
 *  
 *     
 *  @author Alexandru Bondor
 */
public class Parser {
	
	private char variable = '\0'; // variable used by user
	private List <Monomial> poly = new ArrayList <Monomial> (); // polynomial returned after creation
	
	/**
	 * Applies a set of rules over the input String
	 * in order to validate it. If the input String
	 * doesn't break any rule then the String is valid.
	 * 
	 * @param input String to be validated
	 * @return true if input is valid
	 */
	private boolean isValid (String input) {
		
		input = input.replaceAll ("\\s", "");
		if (input.length () == 0) {
			return false;
		}
		
		char 	ch;
		boolean variableFound = false,
				 specialFound = false,
				 	 powFound = false;
		for (int i = 0; i < input.length (); i++) {
			ch = input.charAt (i);
			if (!Character.isLetter (ch) && !Character.isDigit (ch) && ch != '+' && ch != '-' && ch != '^') {
				// found other char that [a-z][A-Z][0-9]+-^
				// input not valid
				return false;
			}
			else {
				if (specialFound && (ch == '+' || ch == '-' || ch == '^')) {
					// there are 2 neighbor special characters
					// example: x^5+-x
					// input not valid
					return false;
				}
				else {
					if (!variableFound && Character.isLetter (ch)) {
						// set variable and flag
						specialFound  = false;
						variable      = ch;
						variableFound = true;
					}
					else {
						if (Character.isLetter (ch) && ch != variable) {
							// found other letter than the first
							// letter found
							// example: x^5+a
							// input not valid
							return false;
						}
						else {
							if (Character.isDigit (ch) || Character.isLetter (ch)) {
								specialFound = false;
								if (Character.isLetter (ch)    && 
									input.charAt(i - 1) == '0' && 
									"+-".contains (String.valueOf (input.charAt (i - 2)))) 
								{
									// found +/-0x
									// input not valid
									return false;
								}
								if (i > 0 && powFound && Character.isLetter (ch)) {
									// found character after ^
									// example: ^a
									// input not valid
									return false;
								}
								else {
									if (i > 0 && Character.isDigit (ch) && Character.isLetter (input.charAt (i - 1))) {
										// found digit right after letter
										// example: a2
										// input not valid
										return false;
									}
								}
							}
							else {
								if (ch == '+' || ch == '-' || ch == '^') {
									specialFound = true;
									if (i == input.length () - 1 || (i == 0 && ch == '^')) {
										// input starts or ends with 
										// special character
										// input not valid
										return false;
									}
									if (ch == '^') {
										powFound = true;
									}
									else {
										powFound = false;
									}
									if (i > 0 && !Character.isLetter (input.charAt (i - 1)) && powFound) {
										// if anything but character
										// before power character '^'
										// input not valid
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Applies a template over the specified term
	 * example: if term is 'x' it will return '1x^1'
	 * 
	 * @param term String to be formatted
	 * @return String formatted term
	 */
	private String formatTerm (String term) {
		String s = String.valueOf (variable);
		if (term.startsWith (s)) {
			term = term.replace (s, "1" + s);
		}
		if (term.startsWith ("-" + s)) {
			term = term.replace ("-" + s, "-1" + s);
		}
		if (term.startsWith ("+" + s)) {
			term = term.replace ("+" + s, "+1" + s);
		}
		if (term.endsWith (s)) {
			term = term.concat("^1");
		}
		if (!term.contains(s)) {
			term = term.concat(s + "^0");
		}
		return term;
	}
	
	/**
	 * Extracts the coefficient and degree from
	 * term String. Then it composes a Monomial
	 * and sets its coefficient/degree to the
	 * ones extracted from term String
	 * 
	 * @param term String to extract coefficient & degree from
	 * @return Monomial with coefficient & degree extracted from term
	 */
	private Monomial decomposeTerm (String term) {
		Monomial monomial = new Monomial();
		String[] data;
		
		data = term.split("("+ variable +"\\^)");
		monomial.setCoefficient(Integer.parseInt(data[0]));
		monomial.setDegree(Integer.parseInt(data[1]));
		
		return monomial;
	}
	
	/**
	 * Takes an input String and parses it if String is
	 * valid. By parsing it, the private List <Monomial> poly
	 * is also built.
	 * 
	 * @param input String to be parsed
	 * @return true  if input parsed
	 * @return false if parsing failed
	 */
	public boolean parseString (String input) {
		if (input == "")
			return false;
		if (!isValid (input)) {
			System.out.println ("Validation failed!");
			return false;
		}
		input = input.replaceAll("\\s", "");
		
		Pattern pattern = Pattern.compile("((\\+|-)?([1-9][0-9]*)?(([a-z]|[A-Z])(\\^[0-9]*)?))|((\\+|-)?([1-9][0-9]*))");
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find()) {
                String term = matcher.group();
                term = formatTerm (term);
                //System.out.println(term);
                if (term != null) {
                	poly.add(decomposeTerm (term));
                }
        }
		return true;
	}
	
	/** 
	 * @return poly List <Monomial> poly built from parsed input
	 */
	public List<Monomial> getPoly () {
		return poly;
	}
	
	/**
	 * @return variable char set while parsing input
	 */
	public char getVariable () {
		return variable;
	}
	
	/**
	 * @param ch char to set private char variable to
	 */
	public void setVariable (char ch) {
		variable = ch;
	}
}