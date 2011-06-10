package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
@RooEntity(table = "TABLE_COLUMNS")
// , finders = { "findTableColumnsByTaskTableAndKeyColumn",
// "findTableColumnsByKeyColumn" })
public class TableColumn implements Serializable, ExerciseEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyColumn == null) ? 0 : keyColumn.hashCode());
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
		TableColumn other = (TableColumn) obj;
		if (!getId().equals(other.getId()))
			return false;
		if (keyColumn == null) {
			if (other.keyColumn != null)
				return false;
		} else if (!keyColumn.equals(other.keyColumn))
			return false;
		if (ordering != other.ordering)
			return false;
		return true;
	}

	@I18nString
	@Transient
	private String name;

	@NotNull
	private Boolean keyColumn;

	private int ordering = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "task_column_fk")
	private Set<TableRow> tableRows = new HashSet<TableRow>();

}
