package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author MatheusBueno RA:81612420 
 * 
 * CCP3AN-MCA
 *  Arquitetura de software
 *
 */
@Repository
public class UsuarioDAO {

	private Connection conn;

	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Método que obtem um usuário pelo username
	 * 
	 * @param userName
	 * @return Usuario
	 * @throws IOException
	 */
	public Usuario obterPorUsuario(String userName) throws IOException {
		String query = "select * from USUARIO where USERNAME = ?";
		Usuario usuario = null;
		try (PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setString(1, userName);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setUsername(rs.getString("USERNAME"));
				usuario.setPassword(rs.getString("PASSWORD"));
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return usuario;
	}
}
