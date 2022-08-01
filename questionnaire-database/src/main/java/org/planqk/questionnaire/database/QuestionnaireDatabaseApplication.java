package org.planqk.questionnaire.database;

import org.planqk.questionnaire.database.model.NumberRange;
import org.planqk.questionnaire.database.model.Question;
import org.planqk.questionnaire.database.model.QuestionTransition;
import org.planqk.questionnaire.database.model.QuestionType;
import org.planqk.questionnaire.database.repository.QuestionRepository;
import org.planqk.questionnaire.database.repository.QuestionTransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = QuestionRepository.class)
public class QuestionnaireDatabaseApplication implements CommandLineRunner {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private QuestionTransitionRepository transitionRepository;

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireDatabaseApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

    @Override
    public void run(final String... args) throws Exception {
        repository.deleteAll();
        transitionRepository.deleteAll();

        final Question nutzen = Question.builder()
                .question("Wollen Sie einen Quantencomputer nutzen?")
                .questionType(QuestionType.YES_NO)
                .root(true)
                .build();

        final Question wichtig = Question.builder()
                .question("Ist Ihnen Performance wichtig?")
                .questionType(QuestionType.YES_NO)
                .root(false)
                .build();

        final Question soll = Question.builder()
                                .question("Dann wissen wir nicht weiter")
                                .questionType(QuestionType.YES_NO)
                                        .root(false)
                                                .build();

        repository.save(nutzen);
        repository.save(wichtig);
        repository.save(soll);

        final QuestionTransition questionTransition = new QuestionTransition(nutzen, wichtig, "$answer === 'yes'");
        final QuestionTransition questionTransition1 = new QuestionTransition(nutzen, soll, "$answer === 'no'");

        transitionRepository.save(questionTransition);
        transitionRepository.save(questionTransition1);

    }

}
