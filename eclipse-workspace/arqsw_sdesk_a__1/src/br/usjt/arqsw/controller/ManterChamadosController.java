package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author MatheusBueno RA:81612420
 * CCP3AN-MCA 
 * Arquitetura de software
 *
 */
@Controller
@Transactional
public class ManterChamadosController {
	private FilaService filaService;

	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	/**
	 * 
	 * @param model
	 *            Acesso à request http
	 * @return JSP de Listar Chamados
	 */
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
			}
			fila = filaService.carregar(fila.getId());
			List<Chamado> chamados = chamadoService.listarChamadoPorFila(fila);

			model.addAttribute("fila", fila);
			model.addAttribute("chamados", chamados);

			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/criar_chamado")
	public String criarChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoCriar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/salvar")
	public String salvarChamado(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoCriar";
			}
			Chamado chamadoCriado = chamadoService.salvar(chamado);
			model.addAttribute("chamado", chamadoCriado);

			return "ChamadoSalvo";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}