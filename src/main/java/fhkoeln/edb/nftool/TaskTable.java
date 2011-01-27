package fhkoeln.edb.nftool;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;
import fhkoeln.edb.nftool.TableColumn;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import fhkoeln.edb.nftool.TableRow;

@RooJavaBean
@RooToString
@RooEntity(table = "TASK_TABLES")
@RooSerializable
@SuppressWarnings("serial")
public class TaskTable implements Serializable {

	private short ordering;

	private short normalform;

	@NotNull
	@Size(max = 150)
	private String description;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<TableColumn> tableColumns = new HashSet<TableColumn>();

	@OneToMany(cascade = CascadeType.ALL)
	private Set<TableRow> tableRows = new HashSet<TableRow>();

	// @ManyToOne
	// private Task task;
}
