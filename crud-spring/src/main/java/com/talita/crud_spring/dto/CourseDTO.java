package com.talita.crud_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Size(min=5, max=100) String name,
        @NotNull @Size(max=10)  String category) {
}
