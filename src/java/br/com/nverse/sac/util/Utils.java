package br.com.nverse.sac.util;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Util.java
 * 
 */
@SuppressWarnings("rawtypes")
public final class Utils {

	private Utils() {

	}
	public static boolean isNull(Object objeto) {

		return objeto == null;
	}

	public static boolean isNotNull(Object objeto) {

		return objeto != null;
	}


	public static boolean isNotNullAndZero(Long number) {

		return number != null && number != 0;
	}

	public static boolean isNotNullAndZero(Integer number) {

		return number != null && number != 0;
	}

	public static boolean isNullOrZero(Number nb) {

		return nb == null || nb.intValue() == 0;
	}

	public static boolean isNullOrEmpty(List lista) {

		return lista == null || lista.isEmpty();
	}

	public static boolean isNullOrEmpty(Object[] lista) {

		return lista == null || lista.length == 0;
	}

	public static boolean isNotNullAndEmpty(List lista) {

		return lista != null && !lista.isEmpty();
	}
	
	public static boolean isNotNullAndEmpty(Set set) {

		return set != null && !set.isEmpty();
	}

	public static boolean isNullOrEmpty(Map<String, Object> map) {

		return map == null || map.isEmpty();
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

//	public static boolean isEmpty(String str) {
//
//		return StringUtils.isEmpty(str);
//	}

//	public static boolean isBlank(String str) {
//
//		return StringUtils.isBlank(str);
//	}

//	public static boolean isLetter(String value) {
//
//		CharMatcher matcher = CharMatcher.JAVA_LETTER;
//		return matcher.matchesAllOf(value);
//	}

//	public static boolean isNumeric(String value) {
//
//		CharMatcher matcher = CharMatcher.JAVA_DIGIT;
//		return matcher.matchesAllOf(value);
//	}
}