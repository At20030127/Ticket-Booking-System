package com.example.ticket_booking_system.dto;

import java.time.LocalDate;

import com.example.ticket_booking_system.enums.MovieStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {


	private String title;
	private String description;
	private String genre;
	private String language;
	private Integer duration;
	private LocalDate releaseDate;
	private String certificate;
	private MovieStatus status;
}
