package com.talita.crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LessonDTO(
        Long id,
        @NotNull @NotBlank @Size(max=100) String name,
        @NotNull @Size(max=50) String youtubeUrl) {

}
