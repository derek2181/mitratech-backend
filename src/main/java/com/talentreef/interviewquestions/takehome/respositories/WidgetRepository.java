package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.entities.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {


}
