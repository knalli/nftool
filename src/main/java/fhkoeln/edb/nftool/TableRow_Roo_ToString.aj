// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import java.lang.String;

privileged aspect TableRow_Roo_ToString {
    
    public String TableRow.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RowNumber: ").append(getRowNumber()).append(", ");
        sb.append("TableColumn: ").append(getTableColumn());
        return sb.toString();
    }
    
}
