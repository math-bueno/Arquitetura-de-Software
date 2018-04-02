package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author MatheusBueno RA:81612420
 * CCP3AN-MCA 
 * Arquitetura de software
 *
 */
public class ChamadoDAO {

	public List<Chamado> listarPorIdFila(int id) throws IOException {
		String query = "select * from chamado where ID_FILA = ?";
		ArrayList<Chamado> lista = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Chamado c = new Chamado();
				Fila f = new Fila();
				c.setId(rs.getInt("id_chamado"));
				c.setDescricao(rs.getString("descricao"));
				c.setStatus(rs.getString("status"));
				c.setDataAbertura(new java.util.Date(rs.getDate("dt_abertura").getTime()));
				c.setDataFechamento(rs.getDate("dt_fechamento") != null
						? new java.util.Date(rs.getDate("dt_fechamento").getTime()) : null);
				f.setId(rs.getInt("id_fila"));
				c.setFila(f);

				lista.add(c);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

	public Chamado criar(Chamado chamado) throws IOException {
		String query = "insert into chamado (descricao, status, dt_abertura, id_fila) values(?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setString(1, chamado.getDescricao());
			pst.setString(2, chamado.getStatus());
			pst.setDate(3, new Date(chamado.getDataAbertura().getTime()));
			pst.setInt(4, chamado.getFila().getId());

			pst.execute();

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return obterMaxId();
	}

	private Chamado obterMaxId() throws IOException {
		String query = "SELECT * FROM servicedesk.chamado order by id_chamado desc limit 1";
		Chamado chamado = null;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();) {

			if (rs.next()) {
				chamado = new Chamado();
				Fila f = new Fila();
				chamado.setId(rs.getInt("id_chamado"));
				chamado.setDescricao(rs.getString("descricao"));
				chamado.setStatus(rs.getString("status"));
				chamado.setDataAbertura(new java.util.Date(rs.getDate("dt_abertura").getTime()));
				chamado.setDataFechamento(rs.getDate("dt_fechamento") != null
						? new java.util.Date(rs.getDate("dt_fechamento").getTime()) : null);
				f.setId(rs.getInt("id_fila"));
				chamado.setFila(f);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return chamado;
	}

}
