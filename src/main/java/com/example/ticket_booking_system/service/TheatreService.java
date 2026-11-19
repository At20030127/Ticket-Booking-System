package com.example.ticket_booking_system.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.ticket_booking_system.dto.TheatreRequestDTO;
import com.example.ticket_booking_system.dto.TheatreResponseDTO;
import com.example.ticket_booking_system.entity.Theatre;
import com.example.ticket_booking_system.repository.TheatreRepository;

public class TheatreService {

	private final ModelMapper modelMapper ;
	private final TheatreRepository theatreRepository;
	
	public TheatreService(TheatreRepository theatreRepository,ModelMapper modelMapper) {
		
		this.modelMapper = modelMapper;
		this.theatreRepository = theatreRepository;
	}

	
	public TheatreResponseDTO addTheatre(
			TheatreRequestDTO requestDTO) {
		Theatre theatre=modelMapper.map(requestDTO,Theatre.class);
		Theatre savedTheatre=theatreRepository.save(theatre);
				return modelMapper.map(savedTheatre,TheatreResponseDTO.class);
				
	}
	
	public List<TheatreResponseDTO> getAllTheatres(){
		List<Theatre>theatres=theatreRepository.findAll();
		List<TheatreResponseDTO> responseDTOs=new ArrayList<>();
		for(Theatre theatre : theatres) {
			responseDTOs.add(modelMapper.map(theatre,TheatreResponseDTO.class));
			
		}
		return responseDTOs;
	}

}
