package com.talentreef.interviewquestions.takehome.models.projections;

import java.math.BigDecimal;

public interface WidgetProjection {
    BigDecimal getPrice();
    String getName();
    String getDescription();
}
