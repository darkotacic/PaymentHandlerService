package ftn.sep.tim2.prh.service;

import ftn.sep.tim2.prh.model.Osiguranje;
import ftn.sep.tim2.prh.model.TipUplate;
import ftn.sep.tim2.prh.model.Uplata;

public interface UplataService {
	
	Uplata prepareUplata(Osiguranje o, TipUplate tipUplate);

}
