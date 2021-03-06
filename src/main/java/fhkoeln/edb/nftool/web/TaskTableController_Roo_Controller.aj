// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool.web;

import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.TableRow;
import fhkoeln.edb.nftool.TaskTable;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TaskTableController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String TaskTableController.createForm(Model uiModel) {
        uiModel.addAttribute("taskTable", new TaskTable());
        return "tasktables/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String TaskTableController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        TaskTable.findTaskTable(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/tasktables";
    }
    
    @ModelAttribute("tablecolumns")
    public Collection<TableColumn> TaskTableController.populateTableColumns() {
        return TableColumn.findAllTableColumns();
    }
    
    @ModelAttribute("tablerows")
    public java.util.Collection<TableRow> TaskTableController.populateTableRows() {
        return TableRow.findAllTableRows();
    }
    
    @ModelAttribute("tasktables")
    public java.util.Collection<TaskTable> TaskTableController.populateTaskTables() {
        return TaskTable.findAllTaskTables();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object TaskTableController.showJson(@PathVariable("id") Long id) {
        TaskTable tasktable = TaskTable.findTaskTable(id);
        if (tasktable == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return tasktable.toJson();
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public String TaskTableController.listJson() {
        return TaskTable.toJsonArray(TaskTable.findAllTaskTables());
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public org.springframework.http.ResponseEntity<String> TaskTableController.createFromJson(@RequestBody String json) {
        TaskTable.fromJsonToTaskTable(json).persist();
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public org.springframework.http.ResponseEntity<String> TaskTableController.createFromJsonArray(@RequestBody String json) {
        for (TaskTable taskTable: TaskTable.fromJsonArrayToTaskTables(json)) {
            taskTable.persist();
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public org.springframework.http.ResponseEntity<String> TaskTableController.updateFromJson(@RequestBody String json) {
        if (TaskTable.fromJsonToTaskTable(json).merge() == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public org.springframework.http.ResponseEntity<String> TaskTableController.updateFromJsonArray(@RequestBody String json) {
        for (TaskTable taskTable: TaskTable.fromJsonArrayToTaskTables(json)) {
            if (taskTable.merge() == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public org.springframework.http.ResponseEntity<String> TaskTableController.deleteFromJson(@PathVariable("id") Long id) {
        TaskTable tasktable = TaskTable.findTaskTable(id);
        if (tasktable == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        tasktable.remove();
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    String TaskTableController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
