package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.WidgetDTO;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import java.util.List;

public interface WidgetService {
     GenericResponse<List<WidgetDTO>> getAllWidgets();

     GenericResponse addWidget(WidgetDTO widgetDTO);
}
