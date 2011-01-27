package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "tasks", formBackingObject = Task.class)
@RequestMapping("/tasks")
@Controller
public class TaskController {
}
