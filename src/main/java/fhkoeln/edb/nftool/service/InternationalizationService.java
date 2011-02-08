package fhkoeln.edb.nftool.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import fhkoeln.edb.nftool.ExerciseEntity;

@SuppressWarnings("serial")
@Service("internationalizationService")
@Scope(value = "singleton")
@RooSerializable
public class InternationalizationService implements Serializable {
	private static final Logger logger = Logger.getLogger(InternationalizationService.class);

	private static final Locale FALLBACK_LOCALE = Locale.ENGLISH;
	private static final String LOCALE_ERROR = "TRANSLATION NOT FOUND";

	/**
	 * These labels are filled in initialize() after construction. The key of the map is the entity
	 * name like "Exercise", the value is a List of i18ns for this Entity. Each of them contains an
	 * attributeName, a locale and content.
	 */
	private static Map<String, List<LocalizedLabel>> labels;

	@PostConstruct
	public void initialize() {
		if (labels == null || labels.isEmpty()) {
			List<LocalizedLabel> labelsList = LocalizedLabel.findAllLocalizedLabels();
			labels = new HashMap<String, List<LocalizedLabel>>(labelsList.size() / 2);

			List<LocalizedLabel> entityLabels;
			for (LocalizedLabel label : labelsList) {
				String uri = label.getEntityUri();
				if (labels.containsKey(uri)) {
					entityLabels = labels.get(uri);
				} else {
					entityLabels = new ArrayList<LocalizedLabel>();
					labels.put(uri, entityLabels);
				}
				entityLabels.add(label);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Generated Labels Map: " + labels);
		}
	}

	/**
	 * Based on a Table with unique key of "entityName" and "id"
	 * 
	 * @param entityUri Name of the Entity to look translation for, e.g. "Exercise"
	 * @param attributeName The Field-ID, unique for this entity.
	 * @param obj An locale
	 * @return The translation or it's fallback.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getText(String entityUri, String attributeName, Object localeObj) {
		if (entityUri == null) {
			logger.warn("You queried for Translation with no Entity Identifier");
			return LOCALE_ERROR;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format(
					"The Service was asked about an i18n for %s and id %s. Target locale: %s",
					entityUri, attributeName, localeObj));
		}
		if (labels == null || labels.isEmpty()) {
			logger.error("No locales available in i18nService!");
			return LOCALE_ERROR;
		}

		Assert.isInstanceOf(Locale.class, localeObj, "That was not a locale object!!");
		Locale locale = (Locale) localeObj;

		String result = null, fallback_result = LOCALE_ERROR;

		if (labels.containsKey(entityUri)) {
			List<LocalizedLabel> entityLabels = labels.get(entityUri);
			for (LocalizedLabel localizedLabel : entityLabels) {
				if (localizedLabel.getAttributeName().equals(attributeName)) {
					if (localizedLabel.getLocale().equals(locale)) {
						result = localizedLabel.getContent();
						break;
					} else if (localizedLabel.getLocale().equals(FALLBACK_LOCALE)) {
						fallback_result = localizedLabel.getContent();
					}
				}
			}
			return result == null ? fallback_result : result;
		} else {
			logger.warn("Locales did not contain a Translation for the identifier " + entityUri);
		}
		return LOCALE_ERROR;
	}

	/**
	 * Helper for SpeEL-Expressions, which give us an Object instead of a Locale.
	 * Throws IllegalArgumentException if localeObj was not a Locale.
	 * 
	 * @see getAllTexts()
	 * @param entityName Name of the Entity, e.g.
	 * @param localeObj Locale Object
	 */
	public Map<String, String> getAllTexts(String entityName, Object localeObj) {
		Assert.isInstanceOf(Locale.class, localeObj, "That was not a locale object!!");
		return getAllTexts(entityName, (Locale) localeObj);
	}

	/**
	 * Special here: Check Language instead of Locale equality.
	 * 
	 * @param entityName
	 * @param locale
	 * @return All translations for this Entity and the specified locale as Map<Attribute Name,
	 *         Content>.
	 */
	public Map<String, String> getAllTexts(String entityName, Locale locale) {
		Map<String, String> result = new HashMap<String, String>();
		Assert.notNull(locale, "No locale defined!");
		for (LocalizedLabel label : labels.get(entityName)) {
			if (locale.getLanguage().equals(label.getLocale().getLanguage())) {
				result.put(label.getAttributeName(), label.getContent());
			}
		}
		return result;
	}

	public String getText(Object entityObj, String attributeName, Object localeObj) {
		Assert.isInstanceOf(ExerciseEntity.class, entityObj,
				"You did not gave me an ExerciseEntity to resolve!");
		return getText(createUri((ExerciseEntity) entityObj), attributeName, localeObj);
	}

	public static String createUri(ExerciseEntity... entities) {
		StringBuilder sb = new StringBuilder();
		Assert.notNull(entities);
		for (ExerciseEntity e : entities) {
			if (sb.capacity() > 0) {
				sb.append('/');
			}
			sb.append(e.getClass().getSimpleName()).append(':').append(e.getId());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Created i18n-URI " + sb.toString());
		}
		return sb.toString();
	}
}
