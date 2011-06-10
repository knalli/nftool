package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import fhkoeln.edb.nftool.i18n.I18nString;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(table = "TASK_TABLES")
@RooSerializable
@SuppressWarnings("serial")
public class TaskTable implements Serializable, ExerciseEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + normalform;
		result = prime * result + ordering;
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
		TaskTable other = (TaskTable) obj;
		if (!getId().equals(other.getId()))
			return false;
		if (normalform != other.normalform)
			return false;
		if (ordering != other.ordering)
			return false;
		return true;
	}

	@Transient
	@I18nString
	private String description;

	@OrderBy
	private short ordering;

	private short normalform;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "table_fk")
	// @OrderBy("keyColumn DESC")
	private Set<TableColumn> tableColumns = new HashSet<TableColumn>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "task_table_fk")
	@OrderBy("rowNumber ASC, id ASC")
	private Set<TableRow> tableRows = new HashSet<TableRow>();

}
