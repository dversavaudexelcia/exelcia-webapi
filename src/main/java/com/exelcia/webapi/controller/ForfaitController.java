package com.exelcia.webapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exelcia.webapi.exceptions.ResourceNotFoundException;
import com.exelcia.webapi.model.Forfait;
import com.exelcia.webapi.repository.ForfaitRepository;

@RestController
@RequestMapping("/api")
public class ForfaitController {
	
	@Autowired
	ForfaitRepository repo;
	
	//@RequestMapping(value="v2/forfaits", method = RequestMethod.GET, )
	//@GetMapping(value = "/forfaits", params = "1")
	//@GetMapping(value = "/forfaits", headers = "X-API-VERSION=1")
	@GetMapping(value = "/forfaits")
	public List<Forfait> getAllForfait(){
		return repo.findAll();
	}
	
	@GetMapping("/forfaits/{id}")
	public Forfait getForfaitById(@PathVariable(value="id")Long forfaitId) {
		
		return repo.findById(forfaitId).orElseThrow(
				() -> new ResourceNotFoundException("Forfait", "id", forfaitId));
	}

}
