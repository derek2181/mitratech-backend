package com.talentreef.interviewquestions.takehome.controllers;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentreef.interviewquestions.takehome.entities.Widget;
import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import com.talentreef.interviewquestions.takehome.services.WidgetServiceImpl;
import com.talentreef.interviewquestions.takehome.utils.GenericResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WidgetControllerTests {

  final private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mockMvc;

  @Mock
  private WidgetService widgetService;
  @InjectMocks
  private WidgetController widgetController;

  @Test
  public void when_getAllWidgets_expect_allWidgets() throws Exception {
    List<WidgetDTO> widgets = List.of(new WidgetDTO("Thorfinn", "This is thorfinn",BigDecimal.valueOf(2050.23)),
            new WidgetDTO("Thors", "This is thors",BigDecimal.valueOf(1050.23)));
    Page<WidgetDTO> widgetPage = new PageImpl<>(widgets);
    GenericResponse<Page<WidgetDTO>> response = new GenericResponse<>();
    response.setCode(200);
    response.setMessage("Success");
    response.setResult(widgetPage);

    // Mocking the service method
    when(widgetService.getAllWidgets(0,4,false)).thenReturn(response);

    // Calling the controller method
    ResponseEntity<GenericResponse<Page<WidgetDTO>>> responseEntity = widgetController.getAllWidgets(0,4,false);

    // Verifying the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    GenericResponse<Page<WidgetDTO>> responseBody = responseEntity.getBody();
    assertNotNull(responseBody);
    assertEquals(200, responseBody.getCode().intValue());
    assertEquals("Success", responseBody.getMessage());
    assertNotNull(responseBody.getResult());
    assertEquals(2, responseBody.getResult().getTotalElements());
  }
  @Test
  public void when_getWidgets_expect_Error() throws Exception {
    GenericResponse<Page<WidgetDTO>> response = new GenericResponse<>();
    response.setCode(400);
    response.setMessage("Page index must not be less than zero");
    when(widgetService.getAllWidgets(-1,0,false)).thenReturn(response);

    // Calling the controller method
    ResponseEntity<GenericResponse<Page<WidgetDTO>>> responseEntity = widgetController.getAllWidgets(-1,-0,false);

    // Verifying the response
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    GenericResponse<Page<WidgetDTO>> responseBody = responseEntity.getBody();
    assertNotNull(responseBody);
    assertEquals(400, responseBody.getCode().intValue());
    assertEquals("Page index must not be less than zero", responseBody.getMessage());
  }


  @Test
  public void when_addWidget_expect_Success() throws Exception {
    WidgetDTO widget = new WidgetDTO("Thorfinn", "This is thorfinn",BigDecimal.valueOf(202.42));
    GenericResponse response = new GenericResponse();
    response.setCode(201);
    response.setMessage("Widget created succesfully");
    when(widgetService.addWidget(widget)).thenReturn(response);

    // Calling the controller method
    ResponseEntity<GenericResponse> responseEntity = widgetController.addWidget(widget);

    // Verifying the response
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    GenericResponse responseBody = responseEntity.getBody();
    assertNotNull(responseBody);
    assertEquals(201, responseBody.getCode().intValue());
    assertEquals("Widget created succesfully", responseBody.getMessage());
  }
  @Test
  public void when_addWidget_expect_Error_NullValues() throws Exception {
    WidgetDTO widget = new WidgetDTO(null, null,null);
    GenericResponse response = new GenericResponse();
    response.setCode(400);
    response.setMessage("There were errors in the request");
    Map<String, List<String>> errors = new HashMap<>();

    // Add error messages for each field
    errors.put("price", Arrays.asList("must not be null"));
    errors.put("name", Arrays.asList("must not be blank", "must not be null"));
    errors.put("description", Arrays.asList("must not be null", "must not be blank"));
    response.setErrors(errors);
    when(widgetService.addWidget(widget)).thenReturn(response);

    // Calling the controller method
    ResponseEntity<GenericResponse> responseEntity = widgetController.addWidget(widget);

    // Verifying the response
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    GenericResponse responseBody = responseEntity.getBody();
    assertNotNull(responseBody);
    assertEquals(400, responseBody.getCode().intValue());
    assertEquals("There were errors in the request", responseBody.getMessage());

    Map<String, List<String>> errorsExpected = responseBody.getErrors();
    assertTrue(errorsExpected.containsKey("price"));
    assertTrue(errorsExpected.containsKey("name"));
    assertTrue(errorsExpected.containsKey("description"));
    assertEquals(1, errorsExpected.get("price").size());
    assertEquals(2, errorsExpected.get("name").size());
    assertEquals(2, errorsExpected.get("description").size());
    assertTrue(errorsExpected.get("price").contains("must not be null"));
    assertTrue(errorsExpected.get("name").contains("must not be blank"));
    assertTrue(errorsExpected.get("name").contains("must not be null"));
    assertTrue(errorsExpected.get("description").contains("must not be blank"));
    assertTrue(errorsExpected.get("description").contains("must not be null"));
  }
}
