package fhkoeln.edb.nftool.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import fhkoeln.edb.nftool.service.LocalizedLabel;

//@RooWebScaffold(path = "exercises", formBackingObject = Exercise.class)
@RequestMapping("/addexercise")
@Controller
@SessionAttributes("newExercise")
public class AddExerciseController {

	private static Logger logger = Logger.getLogger(AddExerciseController.class);

	private final static Map<String, String> languages = new HashMap<String, String>(2);

	public AddExerciseController() {
		logger.trace("New AddExerciseController instantiated.");
		languages.put("de", "Deutsch");
		languages.put("en", "Englisch");
	}

	@ModelAttribute("exercises")
	public Collection<Exercise> populateExercises() {
		return Exercise.findAllExercises();
	}

	@ModelAttribute("languages")
	public Map<String, String> populateLanguages() {
		return languages;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		logger.trace("Initializing " + this.toString());

		NewExercise ex = new NewExercise();
		ex.setExercise(new Exercise());
		ex.setLanguage("de");
		model.addAttribute(ex);

		return new ModelAndView("addexercise/start", model);
	}

	@RequestMapping(value = "exercise", method = RequestMethod.POST)
	public ModelAndView exercise(@RequestParam String title,
			@ModelAttribute("languages") String language, @RequestParam String introText,
			ModelMap modelMap, BindingResult result, SessionStatus status) {

		LocalizedLabel exerciseTitle = createLocalizedLabelFromRequest(title, modelMap);
		modelMap.addAttribute("exerciseTitle", exerciseTitle);

		LocalizedLabel intro = createLocalizedLabelFromRequest(introText, modelMap);
		modelMap.addAttribute("introText", intro);

		modelMap.addAttribute("nextStep", "persist");

		return persist(modelMap);
		// return "addexercise/tasks";
	}

	@RequestMapping(value = "taskPk", method = RequestMethod.POST)
	public String taskPk(@RequestParam String name, ModelMap modelMap) {
		createTask("taskPk", name);
		return "";
	}

	@RequestMapping(value = "taskNf1", method = RequestMethod.POST)
	public String taskNf1(ModelMap modelMap) {
		return "";
	}

	@RequestMapping(value = "taskNf2", method = RequestMethod.POST)
	public String taskNf2(ModelMap modelMap) {
		return "";
	}

	@RequestMapping(value = "taskNf3", method = RequestMethod.POST)
	public String taskNf3(ModelMap modelMap) {
		return "";
	}

	/**
	 * @todo Handle Entity-Already-Persisted-Exception!
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "persist", method = RequestMethod.POST)
	@Transactional
	public ModelAndView persist(ModelMap modelMap) {
		logger.trace("Entering persisting state.");

		Exercise exercise = (Exercise) modelMap.get("exercise");
		exercise.persist();

		LocalizedLabel exTitle = (LocalizedLabel) modelMap.get("exerciseTitle");
		persistLabelForEntity(exercise, exTitle);

		return new ModelAndView("persisted", modelMap);
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

	private void persistLabelForEntity(ExerciseEntity entity, LocalizedLabel label) {
		Assert.notNull(entity.getId(), "Entity has to be persisted before!");
		String name = entity.getClass().getSimpleName();
		label.setEntityUri(name + ":" + entity.getId());
		label.persist();
	}

	private LocalizedLabel createLocalizedLabelFromRequest(String text, ModelMap modelMap) {
		LocalizedLabel label = new LocalizedLabel();
		label.setLocale(new Locale((String) modelMap.get("language")));
		label.setContent(text);
		return label;
	}
}
