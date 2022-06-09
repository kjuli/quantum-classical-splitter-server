package org.planqk.quantumclassicalsplitter.questionair.dto;

import lombok.Data;

import java.util.List;

/**
 * This class represents a Data object that specifies for each task a required question.
 */
@Data
public class TaskQuestions {

    /** The specific tasks. */
    private final String task;

    /** The questions for this task to answer */
    private final List<String> questions;

}
