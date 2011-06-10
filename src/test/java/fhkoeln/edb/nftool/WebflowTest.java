package fhkoeln.edb.nftool;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class WebflowTest extends AbstractXmlFlowExecutionTests {

	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory.createFileResource("src/main/webapp/views/doexercise/flow.xml");
	}

	private AnswerColumns answerColumns;
	private AnswerTables answerTables;

	@Before
	public void initAnswers() {
		answerColumns = new AnswerColumns();
		answerTables = new AnswerTables();
	}

	@Ignore
	public void testStartDoExerciseFlowExecution() {
		MutableAttributeMap input = new LocalAttributeMap();
		input.put("answer", answerColumns);
		MockExternalContext context = new MockExternalContext();
		// context.setCurrentUser("keith");
		startFlow(input, context);

		assertCurrentStateEquals("intro");
		assertTrue(getRequiredFlowAttribute("Exercise") instanceof Exercise);
	}

}
