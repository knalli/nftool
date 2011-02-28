package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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

	@Enumerated
	private ExerciseState state;

	@Value("false")
	@Transient
	private boolean solved;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "task_fk")
	private Set<TaskTable> taskTables = new HashSet<TaskTable>();

	@ManyToOne
	@JoinColumn(name = "exercise_fk")
	private Exercise exercise;
}
