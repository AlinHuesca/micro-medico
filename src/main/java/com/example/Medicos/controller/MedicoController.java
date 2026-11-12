package com.example.Medicos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Medicos.dto.Respuesta;
import com.example.Medicos.entidad.Medico;
import com.example.Medicos.service.MedicosService;

@RestController
@RequestMapping("medicos")
@CrossOrigin
public class MedicoController {
	
	final MedicosService medicosService;
	
	MedicoController(MedicosService medicosService){
		this.medicosService=medicosService;
	}
	
	@GetMapping("listar")
	public ResponseEntity<?>listar(){
		return medicosService.listar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Medico medico) {
		return medicosService.guardar(medico);
	}
	
	@PostMapping("editar")
	public Respuesta editar(@RequestBody Medico medico) {
		return medicosService.editar(medico);
	}
	
	@GetMapping("eliminar/{numero}")
	public Respuesta eliminar(@PathVariable("numero") long numero) {
		return medicosService.eliminar(numero);
	}
	
	@GetMapping("buscar/{numero}")
	public ResponseEntity<?> buscar(@PathVariable("numero")long numero){
		return medicosService.buscar(numero);
	}
	
	@GetMapping("buscarMedicos/{numero}")
	public ResponseEntity<?>buscar(@PathVariable("numero") int numero){
		return medicosService.buscarMedicosPorClinica(numero);
	}
	
	@GetMapping("buscarPacientes/{numero}")
	public ResponseEntity<?>buscarPaciente(@PathVariable("numero") long numero){
		return medicosService.buscarPacientes(numero);
	}

}
