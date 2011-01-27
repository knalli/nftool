package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class AnswerTables implements Serializable {
	private static final Logger logger = Logger.getLogger(AnswerTables.class);
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

	public void buildPossibleColumns() {
		Assert.notNull(exercise, "Exercise has to be set before generating possible columns.");
		Assert.notNull(state, "State has to be set before generating possible columns.");
		Assert.notNull(locale,
				"The locale was null. Have to know which locale I should build a Column list for.");
		// if (exercise == null || state == null)
		// return;
		possibleColumns = new HashSet<String>();

		Task t = Task.findTasksByExerciseAndState(exercise, ExerciseState.valueOf(state))
				.getSingleResult();
		// Task t = exercise.getTaskByState(state);
		Set<TaskTable> tables = t.getTaskTables();
		for (TaskTable table : tables) {
			for (TableColumn column : table.getTableColumns()) {
				/*
				 * if (!column.getTexts().containsKey(locale)) {
				 * logger.error("The Column '" + column + "' of TaskTable '" + table
				 * + "' has no translation for Locale '" + locale + "'.");
				 * }
				 * possibleColumns.add(column.getTexts().get(locale).getName());
				 */
				possibleColumns.add(column.getName());
			}
		}
	}
}
