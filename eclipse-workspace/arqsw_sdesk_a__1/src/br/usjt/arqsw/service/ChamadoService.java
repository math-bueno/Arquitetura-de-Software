package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;


/**
 * 
 * @author MatheusBueno
 *
 *CCP3AN-MCA
 */
public class ChamadoService {

	private ChamadoDAO chamadoDAO;

	public ChamadoService() {
		chamadoDAO = new ChamadoDAO();
	}
	/**
	 * Método para encontrar chamados pela Fila
	 * @param fila Fila para encontrar chamados relacionados a ela
	 * @return Lista de chamados
	 * @throws IOException
	 */
	public List<Chamado> listarChamadoPorFila(Fila fila) throws IOException {
		return chamadoDAO.listarPorIdFila(fila.getId());
	}
	/**
	 * Método para salvar um chamado
	 * @param chamado Chamado que se deseja salvar
	 * @return Chamado que foi salvo
	 * @throws IOException
	 */
	public Chamado salvar(Chamado chamado) throws IOException {
		chamado.setDataAbertura(new Date());
		chamado.setStatus(Chamado.ABERTO);
		
		Chamado chamadoSalvo = chamadoDAO.criar(chamado);
		
		return chamadoSalvo;
	}

}
