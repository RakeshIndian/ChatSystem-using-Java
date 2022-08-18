package service;

import java.sql.SQLException;
import java.util.List;

import domain.Group;

public class GroupService {
	private static GroupDAO gDao = new GroupDAO();
	public static Group createGroup(String gName,String cName,String ip) throws ClassNotFoundException, SQLException {
		Group g = gDao.fetchLatestGroupByUser(cName);
		String tempIp = "";
		if(g.getgName()!=null) {
			System.out.println(g.getgIp());
			String[] ipGroup = g.getgIp().split(".");
			for(int i=3;i>=2;i--) {
				if(ipGroup[i]!="255") {
					ipGroup[i] = ""+(Integer.parseInt(ipGroup[i])+1);
					break;
				}	
			}
			tempIp = ipGroup[0]+"."+ipGroup[1]+"."+ipGroup[2]+"."+ipGroup[3];
		}
		else {
			System.out.println(ip);
			tempIp = "232."+ ip.substring(ip.lastIndexOf('.')+1)+"."+"0.0";
		}
		Group newGroup = new Group(gName,cName,tempIp);
		gDao.createGroup(newGroup);
		return newGroup;	
	}
	public static List<Group> getGroupByName(String name) throws ClassNotFoundException, SQLException{
		return gDao.getGroupByName(name);
	}
	public static List<Group> getGroupByCreator(String creator) throws ClassNotFoundException, SQLException{
		return gDao.getGroupByCreator(creator);
	}
}
