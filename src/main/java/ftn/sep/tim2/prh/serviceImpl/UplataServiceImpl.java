package ftn.sep.tim2.prh.serviceImpl;

import org.springframework.stereotype.Service;

import ftn.sep.tim2.prh.model.Osiguranje;
import ftn.sep.tim2.prh.model.Uplata;
import ftn.sep.tim2.prh.service.UplataService;

@Service
public class UplataServiceImpl implements UplataService {

	@Override
	public Uplata prepareUplata(Osiguranje o) {
		Uplata u = new Uplata();
		u.setDatumUplate(o.getDatumSklapanja());
		u.setIznos(o.getIznos());
		u.setOsiguranje(o);
		u.setNacinPlacanja("PAYPAL");
		//u.setLozinkaTrgovca();
		//u.setTrgovacId();
		return u;
	}

}
