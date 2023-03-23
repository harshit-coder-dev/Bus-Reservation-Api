package com.masai.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAdminSession {

    @Id
    @Column(unique = true)
    private Integer adminId;

    private String uuid;

    private LocalDateTime dateTime;
}
