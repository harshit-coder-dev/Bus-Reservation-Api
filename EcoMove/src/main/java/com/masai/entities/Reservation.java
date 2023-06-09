package com.masai.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;
	
	private String reservationStatus;

	private String reservationType;
	
	private LocalDate reservationDate;
	
	private LocalTime reservationTime;
	
	private String source;
	
	private String destination;
	
	private LocalDate journeyDate; // self added field
	
	private Integer noOfSeatsBooked; // self added field
	
	private Integer fare; // self added field

	@ManyToOne
	private Bus bus;

	@ManyToOne
	private User user;
}
