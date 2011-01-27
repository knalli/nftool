package fhkoeln.edb.nftool.service;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Locale;

@RooJavaBean
@RooToString
@RooSerializable
@RooEntity(finders = { "findLocalizedLabelsByEntityNameAndAttributeNameAndLocale" })
public class LocalizedLabel implements Serializable {

	/**
	 * Name of the entity like 'Exercise'. Defines relation of this text to the Entity. @See
	 * attributeId
	 */
	@NotNull
	@Size(max = 20)
	private String entityName;

	/**
	 * Attribute number which identifies this content in the entity.
	 */
	private String attributeName;

	/**
	 * Locale like 'en' or 'de'
	 */
	private Locale locale;

	/**
	 * Text in the setted country language.
	 */
	@NotNull
	private String content;
}
