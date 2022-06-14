package org.planqk.quantumclassicalsplitter.questionair.routes;

import org.junit.jupiter.api.Test;
import org.planqk.quantumclassicalsplitter.questionair.QuestionaireSupplier;
import org.planqk.quantumclassicalsplitter.questionair.bpmn.BPMNFileParserService;
import org.planqk.quantumclassicalsplitter.questionair.bpmn.BPMNFileParserTest;
import org.planqk.quantumclassicalsplitter.questionair.dto.Question;
import org.planqk.quantumclassicalsplitter.questionair.dto.QuestionType;
import org.planqk.quantumclassicalsplitter.questionair.dto.Task;
import org.planqk.quantumclassicalsplitter.questionair.service.QuestionaireGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionaireGeneratorController.class)
public class QuestionaireGeneratorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BPMNFileParserService bpmnFileParserService;

    @MockBean
    private QuestionaireGeneratorService questionaireGeneratorService;

    @Autowired
    private QuestionaireSupplier supplier;

    @Test
    public void contextLoads() throws Exception {
        final Task task = new Task("Task_1", "Sample Task");
        final InputStream bpmnInputStream = BPMNFileParserTest.class.getClassLoader().getResourceAsStream("sample.bpmn");
        final MockMultipartFile file = new MockMultipartFile("file", "sample.bpmn", MediaType.TEXT_XML_VALUE, bpmnInputStream);

        when(bpmnFileParserService.getBPMNTasks(any())).thenReturn(List.of(task));
        when(questionaireGeneratorService.getQuestionsPerTask(any())).thenCallRealMethod();

        this.mockMvc.perform(multipart("/questionaire")
                        .file(file))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @TestConfiguration
//    public static class TestConfig {
//        @Bean
//        public QuestionaireSupplier questionaireSupplierTest() {
//            return QuestionaireSupplier.newInstance()
//                    .add(task -> Question.builder()
//                            .questionType(QuestionType.SINGLE_CHOICE)
//                            .question("Is this a Quantum Task?")
//                            .possibleValue("yes")
//                            .possibleValue("no")
//                            .build());
//        }
//    }
}
