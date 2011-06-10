package fhkoeln.edb.nftool.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> sja/master
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
<<<<<<< HEAD
	public void validateNormalForm2(AnswerTables answer, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validating answer '%s' in context %s.", answer, context));
=======
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public void validateNormalForm2(AnswerTables answer, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (logger.isInfoEnabled()) {
			logger.info(String.format("Validating answer '%s' in context %s.", answer, context));
>>>>>>> sja/master
		}

		final ExerciseState nextState = ExerciseState
				.next(ExerciseState.valueOf(answer.getState()));
		final Set<TaskTable> tables = answer.getExercise().getTaskByState(nextState)
				.getTaskTables();
		final Map<Long, List<String>> solutionKeyColumns = new HashMap<Long, List<String>>();
		final Map<Long, List<String>> solutionColumns = new HashMap<Long, List<String>>();

<<<<<<< HEAD
		getSolution(tables, solutionKeyColumns, solutionColumns, answer.getLocale());

		Map<Long, List<String>> answerKeyColumns = answer.getKeys();
		Map<Long, List<String>> answerColumns = answer.getColumns();
=======
		getSolution(tables, solutionKeyColumns, solutionColumns, answer.getLocale().getLanguage());

		Map<Long, List<String>> answerKeyColumns = getAnswerColumnNames(answer.getKeys(),
				answer.getLocale());
		Map<Long, List<String>> answerColumns = getAnswerColumnNames(answer.getColumns(),
				answer.getLocale());
>>>>>>> sja/master

		final boolean matched = matchTables(messages, solutionKeyColumns, solutionColumns,
				answerKeyColumns, answerColumns);
		if (!matched) {
<<<<<<< HEAD
			answer.setPoints(answer.getPoints() - 1);
		} else if (logger.isDebugEnabled()) {
			logger.debug("The Answer was correct.");
=======
			logger.info("Result: The Answer was NOT correct.");
			answer.setPoints(answer.getPoints() - 1);
		} else {
			logger.info("Result: The Answer was correct.");
		}
	}

	private Map<Long, List<String>> getAnswerColumnNames(Map<Long, List<Long>> answer, Locale locale) {
		Map<Long, List<String>> result = new HashMap<Long, List<String>>();
		for (Entry<Long, List<Long>> answerPart : answer.entrySet()) {
			List<Long> colIds = answerPart.getValue();
			if (colIds == null) {
				continue;
			}
			List<String> names = new ArrayList<String>(answerPart.getValue().size());
			List<Long> colIdsConverted = null;
			// Workaround for Spring bug: Long is in real a String
			try {
				final Long x = colIds.get(0);
			} catch (ClassCastException e) {
				colIdsConverted = new ArrayList<Long>(colIds.size());
				for (Object idObj : colIds) {
					Long id = Long.parseLong((String) idObj);
					colIdsConverted.add(id);
				}
			}
			if (colIdsConverted != null) {
				colIds = colIdsConverted;
			}

			for (Long id : colIds) {
				TableColumn tc = TableColumn.findTableColumn(id);
				names.add(i18nService.getText(tc, "name", locale));
			}
			result.put(answerPart.getKey(), names);
		}
		return result;
	}

	/**
	 * @param answer
	 * @param context
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
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

		Map<Long, List<String>> answerKeyColumns = getAnswerColumnNames(answer.getKeys(),
				answer.getLocale());
		Map<Long, List<String>> answerColumns = getAnswerColumnNames(answer.getColumns(),
				answer.getLocale());

		final boolean matched = matchTables(messages, solutionKeyColumns, solutionColumns,
				answerKeyColumns, answerColumns);
		if (!matched) {
			logger.info("Result: The Answer was NOT correct.");
			answer.setPoints(answer.getPoints() - 1);
		} else {
			logger.info("The Answer was correct.");
>>>>>>> sja/master
		}
	}

	/**
	 * @param tables
	 * @param solutionKeyColumns
	 * @param solutionColumns
	 */
	private void getSolution(final Set<TaskTable> tables,
			final Map<Long, List<String>> solutionKeyColumns,
			final Map<Long, List<String>> solutionColumns, String locale) {
<<<<<<< HEAD
		if (logger.isTraceEnabled()) {
			logger.trace("Building up solution structure for locale " + locale);
=======
		if (logger.isInfoEnabled()) {
			logger.info("Building up solution structure for locale " + locale);
>>>>>>> sja/master
		}
		Assert.notNull(locale, "The locale must be set!");
		List<String> columnKeyStrings, columnStrings;
		for (TaskTable table : tables) {
			columnKeyStrings = new ArrayList<String>();
			columnStrings = new ArrayList<String>();
			for (TableColumn column : table.getTableColumns()) {
<<<<<<< HEAD
				String columnName = i18nService.getText(column, "name", Locale.GERMAN);// column.getTexts().get(locale).getName();
=======
				String columnName = i18nService.getText(column, "name", locale);
>>>>>>> sja/master
				if (column.getKeyColumn()) {
					columnKeyStrings.add(columnName);
				} else {
					columnStrings.add(columnName);
				}
			}
			solutionKeyColumns.put(table.getId(), columnKeyStrings);
			solutionColumns.put(table.getId(), columnStrings);
<<<<<<< HEAD
			if (logger.isDebugEnabled()) {
				logger.debug(String.format(
=======
			if (logger.isInfoEnabled()) {
				logger.info(String.format(
>>>>>>> sja/master
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

<<<<<<< HEAD
		logger.debug("Build mapping answered relation-ids to solution relation-ids");
=======
		logger.info("Build mapping 'answered relation-ids' to 'solution relation-ids'");
>>>>>>> sja/master
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
<<<<<<< HEAD
						if (logger.isDebugEnabled()) {
							logger.debug(String.format(
=======
						if (logger.isInfoEnabled()) {
							logger.info(String.format(
>>>>>>> sja/master
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
<<<<<<< HEAD
				if (!solutionRel.equals(answerRel)) {
					final String err = "Answer was not correct. The columns differ to the solution columns.";
					logger.debug(err);
					messages.addMessage(new MessageBuilder().error()
							.source("" + mapping.getValue()).code("edb.exercise.tables.error")
							.defaultText(err).build());
=======
				if (!(solutionRel.containsAll(answerRel) && answerRel.containsAll(solutionRel) && solutionRel
						.size() == answerRel.size())) {
					final String err = "Answer was not correct. The columns differ to the solution columns.";
					logger.info(err);
					if (logger.isDebugEnabled()) {
						logger.debug("Testet AnswerColumns=" + answerRel + " SolutionColumns="
								+ solutionRel);
					}
					messages.addMessage(new MessageBuilder().error()
							.source("" + mapping.getValue()).code("edb.exercise.tables.error")
							.defaultText(err).build());

>>>>>>> sja/master
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

<<<<<<< HEAD
	/**
	 * @param answer
	 * @param context
	 */
	public void validateNormalForm3(AnswerTables answer, ValidationContext context) {
		MessageContext messages = context.getMessageContext();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validating answer '%s' in context %s.", answer, context));
		}

		final ExerciseState nextState = ExerciseState
				.next(ExerciseState.valueOf(answer.getState()));
		final Set<TaskTable> tables = answer.getExercise().getTaskByState(nextState)
				.getTaskTables();
		final Map<Long, List<String>> solutionKeyColumns = new HashMap<Long, List<String>>();
		final Map<Long, List<String>> solutionColumns = new HashMap<Long, List<String>>();

		getSolution(tables, solutionKeyColumns, solutionColumns, answer.getLocale());

		Map<Long, List<String>> answerKeyColumns = answer.getKeys();
		Map<Long, List<String>> answerColumns = answer.getColumns();

		final boolean matched = matchTables(messages, solutionKeyColumns, solutionColumns,
				answerKeyColumns, answerColumns);
		if (!matched) {
			answer.setPoints(answer.getPoints() - 1);
		} else if (logger.isDebugEnabled()) {
			logger.debug("The Answer was correct.");
		}
	}
=======
>>>>>>> sja/master
}
