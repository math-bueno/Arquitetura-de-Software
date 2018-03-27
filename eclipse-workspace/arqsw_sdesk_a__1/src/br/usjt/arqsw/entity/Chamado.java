package br.usjt.arqsw.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * @author MatheusBueno RA:81612420
 * CCP3AN-MCA 
 * Arquitetura de software
 *
 */
@Entity
@Table(name = "chamado")
public class Chamado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ABERTO = "Aberto";
	public static final String FECHADO = "Fechado";
	
	@Id
	@Column(name = "ID_CHAMADO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "A descrição não pode estar vazia.")
	@NotEmpty(message = "A descrição não pode estar vazia.")
	@Size(min = 5, max = 100, message = "A descrição deve ter entre 5 e 100 caracteres.")
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DT_ABERTURA")
	private Date dataAbertura;
	
	@Column(name = "DT_FECHAMENTO")
	private Date dataFechamento;
	
	@Valid
	@JoinColumn(name = "ID_FILA", referencedColumnName = "ID_FILA")
    @ManyToOne
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