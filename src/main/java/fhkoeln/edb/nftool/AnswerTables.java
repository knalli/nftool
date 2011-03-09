package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class AnswerTables implements Serializable {
	private static final Logger logger = Logger.getLogger(AnswerTables.class);

	Map<Long, List<Long>> columns = new HashMap<Long, List<Long>>();
	Map<Long, List<Long>> keys = new HashMap<Long, List<Long>>();
	Exercise exercise;
	String state;
	Set<TableColumn> possibleColumns;
	Integer points;
	Boolean solved = false;
	Locale locale;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AnswerTables: Keys=").append(keys);
		sb.append(" Columns=").append(columns);
		sb.append(" Locale=").append(locale);
		return sb.toString();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public void buildPossibleColumns() {
		logger.debug("Generating possible list of cases ...");
		Assert.notNull(exercise, "Exercise has to be set before generating possible columns.");
		Assert.notNull(state, "State has to be set before generating possible columns.");
		Assert.notNull(locale,
				"The locale was null. Have to know which locale I should build a Column list for.");
		possibleColumns = new HashSet<TableColumn>();

		Task t = Task.findTasksByExerciseAndState(exercise, ExerciseState.valueOf(state))
				.getSingleResult();
		Set<TaskTable> tables = t.getTaskTables();
		for (TaskTable table : tables) {
			for (TableColumn column : table.getTableColumns()) {
				possibleColumns.add(column);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Possible Columns: " + possibleColumns);
			}
		}
	}
}
