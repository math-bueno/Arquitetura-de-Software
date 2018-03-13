package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 
 * @author MatheusBueno
 *
 *CCP3AN-MCA
 */

public class Chamado implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String ABERTO = "Aberto";
	public static final String FECHADO = "Fechado";
	
	private Integer id;
	
	@NotNull(message = "A descrição não pode estar vazia.")
	@NotEmpty(message = "A descrição não pode estar vazia.")
	@Size(min = 5, max = 100, message = "A descrição deve ter entre 5 e 100 caracteres.")
	private String descricao;
	private String status;
	private Date dataAbertura;
	private Date dataFechamento;
	
	@Valid
	private Fila fila;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Fila getFila() {
		return fila;
	}
	public void setFila(Fila fila) {
		this.fila = fila;
	}
}
