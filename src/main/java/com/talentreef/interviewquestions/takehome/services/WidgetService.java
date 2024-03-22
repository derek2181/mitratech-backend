package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.models.projections.WidgetProjection;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WidgetService {
     GenericResponse<Page<WidgetDTO>> getAllWidgets(int page,int size,boolean sort);

     GenericResponse<?> addWidget(WidgetDTO widgetDTO);

     GenericResponse<Page<WidgetProjection>> getWidgetByName(String widgetName,int page,int size,boolean sort);

     GenericResponse<WidgetDTO> updateWidgetByName(String widgetName, WidgetDTO widgetDTO);

     GenericResponse<?> deleteWidgetByName(String widgetName);
}
