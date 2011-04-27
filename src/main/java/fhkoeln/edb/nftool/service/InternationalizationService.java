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

	/**
	 * Build up labels from db on construction a singleton upon starting spring.
	 */
	@PostConstruct
	public void initialize() {
		if (labels == null || labels.isEmpty()) {
			List<LocalizedLabel> labelsList = LocalizedLabel.findAllLocalizedLabels();
			labels = new HashMap<String, List<LocalizedLabel>>(labelsList.size() / 2);

//			List<LocalizedLabel> entityLabels;
			for (LocalizedLabel label : labelsList) {
				
				addLabelToList(label);
				/*String uri = label.getEntityUri();
				if (labels.containsKey(uri)) {
					entityLabels = labels.get(uri);
				} else {
					entityLabels = new ArrayList<LocalizedLabel>();
					labels.put(uri, entityLabels);
				}
				entityLabels.add(label);*/
				if (logger.isTraceEnabled()) {
					logger.trace("Added label " + label);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Generated Labels Map: " + labels);
		}
	}

	public String getText(Object entityObj, String attributeName, Object localeObj) {
		String entityUri = extractExerciseEntityName(entityObj);
		String locale = extractLocaleString(localeObj);
		if (locale.length() == 4) {
			locale = locale.substring(0, 2);
		}

		return getText(entityUri, attributeName, locale);
	}

	/**
	 * Wrapper to handle Objects from webflow.
	 * 
	 * @param entityUri
	 * @param attributeNameObj
	 * @param localeObj
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String getText(Object entityObj, Object attributeNameObj, Object localeObj) {

		Assert.isInstanceOf(String.class, attributeNameObj,
				"The attribute name has to be a string!");

		String entityUri = extractExerciseEntityName(entityObj);
		String locale = extractLocaleString(localeObj);

		return getText(entityUri, (String) attributeNameObj, locale);
	}

	/**
	 * @param localeObj
	 * @return
	 */
	private String extractLocaleString(Object localeObj) {
		if (localeObj instanceof String)
			return (String) localeObj;
		else if (localeObj instanceof Locale)
			return StaticHelpers.getLocaleObject(localeObj).getLanguage();
		else {
			logger.error("The localeObj was neither a String like 'de', nor a Locale.");
			return null;
		}
	}

	/**
	 * @param entityObj
	 * @return
	 */
	private String extractExerciseEntityName(Object entityObj) {
		if (entityObj instanceof String)
			return (String) entityObj;
		else if (ExerciseEntity.class.isAssignableFrom(entityObj.getClass()))
			return createUri((ExerciseEntity) entityObj);
		else {
			logger.error("The entityObj is not a string, nor an ExerciseEntity.");
			return null;
		}
	}

	/**
	 * Based on a Table with unique key of "entityName" and "id"
	 * 
	 * @param entityUri Name of the Entity to look translation for, e.g. "Exercise"
	 * @param attributeName The Field-ID, unique for this entity.
	 * @param String An locale string like 'en'
	 * @return The translation or it's fallback.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String getText(String entityUri, String attributeName, String locale) {
		if (entityUri == null) {
			logger.warn("You queried for Translation with no Entity Identifier");
			return LOCALE_ERROR;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String
					.format("The Service was asked about an i18n for %s and attribute %s. Target locale: %s",
							entityUri, attributeName, locale));
		}
		if (labels == null || labels.isEmpty()) {
			logger.error("No locales available in i18nService!");
			return LOCALE_ERROR;
		}

		String result = null, fallback_result = LOCALE_ERROR;

		if (labels.containsKey(entityUri)) {
			List<LocalizedLabel> entityLabels = labels.get(entityUri);
			for (LocalizedLabel localizedLabel : entityLabels) {
				if (localizedLabel.getAttributeName().equals(attributeName)) {
					if (localizedLabel.getLocale().getLanguage().equals(locale)) {
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
	// public Map<String, String> getAllTexts(String entityName, Object localeObj) {
	// return getAllTexts(entityName, StaticHelpers.getLocaleObject(localeObj));
	// }

	/**
	 * Special here: Check Language instead of Locale equality.
	 * 
	 * @param entityName
	 * @param locale
	 * @return All translations for this Entity and the specified locale as Map<Attribute Name,
	 *         Content>.
	 */
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	// public Map<String, String> getAllTexts(String entityName, Locale locale) {
	// Map<String, String> result = new HashMap<String, String>();
	// Assert.notNull(locale, "No locale defined!");
	// for (LocalizedLabel label : labels.get(entityName)) {
	// if (locale.getLanguage().equals(label.getLocale().getLanguage())) {
	// result.put(label.getAttributeName(), label.getContent());
	// }
	// }
	// return result;
	// }

	/**
	 * 
	 * @param entityObj Used with createUri.
	 * @param attributeName
	 * @param localeObj
	 * @return Single string of translation.
	 */
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	// public String getText(Object entityObj, String attributeNameObj, Object localeObj) {
	// Assert.isInstanceOf(ExerciseEntity.class, entityObj,
	// "You did not gave me an ExerciseEntity to resolve!");
	// Assert.isInstanceOf(String.class, attributeNameObj,
	// "The attribute name has to be a string!");
	// return getText(createUri((ExerciseEntity) entityObj), attributeNameObj, localeObj);
	// }

	/**
	 * Creates an URI of an entity of this project. Example: Task:1 = Exercise:1/Task:1
	 * 
	 * @param entities
	 * @return
	 */
	public static String createUri(ExerciseEntity... entities) {
		StringBuilder sb = new StringBuilder();
		Assert.notNull(entities);
		for (ExerciseEntity e : entities) {
			if (sb.length() > 0) {
				sb.append('/');
			}
			sb.append(e.getClass().getSimpleName()).append(':').append(e.getId());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Created i18n-URI " + sb.toString());
		}
		return sb.toString();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void setText(ExerciseEntity entity, String attributeName, String text, Locale locale) {
		Locale lang = new Locale(locale.getLanguage());
		LocalizedLabel label = new LocalizedLabel(text, lang);
		label.setEntityUri(createUri(entity));
		label.setAttributeName(attributeName);
		
		addLabelToList(label);
			
		label.persist();
	}

	private void addLabelToList(LocalizedLabel label) {
		List<LocalizedLabel> list;
		String uri = label.getEntityUri();
		if (!labels.containsKey(uri)) {
			list = new ArrayList<LocalizedLabel>(1);
			labels.put(uri, list);
		} else {
			list = labels.get(label.getEntityUri());
		}
		list.add(label);
	}
	
	private void removeLabelFromList(LocalizedLabel label) {
		if (labels.containsKey(label.getEntityUri())) {
			List<LocalizedLabel> list = labels.get(label.getEntityUri());
			list.remove(label);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateText(ExerciseEntity entity, String attributeName, String text, Locale locale) {
		Locale lang = new Locale(locale.getLanguage());
		LocalizedLabel label = LocalizedLabel
				.findLocalizedLabelsByEntityUriAndAttributeNameAndLocale(createUri(entity),
						attributeName, lang).getSingleResult();
		label.setContent(text);
		label.merge();
		removeLabelFromList(label);
		addLabelToList(label);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteText(ExerciseEntity entity, String attributeName, String text, Locale locale) {
		LocalizedLabel label = LocalizedLabel
				.findLocalizedLabelsByEntityUriAndAttributeNameAndLocale(createUri(entity),
						attributeName, locale).getSingleResult();
		label.remove();
		removeLabelFromList(label);
	}

}
