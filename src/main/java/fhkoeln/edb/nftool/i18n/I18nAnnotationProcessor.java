package fhkoeln.edb.nftool.i18n;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class I18nAnnotationProcessor {
	public static void processAnnotations(Object obj) {
		try {
			Class clazz = obj.getClass();
			for (Field m : clazz.getDeclaredFields()) {
				I18nString f = m.getAnnotation(I18nString.class);
				if (f != null) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getI18nValue() {
		return "";
	}
}
