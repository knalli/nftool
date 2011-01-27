package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.Exercise;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "exercises", formBackingObject = Exercise.class)
@RequestMapping("/exercises")
@Controller
public class ExerciseController {
}
