package classic.web.app;

import java.sql.*;
import java.util.*;

public class CustomerBean implements java.io.Serializable {

	private String id;
	private String password;
	private static String did;

	public final String getId() { return id; }
	public final void setId(String value) { id = value; }

	public final String getPassword() { return password; }
	public final void setPassword(String value) { password = value; }

	public final String getDid() { return did; }
	public final void setDid(String value) { did = value; }

	public boolean authenticate() throws SQLException {
		try(var con = DB.connect()){
			var pstmt = con.prepareStatement("select count(id) from admin where id=? and pwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			var rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			pstmt.close();
			if(count == 1)
				return true;
			id = password = null;
			return false;
		}
	}

	public List<DeptEntry> getDept() throws SQLException {
		var dept = new ArrayList<DeptEntry>();
		try(var con = DB.connect()){
			var pstmt = con.prepareStatement("select did, dname,loc from dept");
			var rs = pstmt.executeQuery();
			while(rs.next())
				dept.add(new DeptEntry(rs));
			rs.close();
			pstmt.close();
		}
		return dept;
	}


	public static class DeptEntry {
		
		//private String did;
		private String dname;
		private String loc;

		DeptEntry(ResultSet rs) throws SQLException {
			did = rs.getString("did");
			dname = rs.getString("dname");
			loc = rs.getString("loc");
		}

		public final String getDid() { return did; }
		public final void setDid( String a) {  did = a; }

		public final String getDname() { return dname; }

		public final String getLoc() { return loc; }
	}

	public List<EmpEntry> getEmp() throws SQLException {
		var emp = new ArrayList<EmpEntry>();
		try(var con = DB.connect()){
			var pstmt = con.prepareStatement("select eid, ename,sal, age, did from emp where did=?");
			pstmt.setString(1, did);
			var rs = pstmt.executeQuery();
			while(rs.next())
				emp.add(new EmpEntry(rs));
			rs.close();
			pstmt.close();
		}
		return emp;
	}

	public static class EmpEntry {
		
		private String eid;
		private String ename;
		private int sal;
		private int age;
		private String did;

      	EmpEntry(ResultSet rs) throws SQLException {
			eid = rs.getString("eid");
			ename = rs.getString("ename");
			sal = rs.getInt("sal");
			age = rs.getInt("age");
			did = rs.getString("did");
		}

		public final String getEid() { return eid; }

		public final String getEname() { return ename; }

		public final int getSal() { return sal; }
		
		public final int getAge() { return age; }
		
		public final String getDid() {return did ;}
		
	}
}
