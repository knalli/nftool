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

	@ManyToOne(optional = false)
	private TableColumn tableColumn;
}
