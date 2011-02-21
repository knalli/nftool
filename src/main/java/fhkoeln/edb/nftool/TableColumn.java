package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
public class TableColumn implements Serializable, ExerciseEntity {

	// @NotNull
	// @Size(min = 1, max = 60)
	// private String name;

	// @NotNull
	// @Size(min = 2, max = 2)
	// private String locale;

	@NotNull
	private Boolean keyColumn;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "task_column_fk")
	private Set<TableRow> tableRows = new HashSet<TableRow>();

}
