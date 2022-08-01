package org.planqk.questionnaire.database.service;

import org.planqk.questionnaire.database.dto.QuestionDto;
import org.planqk.questionnaire.database.dto.QuestionTransitionDto;
import org.planqk.questionnaire.database.model.Question;
import org.planqk.questionnaire.database.model.QuestionTransition;
import org.planqk.questionnaire.database.repository.QuestionRepository;
import org.planqk.questionnaire.database.repository.QuestionTransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QuestionnaireService {

    private final QuestionRepository questionRepository;
    private final QuestionTransitionRepository questionTransitionRepository;

    @Autowired
    public QuestionnaireService(final QuestionRepository questionRepository, final QuestionTransitionRepository questionTransitionRepository) {
        this.questionRepository = questionRepository;
        this.questionTransitionRepository = questionTransitionRepository;
    }

    @Transactional(readOnly = true)
    public QuestionDto getFullQuestionnaire() {
        final List<Question> questions = questionRepository.findAll();
        final List<QuestionTransition> transitions = questionTransitionRepository.findAll();
        return new QuestionDto(questions,
                transitions.stream().map(QuestionTransitionDto::fromDB).collect(Collectors.toList()));
    }
}
