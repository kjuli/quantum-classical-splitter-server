package org.planqk.questionnaire.database.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "transitions")
@Getter @Setter
@NoArgsConstructor
public class QuestionTransition {

    @Id @JsonProperty("_id")
    private String id;

    @DocumentReference
    private Question from;

    @DocumentReference
    private Question to;

    private String condition;

    public QuestionTransition(final Question from, final Question to, final String condition) {
        this.from = from;
        this.to = to;
        this.condition = condition;
    }
}
