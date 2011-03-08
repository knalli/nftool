package fhkoeln.edb.nftool.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fhkoeln.edb.nftool.AnswerTables;
import fhkoeln.edb.nftool.ExerciseState;
import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.TaskTable;
import fhkoeln.edb.nftool.service.InternationalizationService;

@Component
public class AnswerTablesValidator {
	Logger logger = Logger.getLogger(AnswerTablesValidator.class);
	@Autowired
	InternationalizationService i18nService;

	/**
	 * @param answer
	 * @param context
	 */
	public void validateNormalForm2(AnswerTables answer, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (logger.isInfoEnabled()) {
			logger.info(String.format("Validating answer '%s' in context %s.", answer, context));
		}

		final ExerciseState nextState = ExerciseState
				.next(ExerciseState.valueOf(answer.getState()));
		final Set<TaskTable> tables = answer.getExercise().getTaskByState(nextState)
				.getTaskTables();
		final Map<Long, List<String>> solutionKeyColumns = new HashMap<Long, List<String>>();
		final Map<Long, List<String>> solutionColumns = new HashMap<Long, List<String>>();

		getSolution(tables, solutionKeyColumns, solutionColumns, answer.getLocale().getLanguage());

		Map<Long, List<String>> answerKeyColumns = answer.getKeys();
		Map<Long, List<String>> answerColumns = answer.getColumns();

		final boolean matched = matchTables(messages, solutionKeyColumns, solutionColumns,
				answerKeyColumns, answerColumns);
		if (!matched) {
			logger.info("Result: The Answer was NOT correct.");
			answer.setPoints(answer.getPoints() - 1);
		} else {
			logger.info("Result: The Answer was correct.");
		}
	}

	/**
	 * @param answer
	 * @param context
	 */
	public void validateNormalForm3(AnswerTables answer, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (logger.isInfoEnabled()) {
			logger.info(String.format("Validating answer '%s' in context %s.", answer, context));
		}

		final ExerciseState nextState = ExerciseState
				.next(ExerciseState.valueOf(answer.getState()));
		final Set<TaskTable> tables = answer.getExercise().getTaskByState(nextState)
				.getTaskTables();
		final Map<Long, List<String>> solutionKeyColumns = new HashMap<Long, List<String>>();
		final Map<Long, List<String>> solutionColumns = new HashMap<Long, List<String>>();

		getSolution(tables, solutionKeyColumns, solutionColumns, answer.getLocale().getLanguage());

		Map<Long, List<String>> answerKeyColumns = answer.getKeys();
		Map<Long, List<String>> answerColumns = answer.getColumns();

		final boolean matched = matchTables(messages, solutionKeyColumns, solutionColumns,
				answerKeyColumns, answerColumns);
		if (!matched) {
			logger.info("Result: The Answer was NOT correct.");
			answer.setPoints(answer.getPoints() - 1);
		} else {
			logger.info("The Answer was correct.");
		}
	}

	private class SolutionColumn {
		String columnName;
		long id;
		boolean keyColumn;
	}

	/**
	 * @param tables
	 * @param solutionKeyColumns
	 * @param solutionColumns
	 */
	private void getSolution(final Set<TaskTable> tables,
			final Map<Long, List<String>> solutionKeyColumns,
			final Map<Long, List<String>> solutionColumns, String locale) {
		if (logger.isInfoEnabled()) {
			logger.info("Building up solution structure for locale " + locale);
		}
		Assert.notNull(locale, "The locale must be set!");
		List<String> columnKeyStrings, columnStrings;
		for (TaskTable table : tables) {
			columnKeyStrings = new ArrayList<String>();
			columnStrings = new ArrayList<String>();
			for (TableColumn column : table.getTableColumns()) {
				String columnName = i18nService.getText(column, "name", locale);
				// column.getTexts().get(locale).getName();
				if (column.getKeyColumn()) {
					columnKeyStrings.add(columnName);
				} else {
					columnStrings.add(columnName);
				}
			}
			solutionKeyColumns.put(table.getId(), columnKeyStrings);
			solutionColumns.put(table.getId(), columnStrings);
			if (logger.isInfoEnabled()) {
				logger.info(String.format(
						"%d. solution relation with key-columns %s and columns %s. ",
						solutionColumns.size(), columnKeyStrings, columnStrings));
			}
		}
	}

	/**
	 * @param messages
	 * @param solutionKeyColumns
	 * @param solutionColumns
	 * @param answerKeyColumns
	 * @param answerColumns
	 */
	private boolean matchTables(MessageContext messages,
			final Map<Long, List<String>> solutionKeyColumns,
			final Map<Long, List<String>> solutionColumns,
			Map<Long, List<String>> answerKeyColumns, Map<Long, List<String>> answerColumns) {

		if (answerKeyColumns.size() < 1 || answerColumns.size() < 1) {
			final String err = "The given answer was empty.";
			logger.debug(err);
			messages.addMessage(new MessageBuilder().error().code("edb.exercise.tables.error")
					.defaultText(err).build());
			return false;
		}

		logger.info("Build mapping answered relation-ids to solution relation-ids");
		final Map<Long, Long> solutionToAnswerRelationMapping = new HashMap<Long, Long>();
		try {
			for (Entry<Long, List<String>> answerKeys : answerKeyColumns.entrySet()) {
				if (answerKeys.getValue() == null) {
					continue;
				}

				for (Entry<Long, List<String>> solutionKeys : solutionKeyColumns.entrySet()) {

					if (solutionKeys.getValue().containsAll(answerKeys.getValue())
							&& answerKeys.getValue().containsAll(solutionKeys.getValue())
							&& !solutionToAnswerRelationMapping.containsKey(solutionKeys.getKey())) {
						// Found relation with matching primary keys in solution
						// relations
						solutionToAnswerRelationMapping.put(solutionKeys.getKey(),
								answerKeys.getKey());
						if (logger.isInfoEnabled()) {
							logger.info(String.format(
									"Maping from solution-id %s (%s) to answer-id %s (%s).",
									solutionKeys.getKey(), solutionKeys.getValue(),
									answerKeys.getKey(), answerKeys.getValue()));
						}
					}
				}
			}
			if (solutionToAnswerRelationMapping.size() != solutionKeyColumns.size()) {
				final String err = "Answer was not correct: Solution and answer differ in number of relations.";
				logger.debug(err);
				messages.addMessage(new MessageBuilder().error()
						.code("edb.exercise.tables.error.relationscount").defaultText(err).build());
				return false;
			}

			// If we reached this, the number of relations and their keys are
			// verified.

			for (Entry<Long, Long> mapping : solutionToAnswerRelationMapping.entrySet()) {
				List<String> solutionRel = solutionColumns.get(mapping.getKey());
				List<String> answerRel = answerColumns.get(mapping.getValue());
				if (!solutionRel.equals(answerRel)) {
					final String err = "Answer was not correct. The columns differ to the solution columns.";
					logger.info(err);
					if (logger.isDebugEnabled()) {
						logger.debug("Testet AnswerColumns=" + answerRel + " SolutionColumns="
								+ solutionRel);
					}
					messages.addMessage(new MessageBuilder().error()
							.source("" + mapping.getValue()).code("edb.exercise.tables.error")
							.defaultText(err).build());
				}
			}
		} catch (ClassCastException e) {
			logger.error("The Answer contained wrong types. Maybe the mvcViewFactoryCreator property <property name='useSpringBeanBinding' value='true'/> is missing. Check your spring config!");
			messages.addMessage(new MessageBuilder().error().code("edb.answer.tables.error")
					.build());
			return false;
		}
		return true;
	}

}
