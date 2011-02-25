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
import fhkoeln.edb.nftool.helper.StaticHelpers;

@SuppressWarnings("serial")
@Service("internationalizationService")
@Scope(value = "singleton")
@RooSerializable
public class InternationalizationService implements Serializable {
	private static final Logger logger = Logger.getLogger(InternationalizationService.class);

	private static final String FALLBACK_LOCALE = Locale.ENGLISH.getLanguage();
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
				if (logger.isTraceEnabled()) {
					logger.trace("Added label " + label);
				}
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
			logger.debug(String
					.format("The Service was asked about an i18n for %s and attribute %s. Target locale: %s, using %s",
							entityUri, attributeName, localeObj,
							StaticHelpers.getLocaleObject(localeObj).getLanguage()));
		}
		if (labels == null || labels.isEmpty()) {
			logger.error("No locales available in i18nService!");
			return LOCALE_ERROR;
		}

		Locale locale = StaticHelpers.getLocaleObject(localeObj);

		String result = null, fallback_result = LOCALE_ERROR;

		if (labels.containsKey(entityUri)) {
			List<LocalizedLabel> entityLabels = labels.get(entityUri);
			for (LocalizedLabel localizedLabel : entityLabels) {
				if (localizedLabel.getAttributeName().equals(attributeName)) {
					if (localizedLabel.getLocale().getLanguage().equals(locale.getLanguage())) {
						result = localizedLabel.getContent();
						break;
					} else if (localizedLabel.getLocale().getLanguage().equals(FALLBACK_LOCALE)) {
						fallback_result = localizedLabel.getContent();
					}
				}
			}
			if (result == null && entityLabels.size() == 1) {
				if (logger.isDebugEnabled()) {
					logger.debug("Detected just one translation for this entity, using it as fallback.");
				}
				result = entityLabels.get(0).getContent();
			}
			if (logger.isDebugEnabled()) {
				if (result == null) {
					logger.debug("Returning fallback result: " + fallback_result);
				} else {
					logger.debug("Returnning result: " + result);
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
		return getAllTexts(entityName, StaticHelpers.getLocaleObject(localeObj));
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

	/**
	 * 
	 * @param entityObj Used with createUri.
	 * @param attributeName
	 * @param localeObj
	 * @return Single string of translation.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String getText(Object entityObj, String attributeName, Object localeObj) {
		Assert.isInstanceOf(ExerciseEntity.class, entityObj,
				"You did not gave me an ExerciseEntity to resolve!");
		return getText(createUri((ExerciseEntity) entityObj), attributeName, localeObj);
	}

	/**
	 * Creates an URI of an entity of this project. Example: Task:1 = Exercise:1/Task:1
	 * 
	 * @param entities
	 * @return
	 */
	protected static String createUri(ExerciseEntity... entities) {
		StringBuilder sb = new StringBuilder();
		Assert.notNull(entities);
		for (ExerciseEntity e : entities) {
			if (sb.length() > 0) {
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
