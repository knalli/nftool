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
    public String TaskController.create(@Valid Task task, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("task", task);
            return "tasks/create";
        }
        uiModel.asMap().clear();
        task.persist();
        return "redirect:/tasks/" + encodeUrlPathSegment(task.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String TaskController.createForm(Model uiModel) {
        uiModel.addAttribute("task", new Task());
        return "tasks/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String TaskController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("task", Task.findTask(id));
        uiModel.addAttribute("itemId", id);
        return "tasks/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String TaskController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("tasks", Task.findTaskEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Task.countTasks() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("tasks", Task.findAllTasks());
        }
        return "tasks/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String TaskController.update(@Valid Task task, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("task", task);
            return "tasks/update";
        }
        uiModel.asMap().clear();
        task.merge();
        return "redirect:/tasks/" + encodeUrlPathSegment(task.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String TaskController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("task", Task.findTask(id));
        return "tasks/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String TaskController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Task.findTask(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tasks";
    }
    
    @RequestMapping(params = { "find=ByExerciseAndState", "form" }, method = RequestMethod.GET)
    public String TaskController.findTasksByExerciseAndStateForm(Model uiModel) {
        uiModel.addAttribute("exercises", Exercise.findAllExercises());
        uiModel.addAttribute("exercisestates", java.util.Arrays.asList(ExerciseState.class.getEnumConstants()));
        return "tasks/findTasksByExerciseAndState";
    }
    
    @RequestMapping(params = "find=ByExerciseAndState", method = RequestMethod.GET)
    public String TaskController.findTasksByExerciseAndState(@RequestParam("exercise") Exercise exercise, @RequestParam("state") ExerciseState state, Model uiModel) {
        uiModel.addAttribute("tasks", Task.findTasksByExerciseAndState(exercise, state).getResultList());
        return "tasks/list";
    }
    
    @RequestMapping(params = { "find=ByState", "form" }, method = RequestMethod.GET)
    public String TaskController.findTasksByStateForm(Model uiModel) {
        uiModel.addAttribute("exercisestates", java.util.Arrays.asList(ExerciseState.class.getEnumConstants()));
        return "tasks/findTasksByState";
    }
    
    @RequestMapping(params = "find=ByState", method = RequestMethod.GET)
    public String TaskController.findTasksByState(@RequestParam("state") ExerciseState state, Model uiModel) {
        uiModel.addAttribute("tasks", Task.findTasksByState(state).getResultList());
        return "tasks/list";
    }
    
    @ModelAttribute("exercises")
    public Collection<Exercise> TaskController.populateExercises() {
        return Exercise.findAllExercises();
    }
    
    @ModelAttribute("exercisestates")
    public java.util.Collection<ExerciseState> TaskController.populateExerciseStates() {
        return Arrays.asList(ExerciseState.class.getEnumConstants());
    }
    
    @ModelAttribute("tasks")
    public java.util.Collection<Task> TaskController.populateTasks() {
        return Task.findAllTasks();
    }
    
    @ModelAttribute("tasktables")
    public java.util.Collection<TaskTable> TaskController.populateTaskTables() {
        return TaskTable.findAllTaskTables();
    }
    
    String TaskController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
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
