package org.planqk.questionnaire.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.planqk.questionnaire.database.model.QuestionTransition;

@Data
@NoArgsConstructor @AllArgsConstructor
public class QuestionTransitionDto {

    private String from;
    private String to;
    private String condition;

    public static QuestionTransitionDto fromDB(final QuestionTransition transition) {
        return new QuestionTransitionDto(transition.getFrom().getQuestionId(),
                transition.getTo().getQuestionId(),
                transition.getCondition());
    }
}
