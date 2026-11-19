package com.example.ticket_booking_system.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ticket_booking_system.dto.MovieRequestDTO;
import com.example.ticket_booking_system.dto.MovieResponseDTO;
import com.example.ticket_booking_system.entity.Movie;
import com.example.ticket_booking_system.enums.MovieStatus;
import com.example.ticket_booking_system.exception.ResourceNotFoundException;
import com.example.ticket_booking_system.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository  movieRepository; 
	
	private final ModelMapper modelMapper;
	
	public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
		Movie  movie =modelMapper.map(movieRequestDTO, Movie.class);
		movie.setStatus(MovieStatus.ACTIVE);
		Movie savedMovie=movieRepository.save(movie);
		return modelMapper.map(savedMovie,MovieResponseDTO.class);
	}
	
	
	public List<MovieResponseDTO> getAllMovies(){
		List<Movie>movies=movieRepository.findAll();
//		List<MovieResponseDTO> movieResponseDTO = new ArrayList<MovieResponseDTO>();
//		for(Movie movie :movies) {
//			movieResponseDTO.add(modelMapper.map(movie,MovieResponseDTO.class));
//		}
		
		return movies.stream()
				.map(movie ->modelMapper.map(movie, MovieResponseDTO.class)).toList();	
	}
	
	public MovieResponseDTO getMovieById(Long id) {
		Movie movie=movieRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						"Movie id"+id+"is not found"));
		return  modelMapper.map(movie, MovieResponseDTO.class);
	}
	
	
	
	
	public MovieResponseDTO updateMovieById(Long id,MovieRequestDTO movieRequestDTO) {
		Movie movie = movieRepository.findById(id).orElseThrow(
				() ->new ResourceNotFoundException(
				"Movie id "+id+"is not Found"));
//		movie.setDescription(movieRequestDTO.getDescription());
//		movie.setCertificate(movieRequestDTO.getCertificate());
//		movie.setDuration(movieRequestDTO.getDuration());
//		movie.setGenre(movieRequestDTO.getGenre());
//		movie.setLanguage(movieRequestDTO.getLanguage());
//		movie.setReleaseDate(movieRequestDTO.getReleaseDate());
//		movie.setTitle(movieRequestDTO.getTitle());
		
		modelMapper.map(movieRequestDTO,movie);
		
		Movie updatedMovie = movieRepository.save(movie);	
		return modelMapper.map(updatedMovie, MovieResponseDTO.class);
	
	}
}
