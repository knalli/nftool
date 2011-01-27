package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import fhkoeln.edb.nftool.Points;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "pointses", formBackingObject = Points.class)
@RequestMapping("/pointses")
@Controller
public class PointsController {
}
