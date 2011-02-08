package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@SuppressWarnings("serial")
@RooEntity(table = "TASKS", finders = { "findTasksByState", "findTasksByExerciseAndState" })
public class Task implements Serializable, ExerciseEntity {

	@NotNull
	private short ordering;

	// @NotNull
	// @Size(max = 200)
	// private String description;

	@Enumerated
	private ExerciseState state;

	@Value("false")
	@Transient
	private boolean solved;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "task")
	private Set<TaskTable> taskTables = new HashSet<TaskTable>();

	@ManyToOne
	private Exercise exercise;
}
