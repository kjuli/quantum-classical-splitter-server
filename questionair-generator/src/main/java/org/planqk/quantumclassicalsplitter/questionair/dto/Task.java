package org.planqk.quantumclassicalsplitter.questionair.dto;

import lombok.*;

/**
 * A task data-class representing a BPMN task.
 *
 * @author Julijan Katic
 * @version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Task {

    private String id;
    private String name;


}
