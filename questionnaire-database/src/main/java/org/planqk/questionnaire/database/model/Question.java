package org.planqk.questionnaire.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection = "questions")
@Builder
public class Question {

    @Id @JsonProperty("_id")
    private String questionId;

    private String question;

    @Nullable
    private List<String> possibleValues;

    @Nullable
    private NumberRange numberRange;

    private QuestionType questionType;

    //@JsonIgnore
    //@DocumentReference(lookup = "{ 'from': ?#{#self._id} }")
    //private List<QuestionTransition> transitions;

    private boolean root;

}
