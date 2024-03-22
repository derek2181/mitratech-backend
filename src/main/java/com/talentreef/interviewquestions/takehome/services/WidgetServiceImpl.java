package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.entities.Widget;
import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.models.projections.WidgetProjection;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WidgetServiceImpl implements WidgetService {

  private final WidgetRepository widgetRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private WidgetServiceImpl(WidgetRepository widgetRepository) {
    Assert.notNull(widgetRepository, "widgetRepository must not be null");
    this.widgetRepository = widgetRepository;
  }

  public GenericResponse<List<WidgetDTO>> getAllWidgets() {
    //Maps all the widgets into the DTO class
    var widgetsEntities=widgetRepository.findAll();
    var widgets=widgetRepository.findAll().stream()
            .map(entity -> modelMapper.map(entity, WidgetDTO.class))
            .collect(Collectors.toList());

    //Build the response
    var response=new GenericResponse<List<WidgetDTO>>();
    response.setCode(200);
    response.setResult(widgets);
    return response;
  }

  @Override
  public GenericResponse addWidget(WidgetDTO widgetDTO) {
    GenericResponse response=new GenericResponse();
    try{
      Widget widget=modelMapper.map(widgetDTO,Widget.class);
      widgetRepository.save(widget);
      response.setCode(201);
      response.setMessage("Widget created succesfully");
      return response;
    }catch (Exception e){
      response.setCode(400);
      response.setMessage(e.getMessage());
      return response;
    }
  }

  @Override
  public GenericResponse<WidgetProjection> getWidgetByName(String widgetName) {
    var response=new GenericResponse<WidgetProjection>();
    try{
    var widgetEntity=widgetRepository.findByNameWithProjection(widgetName);

    if(widgetEntity==null){
      response.setCode(404);
      response.setMessage("The widget with name: " + widgetName + " could not be found");
      return response;
    }

    var widget=modelMapper.map(widgetEntity, WidgetProjection.class);

    //Build the response
    response.setCode(200);
    response.setResult(widget);
    return response;
    }catch (Exception e){
      response.setCode(400);
      response.setMessage(e.getMessage());
      return response;
    }
  }

}
