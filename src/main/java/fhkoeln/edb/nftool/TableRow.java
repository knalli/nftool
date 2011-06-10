package fhkoeln.edb.nftool;

import java.io.Serializable;

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
@RooEntity(table = "TABLE_ROWS")
@RooSerializable
@SuppressWarnings("serial")
public class TableRow implements Serializable, ExerciseEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rowNumber == null) ? 0 : rowNumber.hashCode());
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
		TableRow other = (TableRow) obj;
		if (!getId().equals(other.getId()))
			return false;
		if (rowNumber == null) {
			if (other.rowNumber != null)
				return false;
		} else if (!rowNumber.equals(other.rowNumber))
			return false;
		return true;
	}

	@Transient
	@I18nString
	private String content;

	@NotNull
	private Integer rowNumber;

}
