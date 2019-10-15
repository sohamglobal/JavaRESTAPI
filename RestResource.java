package restapis;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/bankapi")
public class RestResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccounts()
	{
		
		Account obj;
		Connection con;
		Statement st;
		ResultSet rs;
		List<Account> lst=new ArrayList<Account>();		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sohamdb?user=root&password=volkswagen");
			
			st=con.createStatement();
			rs=st.executeQuery("select * from accounts;");
			
			
			while(rs.next())
			{
				obj=new Account();
				obj.setAccno(rs.getInt("accno"));
				obj.setAccnm(rs.getString("accnm"));
				obj.setAcctype(rs.getString("acctype"));
				obj.setBalance(rs.getDouble("balance"));
				lst.add(obj);
				
			}
			
			con.close();
		}
		catch(Exception e)
		{
			
		}
	
	return(lst);	
	}

	
	@GET
	@Path("/account/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("id") int id)
	{
		System.out.println(id);
		Account obj=new Account();
		Connection con;
		Statement st;
		ResultSet rs;
				
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sohamdb?user=root&password=volkswagen");
			
			st=con.createStatement();
			rs=st.executeQuery("select * from accounts where accno="+id+";");
			
			
			if(rs.next())
			{
				
				obj.setAccno(rs.getInt("accno"));
				obj.setAccnm(rs.getString("accnm"));
				obj.setAcctype(rs.getString("acctype"));
				obj.setBalance(rs.getDouble("balance"));
				
				
			}
			else
			{
				obj.setAccno(0);
				obj.setAccnm("NA");
				obj.setAcctype("NA");
				obj.setBalance(0.00);
			}
			
			
			con.close();
		}
		catch(Exception e)
		{
			
		}
		
		
		
		return(obj);	
	}

	
}
