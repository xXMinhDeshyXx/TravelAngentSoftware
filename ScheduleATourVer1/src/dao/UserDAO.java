package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;


public class UserDAO extends DAO{
	public UserDAO() {
		super();
	}
	public boolean checkLogin(User user) {
		boolean ok = false;
		String sql = "SELECT fullname, role, ID FROM tblUser WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setName(rs.getString("fullname"));
				user.setRole(rs.getString("role"));
				user.setId(rs.getInt("ID"));
				ok = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
}
