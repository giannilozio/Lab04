package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;

public class StudenteDAO {

	public List<Corso> getCorsi(int matr) {
		final String sql = "SELECT * FROM corso WHERE codins IN (SELECT codins FROM iscrizione WHERE matricola=?)";
		List<Corso> corsi=new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				corsi.add(c);
			}
			rs.close();
			conn.close();
			
			return corsi;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

}
