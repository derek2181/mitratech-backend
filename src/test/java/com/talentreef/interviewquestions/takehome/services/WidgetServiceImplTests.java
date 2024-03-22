package com.talentreef.interviewquestions.takehome.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.talentreef.interviewquestions.takehome.models.dto.WidgetDTO;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class WidgetServiceImplTests {

  @Mock
  private WidgetRepository widgetRepository;

  @InjectMocks
  private WidgetServiceImpl widgetServiceImpl;

  @InjectMocks
  private ModelMapper modelMapper;

  @Test
  public void when_getAllWidgets_expect_findAllResult() throws Exception {
    WidgetDTO widget = WidgetDTO.builder().name("Widgette Nielson").build();
    List<WidgetDTO> response = List.of(widget);
    List<WidgetDTO> responseRepository=widgetRepository.findAll().stream()
            .map(entity -> modelMapper.map(entity, WidgetDTO.class))
            .collect(Collectors.toList());
    when(responseRepository).thenReturn(response);

    List<WidgetDTO> result = widgetServiceImpl.getAllWidgets().result;

    assertThat(result).isEqualTo(response);
  }

}
