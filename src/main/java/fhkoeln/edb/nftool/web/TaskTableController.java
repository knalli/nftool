package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.TaskTable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "tasktables", formBackingObject = TaskTable.class)
@RequestMapping("/tasktables")
@Controller
public class TaskTableController {
}
