package fhkoeln.edb.nftool.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;

/**
 * Bean to store an translation of an entity field.
 * 
 * @author sja
 * 
 */
@RooJavaBean
@SuppressWarnings("serial")
@RooSerializable
@RooEntity(finders = { "findLocalizedLabelsByEntityUriAndAttributeNameAndLocale" })
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "entityUri", "attributeName", "locale" }))
public class LocalizedLabel implements Serializable {

	/**
	 * Pre-Set content text and locale.
	 * 
	 * @param content
	 */
	public LocalizedLabel(String content, Locale locale) {
		this.content = content;
		this.locale = locale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeName == null) ? 0 : attributeName.hashCode());
		result = prime * result + ((entityUri == null) ? 0 : entityUri.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalizedLabel other = (LocalizedLabel) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		if (entityUri == null) {
			if (other.entityUri != null)
				return false;
		} else if (!entityUri.equals(other.entityUri))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		return true;
	}

	public LocalizedLabel() {
	}

	@Override
	public String toString() {
		return this.content;
	}

	/**
	 * URI to a specific entity: 'Exercise:1/Table:42' Name of the entity like 'Exercise'. Defines
	 * relation of this text to the Entity.
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
	@Size(max = 4000)
	private String content;

	/*
	 * @Transient public static Converter<LocalizedLabel, String> getLocalizedLabelConverter() {
	 * return new Converter<LocalizedLabel, String>() {
	 * 
	 * @Override public String convert(LocalizedLabel label) { String locale =
	 * label.getLocale().getDisplayLanguage(); return new
	 * StringBuilder().append(label.getEntityName()).append(" ")
	 * .append(label.getAttributeName()).append(" ").append(locale).toString(); } }; }
	 */

	public static List<Locale> getLocales() {
		return entityManager().createQuery("select distinct o.locale from LocalizedLabel o",
				Locale.class).getResultList();
	}
}
