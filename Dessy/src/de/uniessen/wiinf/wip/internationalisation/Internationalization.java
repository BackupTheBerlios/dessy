package de.uniessen.wiinf.wip.internationalisation;


import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
	public static final String englishLanguage = "en";
	public static final String englishCountry = "US";
	public static String[] english = { englishLanguage, englishCountry };
	public static final String germanLanguage = "de";
	public static final String germanCountry = "DE";
	public static String[] german = { germanLanguage, germanCountry };
	public static final String frenchLanguage = "fr";
	public static final String frenchCountry = "FR";
	public static String[] french = { frenchLanguage, frenchCountry };

	static String[][] languages = { english, german, french };

	static Locale currentLocale = new Locale(englishLanguage, englishCountry);
	static ResourceBundle messages =
		ResourceBundle.getBundle(
			"de.uniessen.wiinf.wip.internationalization.messageBundles.MessagesBundle",
			currentLocale);
	static String language = englishLanguage;

	public static String[] getCountry(String language) {
		String[] ret = null;
		for (int i = 0; i < languages.length; i++) {
			ret = languages[i];
			if (ret[0].equals(language))
				return ret;
		}
		return ret;
	}

	public static void setGermanInstance() {
		currentLocale = new Locale(germanLanguage, germanCountry);
		setMessages();
		language = germanLanguage;
		//setGermanInstance();

	}

	public static void setEnglishInstance() {
		currentLocale = new Locale(englishLanguage, englishCountry);
		setMessages();
		language = englishLanguage;
		//setEnglishInstance();
		
	
	}
	private Internationalization(String lang, String country) {
		currentLocale = new Locale(language, country);
		setMessages();
		language = lang;
	}

	private Internationalization(String lang) {
		String country = (getCountry(language))[1];
		currentLocale = new Locale(language, country);
		setMessages();
		language = lang;
	}

	static void setMessages() {
		messages =
			ResourceBundle.getBundle(
				"de.uniessen.wiinf.wip.internationalization.messageBundles.MessagesBundle",
				currentLocale);


	}
	public static String getLanguage() {
		return language;
	}

	public static String getMessage(String message) {
		String ret = message;
		try {
			message = messages.getString(message);
		} catch (Exception e) {
			System.out.println("Int_Dis:: Missing Ressource: " + message);
		}
		return message;
	}

}