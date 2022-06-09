package org.planqk.quantumclassicalsplitter.questionair.routes;

import com.google.gson.Gson;
import org.planqk.quantumclassicalsplitter.questionair.bpmn.BPMNFileParserService;
import org.planqk.quantumclassicalsplitter.questionair.dto.TaskQuestions;
import org.planqk.quantumclassicalsplitter.questionair.service.QuestionaireGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This controller adds endpoints for generating a questionaire. The response
 * will be in JSON, where per each task there are multiple questions. Sometimes,
 * a question might not fit to a specific task.
 *
 * Example response:
 * <pre><code>
 *     [
 *      {
 *          "task": "Do something",
 *          "questions": ["Question 1?", "Question 2?", ...]
 *      },
 *      ...
 *     ]
 * </code></pre>
 *
 * @author Julijan Katic
 * @version 1.0
 * @see QuestionaireGeneratorService
 */
@RestController
@RequestMapping(path = "/questionaire")
public class QuestionaireGeneratorController {
    private static final String FILE_TYPE = "text/xml";

    private final BPMNFileParserService bpmnFileParserService;
    private final QuestionaireGeneratorService questionaireGeneratorService;

    private final Gson gson = new Gson();

    @Autowired
    public QuestionaireGeneratorController(final BPMNFileParserService bpmnFileParserService,
                                           final QuestionaireGeneratorService questionaireGeneratorService) {
        this.bpmnFileParserService = bpmnFileParserService;
        this.questionaireGeneratorService = questionaireGeneratorService;
    }

    @PostMapping
    public ResponseEntity<?> handleBPMNFileUpload(@RequestParam("file") final MultipartFile file) {
        if (!FILE_TYPE.equalsIgnoreCase(file.getContentType())) {
            return ResponseEntity.badRequest()
                    .body("File must be a '" + FILE_TYPE + "', but actually is '" + file.getContentType() + "'");
        }

        final List<String> tasks = this.bpmnFileParserService.getBPMNTasks(file);
        final List<TaskQuestions> questions = this.questionaireGeneratorService.getQuestionsPerTask(tasks);

        return ResponseEntity.ok(gson.toJson(questions));
    }

}
