// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import java.lang.String;

privileged aspect TaskTable_Roo_ToString {
    
    public String TaskTable.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordering: ").append(getOrdering()).append(", ");
        sb.append("Normalform: ").append(getNormalform()).append(", ");
        sb.append("TableColumns: ").append(getTableColumns() == null ? "null" : getTableColumns().size()).append(", ");
        sb.append("TableRows: ").append(getTableRows() == null ? "null" : getTableRows().size());
        return sb.toString();
    }
    
}
