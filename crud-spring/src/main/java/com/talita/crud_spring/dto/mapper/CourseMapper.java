package com.talita.crud_spring.dto.mapper;

import com.talita.crud_spring.dto.CourseDTO;
import com.talita.crud_spring.dto.LessonDTO;
import com.talita.crud_spring.enums.Category;
import com.talita.crud_spring.model.Course;
import com.talita.crud_spring.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null){
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(),
                        lesson.getYoutubeUrl()))
                .toList();

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).toList();
        course.setLessons(lessons);
        return course;
    }

    public Category convertCategoryValue(String value){
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid category: " + value);
        };
    }


}
