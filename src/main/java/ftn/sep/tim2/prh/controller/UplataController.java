package ftn.sep.tim2.prh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ftn.sep.tim2.prh.config.DatabaseUri;
import ftn.sep.tim2.prh.model.Osiguranje;
import ftn.sep.tim2.prh.model.TipUplate;
import ftn.sep.tim2.prh.model.Uplata;
import ftn.sep.tim2.prh.service.UplataService;

@RestController
@RequestMapping("/payment")
public class UplataController {
	
	private final UplataService uplataService;
	private final RestTemplate restTemplate;
	private final DatabaseUri databaseUri;
	
	@Autowired
	public UplataController(RestTemplate restTemplate,UplataService uplataService,DatabaseUri databaseUri){
		this.uplataService = uplataService;
		this.databaseUri = databaseUri;
		this.restTemplate = restTemplate;
	}
	
	@PostMapping("/{tipUplate}")
	@ResponseBody
	public String createUplata(@RequestBody Osiguranje osiguranje, @PathVariable("tipUplate") TipUplate tipUplate) {
		Uplata uplata = uplataService.prepareUplata(osiguranje, tipUplate);
		//slanje bazi na dodavanje radi dodele paymentID
		uplata = restTemplate.postForObject(databaseUri.getDatabaseUri()+"/uplate/"+osiguranje.getId(), uplata, Uplata.class);
		//slanje pcc na dalje placanje
	    return restTemplate.postForObject(databaseUri.getPccUri()+"/pay", uplata, String.class);
	}
	
	@PostMapping("/cancel")
	@ResponseBody
	public void cancelUplata(@RequestBody Long uplataId) {
		restTemplate.postForObject(databaseUri.getDatabaseUri() + "/uplate/cancel", uplataId, Void.class);
	}
	
	@PostMapping("/success")
	@ResponseBody
	public void successUplata(@RequestBody Long uplataId) {
		Uplata uplata = restTemplate.postForObject(databaseUri.getDatabaseUri() + "/uplate/success", uplataId, Uplata.class);
		restTemplate.postForObject(databaseUri.getPcmUri() + "/notification/notifyParties", uplata, Void.class);
	}
	
	@PostMapping("/error")
	@ResponseBody
	public void errorUplata(@RequestBody Long uplataId) {
		restTemplate.postForObject(databaseUri.getDatabaseUri() + "/uplate/error", uplataId, Void.class);
	}
	
}
