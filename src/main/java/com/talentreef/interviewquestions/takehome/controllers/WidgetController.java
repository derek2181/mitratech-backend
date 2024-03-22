package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.services.WidgetServiceImpl;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/v1/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class WidgetController {

  private final WidgetServiceImpl widgetServiceImpl;
  //TODO custom exception handling
  //TODO add logs
  //TODO add Profiles
  public WidgetController(WidgetServiceImpl widgetServiceImpl) {
    Assert.notNull(widgetServiceImpl, "widgetService must not be null");
    this.widgetServiceImpl = widgetServiceImpl;
  }

  @GetMapping
  public ResponseEntity<GenericResponse> getAllWidgets(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "12") int size,
                                                       @RequestParam(name = "sort", defaultValue = "12") boolean sort) {
    var response=widgetServiceImpl.getAllWidgets(page,size,sort);
    //TODO add pagination
    return ResponseEntity.status(response.getCode()).body(response);
  }


  @PostMapping
  public ResponseEntity<GenericResponse> addWidget(@RequestBody WidgetDTO widgetDTO) {

    var response=widgetServiceImpl.addWidget(widgetDTO);
    return ResponseEntity.status(response.getCode()).body(response);
  }
  @GetMapping("search")
  public ResponseEntity<GenericResponse> getWidgetByName(@RequestParam(name = "name", required = false) String name,
                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "12") int size,
                                                         @RequestParam(name = "sort", defaultValue = "12") boolean sort) {

    var response=widgetServiceImpl.getWidgetByName(name,page,size,sort);
    return ResponseEntity.status(response.getCode()).body(response);
  }

  @PatchMapping("{widgetName}")
  public ResponseEntity<GenericResponse> updateWidget(@PathVariable String widgetName, @RequestBody WidgetDTO widgetDTO) {

    var response=widgetServiceImpl.updateWidgetByName(widgetName,widgetDTO);
    return ResponseEntity.status(response.getCode()).body(response);
  }

  @DeleteMapping("{widgetName}")
  public ResponseEntity<GenericResponse> deleteWidget(@PathVariable String widgetName) {

    var response=widgetServiceImpl.deleteWidgetByName(widgetName);
    return ResponseEntity.status(response.getCode()).body(response);
  }
}
