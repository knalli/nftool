package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class AnswerColumns implements Serializable {

	List<String> columns;

	Exercise exercise;

	String state;

	Integer points;

	Boolean solved = false;

	Locale locale;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AnswerColumns:");
		sb.append(columns);
		sb.append(" for Exercise ");
		sb.append(exercise);
		sb.append(" in state ");
		sb.append(state);
		sb.append(" (Locale:");
		sb.append(locale);
		sb.append(").");
		return sb.toString();
	}
}
