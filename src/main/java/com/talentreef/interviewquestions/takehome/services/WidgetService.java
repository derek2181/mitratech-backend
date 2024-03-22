package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.models.projections.WidgetProjection;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import java.util.List;

public interface WidgetService {
     GenericResponse<List<WidgetDTO>> getAllWidgets();

     GenericResponse<?> addWidget(WidgetDTO widgetDTO);

     GenericResponse<WidgetProjection> getWidgetByName(String widgetName);

     GenericResponse<WidgetDTO> updateWidgetByName(String widgetName,WidgetDTO widgetDTO);

     GenericResponse<?> deleteWidgetByName(String widgetName);
}
