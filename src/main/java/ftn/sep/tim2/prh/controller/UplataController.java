package ftn.sep.tim2.prh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.sep.tim2.prh.config.DatabaseUri;
import ftn.sep.tim2.prh.model.Osiguranje;
import ftn.sep.tim2.prh.model.Uplata;
import ftn.sep.tim2.prh.service.UplataService;

@RestController
@RequestMapping("/payment")
public class UplataController {
	
	private final RestTemplate restTemplate;
	private final UplataService uplataService;
	private final DatabaseUri databaseUri;
	
	@Autowired
	public UplataController(RestTemplate restTemplate,UplataService uplataService,DatabaseUri databaseUri){
		this.uplataService = uplataService;
		this.databaseUri = databaseUri;
		this.restTemplate = restTemplate;
	}
	
	@PostMapping
	@ResponseBody
	public String createUplata(@RequestBody Osiguranje osiguranje) {
		Uplata uplata = uplataService.prepareUplata(osiguranje);
		//slanje bazi na dodavanje radi dodele paymentID
		uplata = restTemplate.postForObject(databaseUri.getDatabaseUri()+"/uplate/"+osiguranje.getId(), uplata, Uplata.class);
		//slanje pcc na dalje placanje
	    return restTemplate.postForObject(databaseUri.getPccUri()+"/pay", uplata, String.class);
	}
	
	@PostMapping(value="/cancel")
	@ResponseBody
	public void cancelUplata(@RequestBody Long uplataId) {
		
	}
	
	@PostMapping(value="/success")
	@ResponseBody
	public void successUplata(@RequestBody Long uplataId) {
	
	}
	
	@PostMapping(value="/error")
	@ResponseBody
	public void errorUplata(@RequestBody Long uplataId) {
	
	}
	
}
