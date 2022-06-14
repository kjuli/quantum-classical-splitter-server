package org.planqk.quantumclassicalsplitter.questionair.bpmn;

import org.junit.jupiter.api.Test;
import org.planqk.quantumclassicalsplitter.questionair.dto.Task;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class BPMNFileParserTest {

    private final BPMNFileParserService bpmnFileParserService = new BPMNFileParserService();

    @Test
    public void testBpmnFileParser_GetAllCorrectTasks() throws IOException {
        final InputStream bpmnInputStream = BPMNFileParserTest.class.getClassLoader().getResourceAsStream("sample.bpmn");
        final MockMultipartFile file = new MockMultipartFile("sample", "sample.bpmn", MediaType.TEXT_XML_VALUE, bpmnInputStream);

        final List<Task> tasks = bpmnFileParserService.getBPMNTasks(file);

        final Task task1 = new Task("Task_1", "Example Task 1");
        final Task task2 = new Task("Task_2", "Example Task 2");

        assertThat(tasks, containsInAnyOrder(task1, task2));
    }
}
