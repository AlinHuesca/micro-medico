package com.example.Medicos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Medicos.entidad.Medico;

@Repository
public interface MedicoDao extends JpaRepository<Medico, Long>  {

	List<Medico> findByNumeroClinica(int numeroClinica);
}
