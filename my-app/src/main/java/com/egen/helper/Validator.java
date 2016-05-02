package com.egen.helper;
/**
 * Validation on Server side
 * @author Ankur
 *
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String ALPHABET_PATTERN = "[a-zA-Z]+";
	private static final String STATE_COUNTRY_PATTERN = "[A-Za-z]{2}";
	private static final String ZIP_PATTERN = "\\d{5}";
	public Validator() {
		
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateEmail(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateOnlyAlphabets(final String hex) {
		pattern = Pattern.compile(ALPHABET_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateStateCountry(final String hex) {
		pattern = Pattern.compile(STATE_COUNTRY_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateZip(final String hex) {
		pattern = Pattern.compile(ZIP_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
	
	 
}
