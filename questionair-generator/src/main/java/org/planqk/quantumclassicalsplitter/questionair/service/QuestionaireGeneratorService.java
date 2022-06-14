package org.planqk.quantumclassicalsplitter.questionair.service;

import org.planqk.quantumclassicalsplitter.questionair.QuestionaireSupplier;
import org.planqk.quantumclassicalsplitter.questionair.dto.Question;
import org.planqk.quantumclassicalsplitter.questionair.dto.Task;
import org.planqk.quantumclassicalsplitter.questionair.dto.TaskQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service generates a questionaire according to the given tasks.
 *
 * @author Julijan Katic
 * @version 1.0
 * @see QuestionaireSupplier
 */
@Service
public class QuestionaireGeneratorService {

    private final QuestionaireSupplier questionaireSupplier;

    @Autowired
    public QuestionaireGeneratorService(final QuestionaireSupplier supplier) {
        this.questionaireSupplier = supplier;
    }

    public List<TaskQuestions> getQuestionsPerTask(final List<Task> tasks) {
        return questionaireSupplier.consume(tasks);
    }
}
