package com.talita.crud_spring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talita.crud_spring.domain.enums.Category;
import com.talita.crud_spring.shared.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Size(min=4, max=100) String name,
        @NotNull @Size(max=10)  @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
