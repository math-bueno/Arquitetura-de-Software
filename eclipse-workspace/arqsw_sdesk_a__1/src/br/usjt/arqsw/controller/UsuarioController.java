package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author MatheusBueno RA:81612420 
 * 
 * CCP3AN-MCA
 *  Arquitetura de software
 *
 */
@Controller
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logar(@Valid Usuario usuario, BindingResult result, Model model, HttpServletRequest request) {
		try {
			if (result.hasErrors()) {
				System.out.println("Deu erro " + result.toString());
				return "login";
			}
			Usuario autenticado = usuarioService.logar(usuario);
			if (autenticado == null) {
				model.addAttribute("msg", "Usuário ou senha inválidos.");
				return "login";
			}
			request.getSession().setAttribute("usuarioLogado", autenticado);

			return "redirect:index";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
}
