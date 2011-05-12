package fhkoeln.edb.nftool.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fhkoeln.edb.nftool.TaskTable;

@RooWebScaffold(path = "tasktables", formBackingObject = TaskTable.class)
@RequestMapping("/tasktables")
@Controller
public class TaskTableController extends AbstractLocalizedController<TaskTable> {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid TaskTable taskTable, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("taskTable", taskTable);
			return "tasktables/create";
		}
		uiModel.asMap().clear();
		taskTable.persist();
		persistEntityLocalizations(taskTable, locale);
		return "redirect:/tasktables/" + taskTable.getId().toString();
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TaskTable taskTable = localizeEntity(TaskTable.findTaskTable(id), locale);
		uiModel.addAttribute("taskTable", taskTable);
		return "tasktables/update";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid TaskTable taskTable, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("taskTable", taskTable);
			return "tasktables/update";
		}
		uiModel.asMap().clear();
		taskTable.merge();
		persistEntityLocalizations(taskTable, locale);
		return "redirect:/tasktables/" + taskTable.getId().toString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TaskTable taskTable = localizeEntity(TaskTable.findTaskTable(id), locale);
		uiModel.addAttribute("tasktable", taskTable);
		uiModel.addAttribute("itemId", id);
		return "tasktables/show";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel,
			Locale locale) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute(
					"tasktables",
					localizeEntities(
							TaskTable.findTaskTableEntries(page == null ? 0 : (page.intValue() - 1)
									* sizeNo, sizeNo), locale));
			float nrOfPages = (float) TaskTable.countTaskTables() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("tasktables",
					localizeEntities(TaskTable.findAllTaskTables(), locale));
		}
		return "tasktables/list";
	}

}
