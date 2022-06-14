package org.planqk.quantumclassicalsplitter.questionair.dto;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "question")
public class Question {

    private String question;
    private QuestionType questionType;
    @Singular
    private List<String> possibleValues;
    @Builder.Default
    private double minValue = Double.NEGATIVE_INFINITY;
    @Builder.Default
    private double maxValue = Double.POSITIVE_INFINITY;


}
