package fhkoeln.edb.nftool.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

//@Service
@Service("internationalizationService")
@Component
@Scope(value = "singleton")
@RooSerializable
public class InternationalizationService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InternationalizationService.class);

	private static final Locale FALLBACK_LOCALE = Locale.ENGLISH;

	private static Map<String, List<LocalizedLabel>> labels;

	/**
	 * On construction we load all localized labels from LocalizedLabel and
	 * chache it static in this instance. This is why this service is a
	 * singleton, see getInstance(). Why we do this? Because getText is called
	 * while another transaction is active. getText has been annotated, that a
	 * new Transcation is neccessary, but some Persistence Providers can't
	 * handle this. Ugly, but how to do this better?
	 * 
	 */
	private InternationalizationService() {

	}

	@PostConstruct
	public void initialize() {
		if (labels == null) {
			List<LocalizedLabel> labelsList = LocalizedLabel.findAllLocalizedLabels();
			System.out.println("Labels: " + labelsList);
			labels = new HashMap<String, List<LocalizedLabel>>();

			for (LocalizedLabel label : labelsList) {
				String key = label.getEntityName();
				List<LocalizedLabel> entityLabels = labels.get(key);
				if (entityLabels == null) {
					entityLabels = new ArrayList<LocalizedLabel>();
					labels.put(key, entityLabels);
				}
				entityLabels.add(label);
			}
		}
		System.out.println("Dobe");
	}

	/**
	 * Based on a Table with unique key of "entityName" and "id"
	 * 
	 * @param entityName Name of the Entity to look translation for, e.g. "Exercise"
	 * @param id The Field-ID, unique for this entity.
	 * @param obj An locale
	 * @return The translation or it's fallback.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getText(String entityName, Long id, Object localeObj) {
		Locale locale = FALLBACK_LOCALE;
		if (!(localeObj instanceof Locale)) {
			if (!(localeObj instanceof String))
				throw new IllegalArgumentException("No Locale given!");
			else {
				locale = new Locale((String) localeObj);
			}
		} else {
			locale = (Locale) localeObj;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Locale is detected as %s.", locale));
		}

		if (labels == null)
			throw new IllegalArgumentException("No Locales loaded in constructor!");

		logger.info("id: " + id);
		logger.info("The Service was asked about an i18n for " + entityName);

		/*
		 * LocalizedLabel label = LocalizedLabel
		 * .findLocalizedLabelsByEntityNameAndAttributeIdAndLocale(entityName,
		 * id, locale) .getSingleResult(); if (label == null) { label =
		 * LocalizedLabel
		 * .findLocalizedLabelsByEntityNameAndAttributeIdAndLocale( entityName,
		 * id, FALLBACK_LOCALE).getSingleResult(); } return label.getValue();
		 */

		String result = null, fallback_result = "TRANSLATION NOT FOUND";

		List<LocalizedLabel> entityLabels = labels.get(entityName);
		for (LocalizedLabel localizedLabel : entityLabels) {
			if (localizedLabel.getAttributeName().equals(id)) {
				if (localizedLabel.getLocale().equals(locale)) {
					result = localizedLabel.getContent();
					break;
				} else if (localizedLabel.getLocale().equals(FALLBACK_LOCALE)) {
					fallback_result = localizedLabel.getContent();
				}
			}
		}
		return result == null ? fallback_result : result;
	}

	public Map<String, String> getAllTexts(String entityName, Object localeObj) {
		Assert.isInstanceOf(Locale.class, localeObj, "That was not a locale object!!");
		return getAllTexts(entityName, (Locale) localeObj);
	}

	/**
	 * Special here: Check Language instead of Locale equality.
	 * 
	 * @param entityName
	 * @param locale
	 * @return
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

		// return labels.get(entityName);
	}
}
