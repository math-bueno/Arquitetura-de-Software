package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author MatheusBueno RA:81612420 
 * 
 * CCP3AN-MCA
 *  Arquitetura de software
 *
 */


public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message="O campo usu�rio n�o pode estar vazio.")
	@NotEmpty(message="O campo usu�rio n�o pode estar vazio.")
	private String username;

	@NotNull(message="O campo senha n�o pode estar vazio.")
	@NotEmpty(message="O campo senha n�o pode estar vazio.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
