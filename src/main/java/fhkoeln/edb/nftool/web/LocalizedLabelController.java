package fhkoeln.edb.nftool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;

import fhkoeln.edb.nftool.service.LocalizedLabel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "localizedlabels", formBackingObject = LocalizedLabel.class)
@RequestMapping("/localizedlabels")
@Controller
public class LocalizedLabelController {
}
