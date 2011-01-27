// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool.web;

import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.Task;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ExerciseController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String ExerciseController.create(@Valid Exercise exercise, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("exercise", exercise);
            return "exercises/create";
        }
        exercise.persist();
        return "redirect:/exercises/" + encodeUrlPathSegment(exercise.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ExerciseController.createForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "exercises/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ExerciseController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exercise", Exercise.findExercise(id));
        model.addAttribute("itemId", id);
        return "exercises/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ExerciseController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("exercises", Exercise.findExerciseEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Exercise.countExercises() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("exercises", Exercise.findAllExercises());
        }
        return "exercises/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ExerciseController.update(@Valid Exercise exercise, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("exercise", exercise);
            return "exercises/update";
        }
        exercise.merge();
        return "redirect:/exercises/" + encodeUrlPathSegment(exercise.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ExerciseController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exercise", Exercise.findExercise(id));
        return "exercises/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ExerciseController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Exercise.findExercise(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/exercises?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @ModelAttribute("tasks")
    public Collection<Task> ExerciseController.populateTasks() {
        return Task.findAllTasks();
    }
    
    String ExerciseController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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