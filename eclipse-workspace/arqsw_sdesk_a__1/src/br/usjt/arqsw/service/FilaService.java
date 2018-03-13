package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author MatheusBueno RA:81612420
 *
 *CCP3AN-MCA
 */
public class FilaService {
	private FilaDAO dao;

	public FilaService() {
		dao = new FilaDAO();
	}

	public ArrayList<Fila> listarFilas() throws IOException {
		return dao.listarFilas();
	}

	public Fila carregar(int id) throws IOException {
		return dao.obterPorId(id);
	}
}
