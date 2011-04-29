package fhkoeln.edb.nftool.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fhkoeln.edb.nftool.Exercise;

@RooWebScaffold(path = "exercises", formBackingObject = Exercise.class)
@RequestMapping("/exercises")
@Controller
public class ExerciseController extends AbstractLocalizedController<Exercise> {

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Exercise exercise = localizeEntity(Exercise.findExercise(id), locale);
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
		updateEntityLocalizations(exercise, locale);
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
		persistEntityLocalizations(exercise, locale);
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
					localizeEntities(
							Exercise.findExerciseEntries(page == null ? 0 : (page.intValue() - 1)
									* sizeNo, sizeNo), locale));
			float nrOfPages = (float) Exercise.countExercises() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("exercises", localizeEntities(Exercise.findAllExercises(), locale));
		}

		return "exercises/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Exercise exercise = localizeEntity(Exercise.findExercise(id), locale);
		uiModel.addAttribute("exercise", exercise);
		uiModel.addAttribute("itemId", id);
		return "exercises/show";
	}
}
