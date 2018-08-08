package ch.pfister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.pfister.data_importer.CSVReader_And_Database_Populator;


/*This annotation enables:
 * @EnableAutoConfiguration Spring Boot’s auto-configuration mechanism 
 * @Component scan on the package where the application is located
 * @Configuration allows you to register extra beans in the context or import additional 
 * configuration classes */
@SpringBootApplication
public class Application implements CommandLineRunner 
{
	/* The @Autowired annotation is applied on fields, setter methods, and constructors.  
	 * @Autowired injects object dependency implicitly. When you use @Autowired on fields 
	 * and pass the values for the fields using the property name, Spring will automatically 
	 * assign the fields with the passed values i.e. there is no need for object instantiation */
	@Autowired
	CSVReader_And_Database_Populator csvReaderAndDBPopulator;
	
	public static void main(String[] args) throws Exception 
	{	    
		// Suppress warnings on the command line
		System.err.close(); 
	    System.setErr(System.out);
		
	    SpringApplication.run(Application.class, args);
	}

	/* @Override annotation improves code readability, and assists to identify classes that require 
	changes when you change the signature of a method */
	@Override
	public void run(String... args) throws Exception 
	{	
		csvReaderAndDBPopulator.csvReaderAndDatabasePopulator();
		csvReaderAndDBPopulator.displayQueryResults();
	}
}