package unit.tests;

import org.junit.*;
import junit.framework.TestCase;


import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JUnitTests extends TestCase 
{
	String url = "C:\\Users\\Mark\\Desktop\\Personal Dev\\Pfister\\people.csv";
	private String DB_URL="jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER_NAME = "sys as sysdba";
	private final String PASSWORD = "mraphael2101";
	
	@Test
	public void testThatSourceFileIsARegularExecutableFile() throws Exception
	{	
		Path file = Paths.get(url);

		/* verify that a particular file exists and that the program has the 
		 * ability to execute the file */ 
		boolean isRegularExecutableFile = Files.isRegularFile(file);

		assertTrue(isRegularExecutableFile);
	}

	@Test
	public void testThatSourceFileIsNotEmpty_ValidateRowCount() throws Exception 
	{		
		BufferedReader fileReader = new BufferedReader(new FileReader(url));

		int rowcount = 0;

		while((fileReader.readLine()) != null) 
			rowcount++;

		assertEquals(5, rowcount);

		fileReader.close();        
	}

	@Test
	public void testThatSourceFileIsNotEmpty_ValidateFileContents() throws Exception 
	{
		BufferedReader fileReader = new BufferedReader(new FileReader(url));

		int lengthOfFirstLine = fileReader.readLine().length();		

		assertEquals(32, lengthOfFirstLine);

		fileReader.close(); 
	}

	@Test
	public void testThatDatabaseExists() throws Exception
	{
		// Load and initialize the driver
		Class.forName("oracle.jdbc.OracleDriver");	
		Connection conn = DriverManager.getConnection(DB_URL, USER_NAME,PASSWORD);

		assertEquals("SYS", conn.getSchema());
		
		conn.close();
	}

	@Test
	public void testThatCorrectNoOfRecordsInPersonDatabaseTable() throws Exception 
	{
		ResultSet rs = null;
		Statement stmt = null;

		Class.forName("oracle.jdbc.OracleDriver");	
		Connection conn = DriverManager.getConnection(DB_URL, USER_NAME,PASSWORD);

		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(*) FROM SYS.PERSON");
		rs.next();
				
		assertEquals("4", rs.getString(1));
		
		rs.close(); 
		stmt.close(); 
		conn.close(); 
	} 
}