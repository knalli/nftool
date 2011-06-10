package fhkoeln.edb.nftool.web;

import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.RooConversionService;

import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.TableRow;
import fhkoeln.edb.nftool.Task;
import fhkoeln.edb.nftool.TaskTable;
import fhkoeln.edb.nftool.service.InternationalizationService;

/**
 * A central place to register application Converters and Formatters.
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Autowired
	InternationalizationService i18nService;

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		// registry.addConverter(LocalizedLabel.getLocalizedLabelConverter());
		registry.addConverter(getTaskTableConverter());
		registry.addConverter(getExerciseConverter());
		// registry.addConverter(getTaskConverter());
		registry.addConverter(getSetTableRowConverter());
		super.installFormatters(registry);
		// Register application converters and formatters

	}

	Converter<TaskTable, String> getTaskTableConverter() {
		return new org.springframework.core.convert.converter.Converter<TaskTable, String>() {
			@Override
			public String convert(TaskTable taskTable) {
				return new StringBuilder().append(
						i18nService.getText(taskTable, "description", new Locale("de"))).toString();
			}
		};
	}

	Converter<Exercise, String> getExerciseConverter() {
		return new org.springframework.core.convert.converter.Converter<Exercise, String>() {
			@Override
			public String convert(Exercise exercise) {
				InternationalizationService i18nService = new InternationalizationService();
				return new StringBuilder().append(
						i18nService.getText(exercise, "title", new Locale("de"))).toString();
			}

		};
	}

	Converter<Task, String> getTaskConverter() {
		return new org.springframework.core.convert.converter.Converter<Task, String>() {
			@Override
			public String convert(Task task) {
				// InternationalizationService i18nService = new InternationalizationService();
				return new StringBuilder().append(task.getState().name()).toString();
			}

		};
	}

	Converter<Set<TableRow>, String> getSetTableRowConverter() {
		return new org.springframework.core.convert.converter.Converter<Set<TableRow>, String>() {
			@Override
			public String convert(Set<TableRow> tableRows) {

				return new StringBuilder().append(tableRows.size()).append(" Table Rows")
						.toString();
			}

		};
	}
}
