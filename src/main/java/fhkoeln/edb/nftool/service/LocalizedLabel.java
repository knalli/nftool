package fhkoeln.edb.nftool.service;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * Bean to store an translation of an entity field.
 * 
 * @author sja
 * 
 */
@RooJavaBean
@RooToString
@SuppressWarnings("serial")
@RooSerializable
@RooEntity(finders = { "findLocalizedLabelsByEntityUriAndAttributeNameAndLocale" })
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "entityUri", "attributeName", "locale" }))
public class LocalizedLabel implements Serializable {

	/**
	 * URI to a specific entity: 'Exercise:1/Table:42'
	 * Name of the entity like 'Exercise'. Defines relation of this text to the Entity.
	 */
	@NotNull
	@Size(min = 1)
	@Index(name = "indexEntityUri")
	private String entityUri;

	/**
	 * Attribute number which identifies this content in the entity.
	 */
	@NotNull
	@Size(min = 3, max = 30)
	private String attributeName;

	/**
	 * Locale like 'en' or 'de'
	 */
	@NotNull
	private Locale locale;

	/**
	 * Text in the currently set country language.
	 */
	@NotNull
	private String content;

	/*
	 * @Transient
	 * public static Converter<LocalizedLabel, String> getLocalizedLabelConverter() {
	 * return new Converter<LocalizedLabel, String>() {
	 * 
	 * @Override
	 * public String convert(LocalizedLabel label) {
	 * String locale = label.getLocale().getDisplayLanguage();
	 * return new StringBuilder().append(label.getEntityName()).append(" ")
	 * .append(label.getAttributeName()).append(" ").append(locale).toString();
	 * }
	 * };
	 * }
	 */
}
