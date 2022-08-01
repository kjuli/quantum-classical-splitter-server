package org.planqk.questionnaire.database.repository;

import org.planqk.questionnaire.database.model.QuestionTransition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionTransitionRepository extends MongoRepository<QuestionTransition, String> {
}
