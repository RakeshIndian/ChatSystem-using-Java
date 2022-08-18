package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MyDB;
import domain.User;

public class UserDAO {
    public void insertUser(User user) throws ClassNotFoundException, SQLException{
        Connection con = MyDB.getCon();
        Statement st = con.createStatement();
        String sql_query = "insert into chatappuser(name,password,email,phone,ip) values('"+
        user.getName()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getPhone()+"','"+user.getIp()+"')";
        st.executeUpdate(sql_query);
        con.close();
    }
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatappuser where email='"+email+"'");
		User user = new User();
    	while(rs.next()){
    		user.setName(rs.getString("name"));
    		user.setPassword(rs.getString("password"));
    		user.setEmail(rs.getString("email"));
    		user.setPhone(Long.parseLong(rs.getString("phone")));
    		user.setIp(rs.getString("ip"));
//			user.add(new Airport(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
    	con.close();
    	return user;
    }
    
    public List<User> getUserByName(String name) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatappuser where name='"+name+"'");
    	List<User> userList = new ArrayList<User>();
    	while(rs.next()){
			userList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),Long.parseLong(rs.getString(4)),rs.getString(5)));
		}
    	con.close();
    	return userList;
    }
    
    public List<User> getUserByPhone(Long phone) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatappuser where phone="+phone);
    	List<User> userList = new ArrayList<User>();
    	while(rs.next()){
			userList.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),Long.parseLong(rs.getString(4)),rs.getString(5)));
		}
    	con.close();
    	return userList;
    }
}
