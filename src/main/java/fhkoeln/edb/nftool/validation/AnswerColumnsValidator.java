package fhkoeln.edb.nftool.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;

import fhkoeln.edb.nftool.AnswerColumns;
import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.ExerciseState;
import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.TaskTable;
import fhkoeln.edb.nftool.service.InternationalizationService;

@Component
public class AnswerColumnsValidator {
	Logger logger = Logger.getLogger(AnswerColumnsValidator.class);
	@Autowired
	InternationalizationService i18nService;

	/**
	 * Check if the checked columns in the form are in the next tasks' Tables key columns.
	 * 
	 * @param answer
	 *            The answer given by web form.
	 * @param context
	 */
	public void validatePrimaryKey(AnswerColumns answer, ValidationContext context) {
		logger.trace("Entering validatePrimaryKey");
		checkViewStateByColumnComparison(true, answer, context);
	}

	/**
	 * Check if the checked columns in the form don't appear in the next Task.
	 * 
	 * @param answer
	 * @param context
	 */
	public void validateNormalForm1(AnswerColumns answer, ValidationContext context) {
		logger.trace("Entering validateNormalform1");
		checkViewStateByColumnComparison(false, answer, context);
	}

	/**
	 * @param containing
	 * @param answer
	 * @param context
	 */
	protected void checkViewStateByColumnComparison(boolean containing, AnswerColumns answer,
			ValidationContext context) {
		logger.trace("Entering checkViewStateByColumnComparison");
		if (logger.isInfoEnabled()) {
			logger.info("Validating answer: " + answer);
		}
		List<TableColumn> columns = answer.getColumns();
		MessageContext messages = context.getMessageContext();
		boolean result;

		if (answer.getExercise() == null) {
			logger.error("The current exercise been has not been set. Are we in a Flow?");
			return;
		}
		/*
		 * if (columns.size() < 1) { logger.debug("No column was checked.");
		 * answer.setPoints(answer.getPoints() - 1); messages.addMessage(new
		 * MessageBuilder().error().source("columns")
		 * .code("edb.exercise.columns.emptyanswer").build()); return; }
		 */

		Set<TaskTable> tables = getTaskTablesFormExercise(answer.getExercise(),
				ExerciseState.valueOf(answer.getState()));
		Set<TaskTable> tablesPrevious = getTaskTablesFormExercise(answer.getExercise(),
				ExerciseState.previous(ExerciseState.valueOf(answer.getState())));

		if (containing) {
			result = checkTablesContainsColumnsAll(columns, tables, answer.getLocale()
					.getLanguage(), true);
		} else {
			result = checkTablesContainsColumnsNone(columns, tables, tablesPrevious, answer
					.getLocale().getLanguage(), false);
		}

		if (!result || messages.hasErrorMessages()) {
			answer.setPoints(answer.getPoints() - 1);
			logger.debug("Given answer was incorrect!");
			messages.addMessage(new MessageBuilder().error().source("columns")
					.code("edb.exercise.columns.error").build());
		} else {
			logger.debug("The answer was correct.");
		}

	}

	/**
	 * @param answerColumns
	 * @param taskTables
	 * @param mustBeKey
	 * @return
	 */
	protected boolean checkTablesContainsColumnsAll(List<TableColumn> answerColumns,
			Set<TaskTable> taskTables, String locale, boolean mustBeKey) {
		logger.trace("Entering checkTablesContainsColumnsAll");
		if (logger.isDebugEnabled()) {
			logger.debug("Checking Tables " + taskTables + " for Locale " + locale);
		}
		if (taskTables.size() > 0) {
			logger.info("Solution: The following columns have to be in the anser:");
		} else {
			logger.info("Could not detect solution columns: taskTables are empty.");
		}
		// List<TableColumn> columnsUnchecked = new
		// ArrayList<TableColumn>(answerColumns.size());
		// Collections.copy(columnsUnchecked, answerColumns);

		List<String> answerColumnNames = getLocalizedColumnNames(answerColumns, locale);

		for (TaskTable table : taskTables) {
			Set<TableColumn> solutionColumns = table.getTableColumns();
			if (solutionColumns.size() < 0) {
				logger.info("No columns for this table found:" + table);
			}
			for (TableColumn solutionColumn : solutionColumns) {
				if (!mustBeKey || solutionColumn.getKeyColumn()) {
					String solutionColumnName = i18nService.getText(solutionColumn, "name", locale);
					if (logger.isInfoEnabled()) {
						logger.info("Solution column: " + solutionColumnName + " ("
								+ solutionColumn.toString() + ")");
					}

					if (answerColumnNames.contains(solutionColumnName)) {
						answerColumnNames.remove(answerColumnNames.indexOf(solutionColumnName));
						if (answerColumnNames.size() == 0) {
							continue;
						}
					} else
						// We have in the solution table but the answer did not
						// contain this.
						return false;
				}
			}
		}
		return answerColumnNames.size() == 0;
	}

