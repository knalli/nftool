package fhkoeln.edb.nftool.web;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.service.InternationalizationService;

@RooWebScaffold(path = "exercises", formBackingObject = Exercise.class)
@RequestMapping("/exercises")
@Controller
public class ExerciseController {

	@Autowired
	InternationalizationService i18nService;
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Exercise exercise = localizeExercise(Exercise.findExercise(id), locale);
        uiModel.addAttribute("exercise", exercise);
        return "exercises/update";
    }

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Exercise exercise, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("exercise", exercise);
			return "exercises/update";
		}
		uiModel.asMap().clear();
		exercise.merge();
		i18nService.initialize();
		i18nService.updateText(exercise, "title", exercise.getTitle(), locale);
		i18nService.updateText(exercise, "description", exercise.getDescription(), locale);
		return "redirect:/exercises/" + exercise.getId();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Exercise exercise, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("exercise", exercise);
			return "exercises/create";
		}
		uiModel.asMap().clear();
		exercise.persist();
		i18nService.setText(exercise, "title", exercise.getTitle(), locale);
		i18nService.setText(exercise, "description", exercise.getDescription(), locale);
		return "redirect:/exercises/" + exercise.getId();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel,
			Locale locale) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();

			uiModel.addAttribute(
					"exercises",
					localizeExercises(
							Exercise.findExerciseEntries(page == null ? 0 : (page.intValue() - 1)
									* sizeNo, sizeNo), locale));
			float nrOfPages = (float) Exercise.countExercises() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("exercises",
					localizeExercises(Exercise.findAllExercises(), locale));
		}

		return "exercises/list";
	}

	private List<Exercise> localizeExercises(List<Exercise> exercises, Locale locale) {
		for (Exercise exercise : exercises) {
			localizeExercise(exercise, locale);
		}
		return exercises;
	}
	
	private Exercise localizeExercise(Exercise exercise, Locale locale) {
		exercise.setTitle(i18nService.getText(exercise, "title", locale));
		exercise.setDescription(i18nService.getText(exercise, "description", locale));
		return exercise;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Exercise exercise = Exercise.findExercise(id);
		exercise.setTitle(i18nService.getText(exercise, "title", locale));
		exercise.setDescription(i18nService.getText(exercise, "description", locale));
		uiModel.addAttribute("exercise", exercise);
		uiModel.addAttribute("itemId", id);
		return "exercises/show";
	}
}
