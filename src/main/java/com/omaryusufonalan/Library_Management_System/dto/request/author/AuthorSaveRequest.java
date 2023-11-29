package com.omaryusufonalan.Library_Management_System.dto.request.author;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    @NotNull(message = "Author name cannot be null")
    @NotEmpty(message = "Author name cannot be empty")
    private String name;

    private LocalDate birthdate;

    @NotNull(message = "Author country cannot be null")
    @NotEmpty(message = "Author country cannot be empty")
    private String country;
}
