package org.planqk.quantumclassicalsplitter.questionair;

import org.planqk.quantumclassicalsplitter.questionair.dto.Question;
import org.planqk.quantumclassicalsplitter.questionair.dto.QuestionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuestionairApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionairApplication.class, args);
    }

    /* TODO: Add appropriate questions here */
    @Bean
    public QuestionaireSupplier questionaireSupplier() {
        return QuestionaireSupplier.newInstance()
                .add(task -> Question.builder()
                        .questionType(QuestionType.SINGLE_CHOICE)
                        .question("Is this a Quantum Task?")
                        .possibleValue("yes")
                        .possibleValue("no")
                        .build());
    }
}
