package fhkoeln.edb.nftool.web;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import fhkoeln.edb.nftool.ExerciseEntity;
import fhkoeln.edb.nftool.i18n.I18nString;
import fhkoeln.edb.nftool.service.InternationalizationService;

public abstract class AbstractLocalizedController<T extends ExerciseEntity> {
	@Autowired
	private InternationalizationService i18nService;

	Logger logger = Logger.getLogger(AbstractLocalizedController.class);
	protected final static Class<I18nString> i18nClass = I18nString.class;

	public InternationalizationService getI18nService() {
		return i18nService;
	}

	public void setI18nService(InternationalizationService i18nService) {
		this.i18nService = i18nService;
	}

	protected T localizeEntity(T entity, Locale locale) {
		logger.trace("Localizing ExerciseEntity.");
		try {
			for (Field f : entity.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getAnnotation(i18nClass) != null) {
					if (logger.isDebugEnabled()) {
						logger.debug("Injecting localization " + entity.getClass().getSimpleName()
								+ "." + f.getName());
					}
					final String content = i18nService.getText(entity, f.getName(), locale);
					if (content.equals(InternationalizationService.LOCALE_ERROR)) {
						f.set(entity, "");
					} else {
						f.set(entity, content);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Error on filling localizations in entity.", ex);
		}
		return entity;
	}

	protected List<T> localizeEntities(List<T> entities, Locale locale) {
		for (T entity : entities) {
			localizeEntity(entity, locale);
		}
		return entities;
	}

	protected T persistEntityLocalizations(T entity, Locale locale) {
		Assert.isTrue(entity.getId() != null,
				"Could not get entity id. Maybe the entity to save the label for is not persisted!");
		logger.trace("Persisting ExerciseEntities localized fields.");
		try {
			for (Field f : entity.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getAnnotation(i18nClass) != null) {
					f.set(entity, i18nService.getText(entity, f.getName(), locale));
					i18nService.setText(entity, f.getName(), (String) f.get(entity), locale);
				}
			}
		} catch (Exception ex) {
			logger.error("Error on persisting entity localizations.", ex);
		}
		return entity;
	}

	protected T updateEntityLocalizations(T entity, Locale locale) {
		logger.trace("Updating ExerciseEntitys localized fields.");
		try {
			for (Field f : entity.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getAnnotation(i18nClass) != null) {
					String txt = (String) f.get(entity);
					i18nService.updateText(entity, f.getName(), txt, locale);
					if (logger.isDebugEnabled()) {
						logger.debug("Updated " + f.getName() + " of Entity "
								+ entity.getClass().getSimpleName() + " to value '" + f.get(entity)
								+ "'");
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Error on updating exercise localizations.", ex);
		}
		return entity;
	}
}
