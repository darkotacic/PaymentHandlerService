package ftn.sep.tim2.prh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.sep.tim2.prh.model.Osiguranje;
import ftn.sep.tim2.prh.model.Uplata;
import ftn.sep.tim2.prh.service.UplataService;

@RestController
@RequestMapping("/payment/")
public class UplataController {
	
	private final UplataService uplataService;
	
	@Autowired
	public UplataController(UplataService uplataService){
		this.uplataService = uplataService;
	}
	
	@PostMapping
	@ResponseBody
	public Uplata createUplata(@RequestBody Osiguranje osiguranje) {
		Uplata uplata = uplataService.prepareUplata(osiguranje);
		// salji to na bazi da se doda;
		return null;
	}
	
}
