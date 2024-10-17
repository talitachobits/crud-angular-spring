package com.talita.crud_spring.controllers;

import com.talita.crud_spring.domain.dto.CourseDTO;
import com.talita.crud_spring.domain.dto.CoursePageDTO;
import com.talita.crud_spring.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses Controller", description = "RESTful API for managing courses and your lessons.")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieve a list of all courses")
    public CoursePageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                              @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize){
        return courseService.list(page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID", description = "Retrieve a specific course")
    public CourseDTO findById(@PathVariable() @NotNull @Positive Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus( code = HttpStatus.CREATED)
    @Operation(summary = "Create a new course", description = "Create a new course and return the created course's data")
    public CourseDTO create(@RequestBody @Valid CourseDTO course){
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a course", description = "Update the data of an existing course based on its ID")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id,
                            @RequestBody @Valid CourseDTO course){

        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a course", description = "Delete an existing course based on its ID")
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);
    }
}