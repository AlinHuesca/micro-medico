package com.example.Medicos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Medicos.dao.MedicoDao;
import com.example.Medicos.dto.PacienteDto;
import com.example.Medicos.dto.Respuesta;
import com.example.Medicos.entidad.Medico;

@Service
public class MedicosService {
	final MedicoDao medicoDao;
	final RestTemplate restTemplate;
	
	
	MedicosService(MedicoDao medicoDao, RestTemplate restTemplate){
		this.medicoDao= medicoDao;
		this.restTemplate=restTemplate;	
	}
	
	public ResponseEntity<?>listar(){
		if(medicoDao.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicoDao.findAll());
	}
	
	public Respuesta guardar(Medico medico) {
		if(medicoDao.existsById(medico.getNumero())) {
			return new Respuesta("El medico no se agrego por que su numero", false, medico.getNumero());
		}
		medicoDao.save(medico);
		return new Respuesta("El medico ha sido registrado", true, medico);
	}
	
	public Respuesta editar(Medico medico) {
		if(medicoDao.existsById(medico.getNumero())) {
			medicoDao.save(medico);
			return new Respuesta("El medico ha sido editado", true, medico);
		}
		return new Respuesta("El medico que tratas de ediatr no existe", false, medico.getNumero());
	}
	
	public Respuesta eliminar(long numero) {
		Medico medico = medicoDao.findById(numero).orElse(null);
		if(medico == null) {
			return new Respuesta("El medico que tartas de eliminar no existe", false, numero);
		}
		
		List<PacienteDto>pacientes = restTemplate.getForObject(
				"http://localhost:9001/pacientes/buscarPacientesNumeroMedico/"+numero, List.class);
		if(pacientes != null) {
			return new Respuesta("El medico no se puede eliminar por que tiene pacientes", false, pacientes);
		}
		medicoDao.delete(medico);
		return new Respuesta("El medico ha sido eliminado", true, medico);
	}
	
	public ResponseEntity<?>buscar(long numero){
		Medico medico = medicoDao.findById(numero).orElse(null);
		if(medico == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medico);
	}
	
	public ResponseEntity<?> buscarMedicosPorClinica(int numero){
		List <Medico> medicos = medicoDao.findByNumeroClinica(numero);
		
		if(medicos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicos);
	}
	
	
	//buscar por  paciente
	public ResponseEntity<?> buscarPacientes(long numero){
		List<PacienteDto> pacientes = restTemplate.getForObject(
				"http://localhost:9001/pacientes/buscarPacientesNumeroMedico/"+numero, List.class);
		if(pacientes == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pacientes);
	}
	
}
