// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.TableColumn;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TableColumn_Roo_Json {
    
    public String TableColumn.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TableColumn TableColumn.fromJsonToTableColumn(String json) {
        return new JSONDeserializer<TableColumn>().use(null, TableColumn.class).deserialize(json);
    }
    
    public static String TableColumn.toJsonArray(Collection<TableColumn> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TableColumn> TableColumn.fromJsonArrayToTableColumns(String json) {
        return new JSONDeserializer<List<TableColumn>>().use(null, ArrayList.class).use("values", TableColumn.class).deserialize(json);
    }
    
}
