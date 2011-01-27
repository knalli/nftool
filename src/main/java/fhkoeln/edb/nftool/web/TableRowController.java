package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.TableRow;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "tablerows", formBackingObject = TableRow.class)
@RequestMapping("/tablerows")
@Controller
public class TableRowController {
}
