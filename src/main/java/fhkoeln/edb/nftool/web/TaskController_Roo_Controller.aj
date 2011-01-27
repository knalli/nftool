// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool.web;

import fhkoeln.edb.nftool.Exercise;
import fhkoeln.edb.nftool.ExerciseState;
import fhkoeln.edb.nftool.Task;
import fhkoeln.edb.nftool.TaskTable;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
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

privileged aspect TaskController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String TaskController.create(@Valid Task task, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "tasks/create";
        }
        task.persist();
        return "redirect:/tasks/" + encodeUrlPathSegment(task.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String TaskController.createForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String TaskController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", Task.findTask(id));
        model.addAttribute("itemId", id);
        return "tasks/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String TaskController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("tasks", Task.findTaskEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Task.countTasks() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("tasks", Task.findAllTasks());
        }
        return "tasks/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String TaskController.update(@Valid Task task, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "tasks/update";
        }
        task.merge();
        return "redirect:/tasks/" + encodeUrlPathSegment(task.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String TaskController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", Task.findTask(id));
        return "tasks/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String TaskController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Task.findTask(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tasks?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(params = { "find=ByState", "form" }, method = RequestMethod.GET)
    public String TaskController.findTasksByStateForm(Model model) {
        model.addAttribute("exercisestates", java.util.Arrays.asList(ExerciseState.class.getEnumConstants()));
        return "tasks/findTasksByState";
    }
    
    @RequestMapping(params = "find=ByState", method = RequestMethod.GET)
    public String TaskController.findTasksByState(@RequestParam("state") ExerciseState state, Model model) {
        model.addAttribute("tasks", Task.findTasksByState(state).getResultList());
        return "tasks/list";
    }
    
    @RequestMapping(params = { "find=ByExerciseAndState", "form" }, method = RequestMethod.GET)
    public String TaskController.findTasksByExerciseAndStateForm(Model model) {
        model.addAttribute("exercises", Exercise.findAllExercises());
        model.addAttribute("exercisestates", java.util.Arrays.asList(ExerciseState.class.getEnumConstants()));
        return "tasks/findTasksByExerciseAndState";
    }
    
    @RequestMapping(params = "find=ByExerciseAndState", method = RequestMethod.GET)
    public String TaskController.findTasksByExerciseAndState(@RequestParam("exercise") Exercise exercise, @RequestParam("state") ExerciseState state, Model model) {
        model.addAttribute("tasks", Task.findTasksByExerciseAndState(exercise, state).getResultList());
        return "tasks/list";
    }
    
    @ModelAttribute("exercises")
    public Collection<Exercise> TaskController.populateExercises() {
        return Exercise.findAllExercises();
    }
    
    @ModelAttribute("exercisestates")
    public Collection<ExerciseState> TaskController.populateExerciseStates() {
        return Arrays.asList(ExerciseState.class.getEnumConstants());
    }
    
    @ModelAttribute("tasktables")
    public Collection<TaskTable> TaskController.populateTaskTables() {
        return TaskTable.findAllTaskTables();
    }
    
    String TaskController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
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