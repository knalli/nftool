package fhkoeln.edb.nftool;

import org.springframework.context.annotation.Scope;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Component;

@Component
@RooJavaBean
@RooSerializable
@Scope(value = "session")
@SuppressWarnings("serial")
public class NewExercise {
	String language;
	String title = "Neue Aufgabe";
	String intro;

	Exercise exercise;

	String primaryKeyText;
	Task primaryKey;
	AnswerColumns primaryKeyColumns;

	String normalForm1Text;
	Task normalForm1;
	AnswerColumns normalForm1Columns;

	String normalForm2Text;
	Task normalForm2;
	AnswerTables normalForm2Tables;

	String normalForm3Text;
	Task normalForm3;
	AnswerTables normalForm3Tables;
}
