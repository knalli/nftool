package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
