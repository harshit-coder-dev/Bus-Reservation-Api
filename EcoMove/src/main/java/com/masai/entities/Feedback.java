package com.masai.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedBackId;
    private Integer driverRating;

    @Min(value = 1, message = "Minimum Rating must be 1")
    @Max(value = 5, message = "Maximum Rating must be 5")
    private Integer serviceRating;

    private Integer overallRating;

    private String comments;
    private LocalDateTime feedbackDate;

    @OneToOne
    private User user;

    @OneToOne
    private Bus bus;
}
