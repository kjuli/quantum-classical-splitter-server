package org.planqk.questionnaire.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.planqk.questionnaire.database.model.Question;

import java.util.List;

@Data @AllArgsConstructor
public final class QuestionDto {

    private List<Question> questions;
    private List<QuestionTransitionDto> questionTransitions;

}
