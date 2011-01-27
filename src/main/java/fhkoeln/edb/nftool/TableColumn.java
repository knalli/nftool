package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@SuppressWarnings("serial")
@RooEntity(table = "TABLE_COLUMNS")
// , finders = { "findTableColumnsByTaskTableAndKeyColumn",
// "findTableColumnsByKeyColumn" })
public class TableColumn implements Serializable {

	@NotNull
	@Size(min = 1, max = 60)
	private String name;

	@NotNull
	@Size(min = 2, max = 2)
	private String locale;

	@NotNull
	private Boolean keyColumn;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<TableRow> tableRows = new HashSet<TableRow>();

	// @ManyToOne
	// private TaskTable taskTable;
}
