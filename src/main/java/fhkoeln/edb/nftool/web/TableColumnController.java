package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.TableColumn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "tablecolumns", formBackingObject = TableColumn.class)
@RequestMapping("/tablecolumns")
@Controller
public class TableColumnController {
}
