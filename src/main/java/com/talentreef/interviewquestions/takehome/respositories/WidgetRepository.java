package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.entities.Widget;
import com.talentreef.interviewquestions.takehome.models.projections.WidgetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {

    //We can make the findByName with these two approaches
    Widget findByName(String name);

    @Query("SELECT w.name AS name, w.description AS description, w.price AS price FROM Widget w WHERE w.name = :name")
    WidgetProjection findByNameWithProjection(String name);
}
