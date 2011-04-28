package fhkoeln.edb.nftool.web;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.TableColumn;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "tablecolumns", formBackingObject = TableColumn.class)
@RequestMapping("/tablecolumns")
@Controller
public class TableColumnController extends AbstractLocalizedController<TableColumn> {

	/**
	 * Update a TableColumn and save its localized Labels for the given locale.
	 * 
	 * @param tableColumn
	 * @param bindingResult
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid TableColumn tableColumn, BindingResult bindingResult,
			Model uiModel, Locale locale) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("tableColumn", tableColumn);
			return "tablecolumns/update";
		}
		uiModel.asMap().clear();
		tableColumn.merge();
		updateEntityLocalizations(tableColumn, locale);
		return "redirect:/tablecolumns/" + tableColumn.getId().toString();
	}

	/**
	 * Prepare form to display.
	 * 
	 * @param id
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TableColumn tableColumn = localizeEntity(TableColumn.findTableColumn(id), locale);
		uiModel.addAttribute("tableColumn", tableColumn);
		return "tablecolumns/update";
	}

	/**
	 * Show a single TableColumn. It's transient fields are filled with data from i18nService.
	 * 
	 * @param id
	 * @param uiModel
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel, Locale locale) {
		TableColumn tableColumn = localizeEntity(TableColumn.findTableColumn(id), locale);
		uiModel.addAttribute("tablecolumn", tableColumn);
		uiModel.addAttribute("itemId", id);
		return "tablecolumns/show";
	}

	/**
	 * List all TableColumns.
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
		List<TableColumn> tableColumns;
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			tableColumns = TableColumn.findTableColumnEntries(page == null ? 0
					: (page.intValue() - 1) * sizeNo, sizeNo);
			for (TableColumn tableColumn : tableColumns) {
				localizeEntity(tableColumn, locale);
			}
			uiModel.addAttribute("tablecolumns", tableColumns);
			float nrOfPages = (float) TableColumn.countTableColumns() / sizeNo;
			uiModel.addAttribute("maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			tableColumns = TableColumn.findAllTableColumns();
			for (TableColumn tableColumn : tableColumns) {
				localizeEntity(tableColumn, locale);
			}
			uiModel.addAttribute("tablecolumns", tableColumns);
		}
		return "tablecolumns/list";
	}
}
