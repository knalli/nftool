package fhkoeln.edb.nftool;

import java.io.Serializable;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import fhkoeln.edb.nftool.TableColumn;
import fhkoeln.edb.nftool.i18n.I18nString;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(table = "TABLE_ROWS")
@RooSerializable
@SuppressWarnings("serial")
public class TableRow implements Serializable, ExerciseEntity {

	@Transient
	@I18nString
	private String content;

	@NotNull
	private Integer rowNumber;

}
