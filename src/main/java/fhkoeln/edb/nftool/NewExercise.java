package fhkoeln.edb.nftool;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class NewExercise {
	private static final Logger logger = Logger.getLogger(NewExercise.class);
	private Locale locale;

	private Exercise exercise;

	private List<String> columnNames;
	private String title;

	// private Task introTask;

	// private String primaryKeyText;
	// private Task primaryKey;
	private AnswerColumns tableColumnsModel;

	// private String normalForm1Text;
	// private Task normalForm1;
	// private AnswerColumns normalForm1Columns;

	// private String normalForm2Text;
	// private Task normalForm2;
	private AnswerTables tableModel;

	// private String normalForm3Text;
	// private Task normalForm3;
	// private AnswerTables normalForm3Tables;

	// private String solvedText;
	// private Task solved;
	// private AnswerTables solvedTables;

	public void inititSampleData() {
		Task introTask = new Task();
		introTask.setOrdering((short) 0);
		introTask.setExercise(exercise);
		introTask.setState(ExerciseState.INTRO);

		TaskTable taskTable = new TaskTable();
		taskTable.setNormalform((short) 0);
		taskTable.setDescription("Aufgabentabelle");

		TableRow tableRow = new TableRow();
		tableRow.setContent("content");
		tableRow.setRowNumber(0);
		Set<TableRow> tableRows = new HashSet<TableRow>(1);
		tableRows.add(tableRow);

		TableColumn tableColumn = new TableColumn();
		tableColumn.setName("Spalte 1");
		tableColumn.setKeyColumn(false);
		tableColumn.setOrdering(0);
		tableColumn.setTableRows(tableRows);
		Set<TableColumn> tableColumns = new HashSet<TableColumn>(1);
		tableColumns.add(tableColumn);

		taskTable.setTableRows(tableRows);
		taskTable.setTableColumns(tableColumns);

		Set<TaskTable> taskTables = new HashSet<TaskTable>(1);
		taskTables.add(taskTable);
		introTask.setTaskTables(taskTables);

		Set<Task> tasks = new HashSet<Task>(1);
		tasks.add(introTask);
		exercise = new Exercise();
		exercise.setTasks(tasks);
	}

	/*
	 * public Set<TaskTable> getTaskTablesByState(String stateName) {
	 * for (Task task : exercise.getTasks()) {
	 * if (task.getState().equals(ExerciseState.valueOf(stateName)))
	 * return task.getTaskTables();
	 * }
	 * logger.error("No Such state or no such task.");
	 * return Collections.emptySet();
	 * }
	 * 
	 * public Task getTaskByState(String stateName) {
	 * for (Task task : exercise.getTasks()) {
	 * if (task.getState().equals(ExerciseState.valueOf(stateName)))
	 * return task;
	 * }
	 * logger.error("No Such state or no such task.");
	 * return null;
	 * }
	 */
}
