package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fhkoeln.edb.nftool.helper.StaticHelpers;
import fhkoeln.edb.nftool.service.InternationalizationService;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class AnswerTables implements Serializable {
	private static final Logger logger = Logger.getLogger(AnswerTables.class);
	@Autowired
	private InternationalizationService i18nService;
	Map<Long, List<String>> columns = new HashMap<Long, List<String>>();
	Map<Long, List<String>> keys = new HashMap<Long, List<String>>();
	Exercise exercise;
	String state;
	Set<String> possibleColumns;
	Integer points;
	Boolean solved = false;
	String locale;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AnswerTables:");
		sb.append(columns);
		return sb.toString();
	}

	public void buildPossibleColumns(Object localeObj) {
		if (logger.isDebugEnabled()) {
			logger.debug("Generating possible list of cases ...");
		}
		Assert.notNull(exercise, "Exercise has to be set before generating possible columns.");
		Assert.notNull(state, "State has to be set before generating possible columns.");
		Assert.notNull(locale,
				"The locale was null. Have to know which locale I should build a Column list for.");
		possibleColumns = new HashSet<String>();

		Task t = Task.findTasksByExerciseAndState(exercise, ExerciseState.valueOf(state))
				.getSingleResult();
		Set<TaskTable> tables = t.getTaskTables();
		for (TaskTable table : tables) {
			String columnName;
			for (TableColumn column : table.getTableColumns()) {
				columnName = i18nService.getText(column, "name",
						StaticHelpers.getLocaleObject(localeObj));
				possibleColumns.add(columnName);
				if (logger.isDebugEnabled()) {
					logger.debug("Added " + columnName);
				}
			}
		}
	}
}
