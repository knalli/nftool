package fhkoeln.edb.nftool.helper;

import java.util.Locale;

import org.springframework.util.Assert;

public class StaticHelpers {
	/**
	 * @param localeObj
	 * @return
	 */
	public static Locale getLocaleObject(Object localeObj) {
		Assert.isInstanceOf(Locale.class, localeObj, "That was not a locale object!!");
		return (Locale) localeObj;
	}
}
