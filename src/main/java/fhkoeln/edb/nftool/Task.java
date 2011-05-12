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
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import fhkoeln.edb.nftool.i18n.I18nString;

@RooJavaBean
@RooToString
@RooJson
@RooSerializable
@SuppressWarnings("serial")
@RooEntity(table = "TASKS", finders = { "findTasksByState", "findTasksByExerciseAndState" })
public class Task implements Serializable, ExerciseEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exercise == null) ? 0 : exercise.hashCode());
		result = prime * result + ordering;
		result = prime * result + points;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (!getId().equals(other.getId()))
			return false;
		if (exercise == null) {
			if (other.exercise != null)
				return false;
		} else if (!exercise.equals(other.exercise))
			return false;
		if (ordering != other.ordering)
			return false;
		if (points != other.points)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Transient
	@I18nString
	private String description;

	@NotNull
	private short ordering;

	@Enumerated
	private ExerciseState state;

	private int points = 1;

	@Value("false")
	@Transient
	private boolean solved;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "task_fk", nullable = true)
	private Set<TaskTable> taskTables = new HashSet<TaskTable>();

	@ManyToOne
	@JoinColumn(name = "exercise_fk")
	private Exercise exercise;
}
