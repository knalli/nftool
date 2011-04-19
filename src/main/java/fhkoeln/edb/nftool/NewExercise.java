package fhkoeln.edb.nftool;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.constraints.NotNull;

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
	private Locale locale;

	private Exercise exercise;

	private Task introTask;

	private String primaryKeyText;
	private Task primaryKey;
	private AnswerColumns primaryKeyColumns;

	private String normalForm1Text;
	private Task normalForm1;
	private AnswerColumns normalForm1Columns;

	private String normalForm2Text;
	private Task normalForm2;
	private AnswerTables normalForm2Tables;

	private String normalForm3Text;
	private Task normalForm3;
	private AnswerTables normalForm3Tables;

	private String solvedText;
	private Task solved;
	private AnswerTables solvedTables;

	public static Set<Task> inititSampleData() {

		Task introTask = new Task();
		introTask.setOrdering((short) 0);
		// introTask.setExercise(exercise);
		introTask.setState(ExerciseState.INTRO);

		TaskTable tt = new TaskTable();
		tt.setNormalform((short) 0);
		tt.setDescription("Aufgabentabelle");

		TableRow tr = new TableRow();
		tr.setContent("content");
		tr.setRowNumber(0);
		Set<TableRow> trs = new HashSet<TableRow>(1);
		trs.add(tr);

		TableColumn tc = new TableColumn();
		tc.setName("Spalte 1");
		tc.setKeyColumn(false);
		tc.setOrdering(0);
		tc.setTableRows(trs);
		Set<TableColumn> tcs = new HashSet<TableColumn>(1);

		tt.setTableRows(trs);
		tt.setTableColumns(tcs);

		Set<TaskTable> tts = new HashSet<TaskTable>(1);
		introTask.setTaskTables(tts);

		Set<Task> ts = new HashSet<Task>(1);
		ts.add(introTask);
		return ts;
	}

}
