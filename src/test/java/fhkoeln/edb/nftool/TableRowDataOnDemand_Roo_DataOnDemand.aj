// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.TableRow;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect TableRowDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TableRowDataOnDemand: @Component;
    
    private Random TableRowDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<TableRow> TableRowDataOnDemand.data;
    
    public TableRow TableRowDataOnDemand.getNewTransientTableRow(int index) {
        fhkoeln.edb.nftool.TableRow obj = new fhkoeln.edb.nftool.TableRow();
        obj.setRowNumber(new Integer(index));
        return obj;
    }
    
    public TableRow TableRowDataOnDemand.getSpecificTableRow(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        TableRow obj = data.get(index);
        return TableRow.findTableRow(obj.getId());
    }
    
    public TableRow TableRowDataOnDemand.getRandomTableRow() {
        init();
        TableRow obj = data.get(rnd.nextInt(data.size()));
        return TableRow.findTableRow(obj.getId());
    }
    
    public boolean TableRowDataOnDemand.modifyTableRow(TableRow obj) {
        return false;
    }
    
    public void TableRowDataOnDemand.init() {
        data = fhkoeln.edb.nftool.TableRow.findTableRowEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'TableRow' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<fhkoeln.edb.nftool.TableRow>();
        for (int i = 0; i < 10; i++) {
            fhkoeln.edb.nftool.TableRow obj = getNewTransientTableRow(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
