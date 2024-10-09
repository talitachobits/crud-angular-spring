package com.talita.crud_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.talita.crud_spring.enums.Category;
import com.talita.crud_spring.enums.Status;
import com.talita.crud_spring.enums.converts.CategoryConverter;
import com.talita.crud_spring.enums.converts.StatusCoverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@SQLRestriction("status <> 'Inativo'")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;


    @NotBlank
    @NotNull
    @Size(min=5, max=100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusCoverter.class)
    private Status status = Status.ACTIVE;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
