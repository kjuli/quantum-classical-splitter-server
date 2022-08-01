package org.planqk.questionnaire.database.repository;

import org.planqk.questionnaire.database.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    Optional<Question> findQuestionByRoot(final boolean isRoot);

}
