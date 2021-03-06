// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.TableRow;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect TableRow_Roo_Json {
    
    public String TableRow.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static TableRow TableRow.fromJsonToTableRow(String json) {
        return new JSONDeserializer<TableRow>().use(null, TableRow.class).deserialize(json);
    }
    
    public static String TableRow.toJsonArray(Collection<TableRow> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<TableRow> TableRow.fromJsonArrayToTableRows(String json) {
        return new JSONDeserializer<List<TableRow>>().use(null, ArrayList.class).use("values", TableRow.class).deserialize(json);
    }
    
}