	/**
	 * @param columns
	 * @param locale
	 * @return
	 */
	private List<String> getLocalizedColumnNames(List<TableColumn> columns, String locale) {
		List<String> answerColumnNames = new ArrayList<String>();
		for (TableColumn c : columns) {
			answerColumnNames.add(i18nService.getText(c, "name", locale));
		}
		return answerColumnNames;
	}

	/**
	 * Check if none of the TableColumns in anserColumns occur in taskTables, which are the
	 * Relation(s) of the next ExerciseState.
	 * 
	 * @param answerColumns
	 *            Tables in Http Answer.
	 * @param taskTables
	 *            Tables of current Task, NF1
	 * @param tablesPrevious
	 *            Tables of previous Task, PRIMARY_KEY
	 * @param locale
	 * @param mustBeKey
	 *            Deprecated.
	 * @return
	 */
	protected boolean checkTablesContainsColumnsNone(List<TableColumn> answerColumns,
			Set<TaskTable> taskTables, Set<TaskTable> tablesPrevious, String locale,
			boolean mustBeKey) {
		logger.trace("Entering checkTablesContainsColumnsNone");

		Set<String> columnsIntersection = new HashSet<String>();
		List<String> intersectComplete = new ArrayList<String>();
		List<String> relPrev = new ArrayList<String>();
		List<String> rel = new ArrayList<String>();

		/*
		 * if (tablesPrevious.size() > 1 || taskTables.size() > 1) {
		 * logger.error("Not implemented for many tables in answer in this state!");
		 * logger.debug("tablesPrevious.size() = " + tablesPrevious.size() +
		 * ", taskTables.size() = " + taskTables.size()); }
		 */
		int columnCounter = 0;
		for (TaskTable tPrev : tablesPrevious) {
			Set<TableColumn> tcsPrev = tPrev.getTableColumns();
			for (TaskTable t : taskTables) {
				Set<TableColumn> tcs = t.getTableColumns();
				for (TableColumn tableColumnPrev : tcsPrev) {
					columnCounter++;
					boolean found = false;
					String tableColumnPrevName = i18nService.getText(tableColumnPrev, "name",
							locale);
					for (TableColumn tableColumn : tcs) {
						String tableColumnName = i18nService.getText(tableColumn, "name", locale);
						if (tableColumnName.equals(tableColumnPrevName)) {
							found = true;
							break;
						}
						rel.add(tableColumnName);
					}
					logger.debug("Table:" + rel.toString());
					rel.clear();
					relPrev.add(tableColumnPrevName);
					if (!found) {
						columnsIntersection.add(tableColumnPrevName);
						intersectComplete.add(tableColumnPrevName);
					} else {
						intersectComplete.add(tableColumnPrevName);
					}
				}
				logger.debug("PrevTable:" + relPrev.toString());
				relPrev.clear();
			}
		}
		if (answerColumns.size() == 0 && intersectComplete.size() == columnCounter)
			// The answer was empty and columnIntersection contains all available columns.
			// Ergo: The correct answer is: empty set!
			return true;
		return getLocalizedColumnNames(answerColumns, locale).containsAll(columnsIntersection);
	}

	/**
	 * Extracts the Tables of an Exercise in the given state. Null checks are performed.
	 * 
	 * @param answer
	 * @return
	 */
	protected Set<TaskTable> getTaskTablesFormExercise(Exercise exercise, ExerciseState state) {
		if (exercise == null)
			throw new IllegalArgumentException(
					"Could not validate. The exercise was null. Did the Flow set it to AnswerColumns?");

		ExerciseState nextState = ExerciseState.next(state);
		if (nextState == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("There is no next state after " + state + ". Returning null.");
			}
			return null;
		}
		return exercise.getTaskByState(nextState).getTaskTables();
	}

}
