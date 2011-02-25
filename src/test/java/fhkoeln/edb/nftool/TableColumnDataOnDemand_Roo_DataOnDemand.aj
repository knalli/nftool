// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.TableColumn;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect TableColumnDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TableColumnDataOnDemand: @Component;
    
    private Random TableColumnDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<TableColumn> TableColumnDataOnDemand.data;
    
    public TableColumn TableColumnDataOnDemand.getNewTransientTableColumn(int index) {
        fhkoeln.edb.nftool.TableColumn obj = new fhkoeln.edb.nftool.TableColumn();
        obj.setKeyColumn(Boolean.TRUE);
        obj.setOrdering(new Integer(index));
        return obj;
    }
    
    public TableColumn TableColumnDataOnDemand.getSpecificTableColumn(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        TableColumn obj = data.get(index);
        return TableColumn.findTableColumn(obj.getId());
    }
    
    public TableColumn TableColumnDataOnDemand.getRandomTableColumn() {
        init();
        TableColumn obj = data.get(rnd.nextInt(data.size()));
        return TableColumn.findTableColumn(obj.getId());
    }
    
    public boolean TableColumnDataOnDemand.modifyTableColumn(TableColumn obj) {
        return false;
    }
    
    public void TableColumnDataOnDemand.init() {
        data = fhkoeln.edb.nftool.TableColumn.findTableColumnEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'TableColumn' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<fhkoeln.edb.nftool.TableColumn>();
        for (int i = 0; i < 10; i++) {
            fhkoeln.edb.nftool.TableColumn obj = getNewTransientTableColumn(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
