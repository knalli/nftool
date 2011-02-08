// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool.web;

import fhkoeln.edb.nftool.service.LocalizedLabel;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect LocalizedLabelController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String LocalizedLabelController.create(@Valid LocalizedLabel localizedLabel, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("localizedLabel", localizedLabel);
            return "localizedlabels/create";
        }
        localizedLabel.persist();
        return "redirect:/localizedlabels/" + encodeUrlPathSegment(localizedLabel.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String LocalizedLabelController.createForm(Model model) {
        model.addAttribute("localizedLabel", new LocalizedLabel());
        return "localizedlabels/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String LocalizedLabelController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("localizedlabel", LocalizedLabel.findLocalizedLabel(id));
        model.addAttribute("itemId", id);
        return "localizedlabels/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String LocalizedLabelController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("localizedlabels", LocalizedLabel.findLocalizedLabelEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) LocalizedLabel.countLocalizedLabels() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("localizedlabels", LocalizedLabel.findAllLocalizedLabels());
        }
        return "localizedlabels/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String LocalizedLabelController.update(@Valid LocalizedLabel localizedLabel, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("localizedLabel", localizedLabel);
            return "localizedlabels/update";
        }
        localizedLabel.merge();
        return "redirect:/localizedlabels/" + encodeUrlPathSegment(localizedLabel.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String LocalizedLabelController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("localizedLabel", LocalizedLabel.findLocalizedLabel(id));
        return "localizedlabels/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String LocalizedLabelController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        LocalizedLabel.findLocalizedLabel(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/localizedlabels?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(params = { "find=ByEntityUriAndAttributeNameAndLocale", "form" }, method = RequestMethod.GET)
    public String LocalizedLabelController.findLocalizedLabelsByEntityUriAndAttributeNameAndLocaleForm(Model model) {
        return "localizedlabels/findLocalizedLabelsByEntityUriAndAttributeNameAndLocale";
    }
    
    @RequestMapping(params = "find=ByEntityUriAndAttributeNameAndLocale", method = RequestMethod.GET)
    public String LocalizedLabelController.findLocalizedLabelsByEntityUriAndAttributeNameAndLocale(@RequestParam("entityUri") String entityUri, @RequestParam("attributeName") String attributeName, @RequestParam("locale") Locale locale, Model model) {
        model.addAttribute("localizedlabels", LocalizedLabel.findLocalizedLabelsByEntityUriAndAttributeNameAndLocale(entityUri, attributeName, locale).getResultList());
        return "localizedlabels/list";
    }
    
    String LocalizedLabelController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
