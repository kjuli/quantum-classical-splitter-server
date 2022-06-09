package org.planqk.quantumclassicalsplitter.questionair;

import org.planqk.quantumclassicalsplitter.questionair.dto.TaskQuestions;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class provides functions for adding a new question per task. The
 * questions are added lazily, since not all question might fit to a task.
 *
 * TODO: Add further question types, i.e. what values to expect, value ranges, etc.
 *
 * @author Julijan Katic
 * @version 1.0
 */
public class QuestionaireSupplier {

    private final List<Function<String, String>> questions;

    public QuestionaireSupplier() {
        this.questions = new LinkedList<>();
    }

    /**
     * Adds a new question supplier depending on a task. The supplier receives the task
     * name as an input and can return {@code null} or a blank string if the question
     * should not be provided to that specific task.
     *
     * @param questionSupplier The functional question supplier.
     * @return This instance.
     */
    public QuestionaireSupplier add(final Function<String, String> questionSupplier) {
        this.questions.add(Objects.requireNonNull(questionSupplier));
        return this;
    }

    /**
     * Creates a list of {@link TaskQuestions} per task.
     * @param tasks The tasks to create the response from.
     * @return A DTO of TaskQuestions.
     */
    public List<TaskQuestions> consume(final List<String> tasks) {
        return tasks.stream()
                .map(task ->
                    new TaskQuestions(task, questions.stream()
                            .map(f -> f.apply(task))
                            .filter(question -> question != null && !question.isBlank())
                            .collect(Collectors.toList())
                    )
                )
                .collect(Collectors.toList());
    }

    public static QuestionaireSupplier newInstance() {
        return new QuestionaireSupplier();
    }
}
