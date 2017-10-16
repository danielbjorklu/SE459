/**
 * 
 */
package utility;

/**
 * @author omcda
 *
 */
public class Converter {
	
	public static final String convertString(final Object object) {
		return object.toString();
	}
	
	public static final Integer convertInteger(final Object object) {
		return Integer.parseInt(object.toString());
	}

}
