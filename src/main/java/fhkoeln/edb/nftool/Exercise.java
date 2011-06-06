package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.transaction.annotation.Transactional;

import fhkoeln.edb.nftool.i18n.I18nString;

@SuppressWarnings("serial")
@RooJavaBean
// @RooToString
@RooEntity(table = "EXERCISES")
@RooSerializable
@RooJson
public class Exercise implements Serializable, ExerciseEntity {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Exercise other = (Exercise) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Transient
	private transient static final Logger logger = Logger.getLogger(Exercise.class);

	@Transient
	@I18nString
	private String title;

	@Transient
	@I18nString
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "exercise")
	private Set<Task> tasks = new HashSet<Task>();

	/**
	 * Throws IllegalArgumentException, if the String is not defined in Enum ExerciseState.
	 * 
	 * @param str
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Transactional(readOnly = true)
	public Task getTaskByState(String str) {
		return getTaskByState(ExerciseState.valueOf(str));
	}

	@Transactional(readOnly = true)
	public Task getTaskByState(ExerciseState es) {
		// return Task.findTasksByExerciseAndType(this, es).getSingleResult(); // TODO : DOESNT
		// WORK?

		if (es == null)
			throw new IllegalArgumentException("The State was null!");
		if (tasks.size() < 1) {
			logger.warn("There are no Tasks defined for this Exercise! " + tasks);
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Searching Task for state " + es.name());
		}

		for (Task t : tasks) {
			if (logger.isTraceEnabled()) {
				logger.trace("Found Task " + t.getState());
			}
			if (es.equals(t.getState()))
				return t;
		}
		logger.warn("No Task found for state " + es.name());
		return null;

	}

	public static Exercise getRandomExercise() {
		EntityManager em = new Exercise().entityManager;
		long cnt = countExercises(); // em.createQuery("select count(*) from Exercise",
										// Long.class).getSingleResult();
		Long rnd = Math.round(Math.random() * cnt);
		return em.createQuery("select o from Exercise e", Exercise.class)
				.setFirstResult(rnd.intValue()).setMaxResults(1).getSingleResult();
	}

	/**
	 * Can't use @RooToString here, because it tries to ouput {{@link #getRandomExercise()}. WTF?
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(getId()).append(", ");
		// sb.append("Title: ").append(getTitle()).append(", ");
		// sb.append("Description: ").append(getDescription()).append(", ");
		sb.append("Tasks: ").append(getTasks() == null ? "null" : getTasks().size());
		return sb.toString();
	}

}
