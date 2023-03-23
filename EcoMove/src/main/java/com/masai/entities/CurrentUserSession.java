package com.masai.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	@Column(unique = true)
	private Integer userId;

	private String uuid;

	private LocalDateTime dateTime;
}
