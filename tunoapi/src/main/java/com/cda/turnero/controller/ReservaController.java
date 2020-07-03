package com.cda.turnero.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.service.ReservaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import com.services.services.service.TurnoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

	@Autowired
	ReservaService reservaService;

	@PostMapping("/crear/{idUsuario}")
	public ResponseEntity<?> crearReserva(@RequestBody String descReserva,
			@PathVariable("idUsuario") Integer idUsuario) {
		JsonElement json = new JsonParser().parse(descReserva);
		JsonObject jobject = json.getAsJsonObject();
		System.out.println(jobject + " " +  idUsuario);
		return new ResponseEntity<>(reservaService.crearReserva(jobject, idUsuario), HttpStatus.OK);
	}

}
