package fhkoeln.edb.nftool;

import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import fhkoeln.edb.nftool.TableColumn;

@RooJavaBean
@RooToString
@RooEntity(table = "TABLE_ROWS")
@RooSerializable
@SuppressWarnings("serial")
public class TableRow implements Serializable, ExerciseEntity {

	@NotNull
	private Integer rowNumber;

	// @NotNull
	// @Size(max = 100)
	// private String content;

	// @NotNull
	// @Size(min = 2, max = 2)
	// private String locale;

	// @ManyToOne(optional = false)
	// private TaskTable taskTable;

	@ManyToOne(optional = false)
	private TableColumn tableColumn;
}
