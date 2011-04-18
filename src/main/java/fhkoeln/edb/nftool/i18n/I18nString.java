package fhkoeln.edb.nftool.i18n;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @deprecated
 * @author sja
 * 
 */
@Target(ElementType.FIELD)
public @interface I18nString {
	String entityName();
}
