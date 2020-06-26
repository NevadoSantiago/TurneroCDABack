package com.cda.turnero.services;

import org.springframework.stereotype.Service;

@Service
public class CodigoService {

	
	//deberia devolver un objeto que tenga el codigo de seguimiento y el codigoQR
		public String codigoQRSeguimiento() {

			return "Este metodo devuelve el codigo de seguimiento y el codigo QR";
		}
}