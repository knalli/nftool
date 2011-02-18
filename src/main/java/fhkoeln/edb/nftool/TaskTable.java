package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(table = "TASK_TABLES")
@RooSerializable
@SuppressWarnings("serial")
public class TaskTable implements Serializable, ExerciseEntity {

	private short ordering;

	private short normalform;

	// @NotNull
	// @Size(max = 150)
	// private String description;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tableColumn")
	private Set<TableColumn> tableColumns = new HashSet<TableColumn>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "taskTable")
	private Set<TableRow> tableRows = new HashSet<TableRow>();

}
