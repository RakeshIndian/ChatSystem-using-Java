package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MyDB;
import domain.Group;

public class GroupDAO {
    public void createGroup(Group group) throws ClassNotFoundException, SQLException{
        Connection con = MyDB.getCon();
        Statement st = con.createStatement();
        String sql_query = "insert into chatgroup(gName,creator,gIp) values('"+
        group.getgName()+"','"+group.getgCreator()+"','"+group.getgIp()+"')";
        st.executeUpdate(sql_query);
        con.close();
    }
    public Group fetchLatestGroupByUser(String cName) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatgroup where creator='"+cName+"' ORDER BY gIp DESC ");
		Group user = new Group();
    	if(rs.next()){
    		user.setgName(rs.getString(1));
    		user.setgCreator(rs.getString(2));
    		user.setgIp(rs.getString(3));
		}
    	con.close();
    	return user;
    }
    
    public List<Group> getGroupByName(String name) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatgroup where gName='"+name+"'");
    	List<Group> groupList = new ArrayList<Group>();
    	while(rs.next()){
			groupList.add(new Group(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
    	con.close();
    	return groupList;
    }
    
    public List<Group> getGroupByCreator(String creator) throws SQLException, ClassNotFoundException {
    	Connection con = MyDB.getCon();
        Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from chatgroup where creator='"+creator+"'");
    	List<Group> groupList = new ArrayList<Group>();
    	while(rs.next()){
			groupList.add(new Group(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
    	con.close();
    	return groupList;
    }
}
