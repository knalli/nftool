package fhkoeln.edb.nftool;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooEntity
@RooSerializable
@SuppressWarnings("serial")
public class Points implements Serializable {

	@NotNull
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date gameDate;

	@NotNull
	@Min(0L)
	private Long points;

	@NotNull
	@Size(max = 10)
	private String appKey;

	// @ManyToOne
	// private Task task;
}
