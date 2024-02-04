package APIUtilities;

import java.lang.reflect.Field;
import java.util.Random;

public class Utilities {
	
   private static String UserID="";
	
	public static String getUserJson(String name,String job)
	{
		return "{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"job\": \""+job+"\"\r\n"
				+ "}";
	}
	
	public static String getRegisterJson(String email,String pwd)
	{
		if(pwd.equals(""))
			return "{\r\n"
					+ "    \"email\": \""+email+"\"\r\n"
					+ "}";
		
		return "{\r\n"
				+ "    \"email\": \""+email+"\",\r\n"
				+ "    \"password\": \""+pwd+"\"\r\n"
				+ "}";
	}
	
	
	public static void updateVariables(String id)
	{
		UserID=id;
	}
	
	
	public static String getupdatedValues(String variableName)
	{
		Utilities util=new Utilities();
		try {
		Field f=util.getClass().getDeclaredField(variableName);
		return (String) f.get(util);
		}
		catch(Exception e)
		{
			System.out.println("Error in fetching id with exception: "+e.getMessage());
		}
		
		return null;
	}
}
