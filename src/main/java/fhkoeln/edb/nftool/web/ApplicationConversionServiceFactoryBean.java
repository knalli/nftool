package fhkoeln.edb.nftool.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.RooConversionService;

import fhkoeln.edb.nftool.TaskTable;

/**
 * A central place to register application Converters and Formatters.
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		// registry.addConverter(LocalizedLabel.getLocalizedLabelConverter());
		registry.addConverter(getTaskTableConverter());
		super.installFormatters(registry);
		// Register application converters and formatters

	}

	Converter<TaskTable, String> getTaskTableConverter() {
		return new org.springframework.core.convert.converter.Converter<TaskTable, String>() {
			@Override
			public String convert(TaskTable taskTable) {
				return new StringBuilder().append("taskTablefromConverter").toString();
			}
		};
	}

}
