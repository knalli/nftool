package fhkoeln.edb.nftool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class AnswerColumns implements Serializable {

	List<Long> columnIds;

	Exercise exercise;

	String state;

	Integer points;

	Boolean solved = false;

	Locale locale;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AnswerColumns:");
		sb.append(columnIds);
		sb.append(" for Exercise ");
		sb.append(exercise);
		sb.append(" in state ");
		sb.append(state);
		sb.append(" (Locale:");
		sb.append(locale);
		sb.append(").");
		return sb.toString();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TableColumn> getColumns() {
		if (columnIds == null || columnIds.size() <= 0)
			return Collections.emptyList();
		List<TableColumn> result = new ArrayList<TableColumn>(columnIds.size());
		for (Long columnId : columnIds) {
			result.add(TableColumn.findTableColumn(columnId));
		}
		return result;
	}
}
