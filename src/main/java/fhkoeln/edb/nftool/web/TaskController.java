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

import fhkoeln.edb.nftool.Task;

@RooWebScaffold(path = "tasks", formBackingObject = Task.class)
@RequestMapping("/tasks")
@Controller
public class TaskController extends AbstractLocalizedController<Task> {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Task task, BindingResult bindingResult, Model uiModel, Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("task", task);
			return "tasks/create";
		}
		uiModel.asMap().clear();
		task.persist();
		persistEntityLocalizations(task, locale);
		return "redirect:/tasks/" + task.getId().toString();
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Task task = localizeEntity(Task.findTask(id), locale);
		uiModel.addAttribute("task", task);
		return "tasks/update";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Task task, BindingResult bindingResult, Model uiModel, Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("task", task);
			return "tasks/update";
		}
		uiModel.asMap().clear();
		task.merge();
		updateEntityLocalizations(task, locale);
		return "redirect:/tasks/" + task.getId().toString();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel,
			Locale locale) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute(
					"tasks",
					localizeEntities(Task.findTaskEntries(page == null ? 0 : (page.intValue() - 1)
							* sizeNo, sizeNo), locale));
			float nrOfPages = (float) Task.countTasks() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("tasks", localizeEntities(Task.findAllTasks(), locale));
		}
		return "tasks/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		Task task = localizeEntity(Task.findTask(id), locale);
		uiModel.addAttribute("task", task);
		uiModel.addAttribute("itemId", id);
		return "tasks/show";
	}

}
