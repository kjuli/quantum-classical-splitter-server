package org.planqk.questionnaire.database.controller;

import org.planqk.questionnaire.database.dto.QuestionDto;
import org.planqk.questionnaire.database.model.Question;
import org.planqk.questionnaire.database.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("/root")
    public ResponseEntity<QuestionDto> getFullTree() {
        return ResponseEntity.ok(questionnaireService.getFullQuestionnaire());
    }

}
