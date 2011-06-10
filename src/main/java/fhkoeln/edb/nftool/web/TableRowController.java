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

import fhkoeln.edb.nftool.TableRow;

@RooWebScaffold(path = "tablerows", formBackingObject = TableRow.class)
@RequestMapping("/tablerows")
@Controller
public class TableRowController extends AbstractLocalizedController<TableRow> {

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid TableRow tableRow, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("tableRow", tableRow);
			return "tablerows/create";
		}
		uiModel.asMap().clear();
		tableRow.persist();
		persistEntityLocalizations(tableRow, locale);
		return "redirect:/tablerows/" + tableRow.getId().toString();
	}

	/**
	 * Update a TableRow and theri corresponing LocalizedLabels.
	 * 
	 * @param tableRow
	 * @param bindingResult
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid TableRow tableRow, BindingResult bindingResult, Model uiModel,
			Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("tableRow", tableRow);
			return "tablerows/update";
		}
		uiModel.asMap().clear();
		tableRow.merge();
		updateEntityLocalizations(tableRow, locale);
		return "redirect:/tablerows/" + tableRow.getId().toString();
	}

	/**
	 * Prepare data to display a form for a TableRow with it's corresponding LocalizedLabels.
	 * 
	 * @param id
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TableRow tableRow = localizeEntity(TableRow.findTableRow(id), locale);
		uiModel.addAttribute("tableRow", tableRow);
		return "tablerows/update";
	}

	/**
	 * Show a single TableRow with it's corresponding LocalizedLabels.
	 * 
	 * @param id
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TableRow tableRow = localizeEntity(TableRow.findTableRow(id), locale);
		uiModel.addAttribute("tablerow", tableRow);
		uiModel.addAttribute("itemId", id);
		return "tablerows/show";
	}

	/**
	 * List all TableRows with their corresponding LocalizedLables.
	 * 
	 * @param page
	 * @param size
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel,
			Locale locale) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute(
					"tablerows",
					localizeEntities(
							TableRow.findTableRowEntries(page == null ? 0 : (page.intValue() - 1)
									* sizeNo, sizeNo), locale));
			float nrOfPages = (float) TableRow.countTableRows() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("tablerows", localizeEntities(TableRow.findAllTableRows(), locale));
		}
		return "tablerows/list";
	}
}
