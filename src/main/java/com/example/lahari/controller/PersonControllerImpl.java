package com.example.lahari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lahari.dto.PersonDto;

import com.example.lahari.service.PersonService;

@RestController
@RequestMapping("/api/v1/persons") 
public class PersonControllerImpl implements PersonController{
	 private final PersonService personService;
	 	@Autowired
	    public PersonControllerImpl(PersonService personService) {
	        this.personService = personService;
	    }


	@Override
	@PostMapping
	public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
		PersonDto addPerson = personService.createPerson(personDto);
		 return new ResponseEntity<>(addPerson, HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long personId){
		PersonDto personDto = personService.getPersonById(personId);
		return ResponseEntity.ok(personDto);
	}
	

	@Override
	@PutMapping("/{personId}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long personId, @RequestBody PersonDto updatedPerson){
		PersonDto personDto = personService.updatePerson(personId, updatedPerson);
		return ResponseEntity.ok(personDto);
	}
    
	@Override
	@GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons(){
		List<PersonDto> persons = personService.getAllPersons();
		return ResponseEntity.ok(persons);
	}

	@Override
	@GetMapping("/search")
    public ResponseEntity<List<PersonDto>> searchPersons(@RequestParam String searchType, @RequestParam String searchValue){
		List<PersonDto> persons = personService.searchPersons(searchType, searchValue);
		return ResponseEntity.ok(persons);
	}
}
