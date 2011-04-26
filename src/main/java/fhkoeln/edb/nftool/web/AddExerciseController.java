package fhkoeln.edb.nftool.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.ExerciseEntity;
import fhkoeln.edb.nftool.ExerciseState;
import fhkoeln.edb.nftool.NewExercise;
import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.TableRow;
import fhkoeln.edb.nftool.Task;
import fhkoeln.edb.nftool.TaskTable;
import fhkoeln.edb.nftool.TaskTableDataOnDemand;
import fhkoeln.edb.nftool.service.InternationalizationService;
import fhkoeln.edb.nftool.service.LocalizedLabel;

@RequestMapping("/addexercise")
@Controller
@SessionAttributes("newExercise")
public class AddExerciseController {

	private static final String appname = "addexercise/";

	@Autowired
	private InternationalizationService i18nService;

	private static Logger logger = Logger.getLogger(AddExerciseController.class);

	public AddExerciseController() {
		logger.trace("New AddExerciseController instantiated.");
	}

	@ModelAttribute("exercises")
	public Collection<Exercise> populateExercises() {
		return Exercise.findAllExercises();
	}

	@ModelAttribute("locales")
	public Collection<Locale> populateLocales() {
		return LocalizedLabel.getLocales();
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		logger.trace("Initializing " + this.toString());

		NewExercise ex = new NewExercise();
		ex.setLocale(new Locale("de"));
		ex.inititSampleData();
		model.addAttribute(ex);
		model.addAttribute("i18nService", i18nService);

		// model.addAttribute("taskTables", ex.getTaskTablesByState("INTRO"));

		return new ModelAndView(appname + "start", model);
	}

	@RequestMapping(value = "exercise", method = RequestMethod.POST)
	public String exercise(@RequestParam List<String> columnNames, @ModelAttribute NewExercise ex,
			BindingResult result, SessionStatus status) {
		logger.trace("Handling exercise.");

		Set<TaskTable> taskTables = ex.getExercise().getTaskByState(ExerciseState.INTRO)
				.getTaskTables(); // NPE!
		Set<TableColumn> tableColumns = new HashSet<TableColumn>(columnNames.size());
		Set<TableRow> tableRows = new HashSet<TableRow>();
		int i = 1;
		for (String name : columnNames) {
			TableRow tr = new TableRow();
			tr.setRowNumber(1);
			tr.setContent("Content");

			TableColumn tc = new TableColumn();
			tc.setKeyColumn(false);
			tc.setName(name);
			tc.setOrdering(i++);

			tableRows.add(tr);
			Set<TableRow> tableRowsForColumn = new HashSet<TableRow>(1);
			tableRowsForColumn.add(tr);
			tc.setTableRows(tableRowsForColumn);

			tableColumns.add(tc);
		}
		TaskTable table = taskTables.iterator().next();
		table.setTableColumns(tableColumns);
		table.setTableRows(tableRows);

		return appname + ((result.hasErrors()) ? "exercise" : "taskTable");
		/*
		 * if (result.hasErrors())
		 * return "addexercise/add";
		 * return "addexercise/start";
		 */
	}

	@RequestMapping(value = "introTable", method = RequestMethod.POST)
	public String introTable(@RequestBody List<Map<Integer, String>> tableData,
			@ModelAttribute NewExercise ex, BindingResult result, SessionStatus status) {

		return appname + "taskTable";
	}

	@RequestMapping(value = "taskTable", method = RequestMethod.POST)
	public String taskTable(@ModelAttribute NewExercise ex) {
		TaskTable table = new TaskTable();

		return "";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String taskPk(@RequestParam String name, ModelMap modelMap) {
		createTask("taskPk", name);
		return "";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String taskNf1(ModelMap modelMap) {
		return "";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String taskNf2(ModelMap modelMap) {
		return "";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String taskNf3(ModelMap modelMap) {
		return "";
	}

	/**
	 * @param status
	 * @param result
	 * @todo Handle Entity-Already-Persisted-Exception!
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public String persist(NewExercise ex, BindingResult result, SessionStatus status) {
		logger.trace("Entering persisting state.");

		ex.getExercise().persist();
		/*
		 * persistLabelForExerciseEntity(ex.getExercise(),
		 * new LocalizedLabel(ex.getTitle(), ex.getLocale()));
		 */

		status.isComplete();

		return "persisted";
	}

	private Map<String, Object> createTask(String name, String text) {
		LocalizedLabel label = new LocalizedLabel();
		label.setLocale(new Locale("de"));
		label.setContent(text);
		Task t = new Task();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(name + "Label", label);
		result.put(name, t);
		return result;
	}

	private void persistLabelForExerciseEntity(ExerciseEntity entity, LocalizedLabel label) {
		Assert.notNull(entity.getId(), "Entity has to be persisted before!");
		label.setEntityUri(InternationalizationService.createUri(entity));
		label.persist();
	}

}
