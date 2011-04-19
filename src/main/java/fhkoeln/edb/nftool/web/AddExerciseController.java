package fhkoeln.edb.nftool.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import fhkoeln.edb.nftool.NewExercise;
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
		Exercise e = new Exercise();
		Set<Task> tasks = NewExercise.inititSampleData();
		e.setTitle("Neue Aufgabe");
		e.setTasks(tasks);
		ex.setExercise(e);
		ex.setIntroTask(tasks.iterator().next());
		ex.setLocale(new Locale("de"));

		model.addAttribute(ex);

		return new ModelAndView(appname + "start", model);
	}

	@RequestMapping(value = "exercise", method = RequestMethod.POST)
	public String exercise(@ModelAttribute NewExercise ex, BindingResult result,
			SessionStatus status) {
		logger.trace("Handling exercise.");
		logger.debug(appname + ((result.hasErrors()) ? "exercise" : "taskTable"));
		return appname + ((result.hasErrors()) ? "exercise" : "taskTable");
		/*
		 * if (result.hasErrors())
		 * return "addexercise/add";
		 * return "addexercise/start";
		 */
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
