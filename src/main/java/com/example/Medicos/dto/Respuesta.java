package com.example.Medicos.dto;

import com.example.Medicos.entidad.Medico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
	private String mensaje;
	private boolean sucess;
	private Object obj;

}
