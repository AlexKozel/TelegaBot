package com.example.JavaBot.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class CapitalsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @NotNull
    @NonNull
    @Size(min = 2, message = "the capital's name has to be at least 2 symbols")
    private String name;

    @NotNull
    @NonNull
    @Size(min=20,message = "the description has to be at least 20 symbols")
    private String description;
}
