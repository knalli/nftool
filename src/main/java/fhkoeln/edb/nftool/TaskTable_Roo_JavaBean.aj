// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.TableRow;
import java.lang.String;
import java.util.Set;

privileged aspect TaskTable_Roo_JavaBean {
    
    public String TaskTable.getDescription() {
        return this.description;
    }
    
    public void TaskTable.setDescription(String description) {
        this.description = description;
    }
    
    public short TaskTable.getOrdering() {
        return this.ordering;
    }
    
    public void TaskTable.setOrdering(short ordering) {
        this.ordering = ordering;
    }
    
    public short TaskTable.getNormalform() {
        return this.normalform;
    }
    
    public void TaskTable.setNormalform(short normalform) {
        this.normalform = normalform;
    }
    
    public Set<TableColumn> TaskTable.getTableColumns() {
        return this.tableColumns;
    }
    
    public void TaskTable.setTableColumns(Set<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }
    
    public java.util.Set<TableRow> TaskTable.getTableRows() {
        return this.tableRows;
    }
    
    public void TaskTable.setTableRows(java.util.Set<TableRow> tableRows) {
        this.tableRows = tableRows;
    }
    
}
