package org.planqk.questionnaire.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumberRange {
    private static final NumberRange ALL_NUMBERS = new NumberRange();

    private double from = Double.NEGATIVE_INFINITY;
    private double to = Double.POSITIVE_INFINITY;

}
