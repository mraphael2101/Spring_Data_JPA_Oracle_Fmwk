package ch.pfister.data_importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ch.pfister.data_access_object.queries.PersonRepository;
import ch.pfister.model.Person;

/* The purpose of Spring Data repository abstraction is to significantly reduce the amount of 
 * boilerplate code required to implement data access layers for various persistence stores */

/* @Component annotation is used on classes to indicate a Spring component. It marks the Java class 
 * as a bean or say component so that the component-scanning mechanism of Spring can add into the 
 * application context 
 * @Repository annotation is used on Java classes which directly access the database. The @Repository 
 * annotation works as marker for any class that fulfills the role of repository or Data Access Object
 * This annotation has a automatic translation feature. For example, when an exception occurs in the 
 * @Repository there is a handler for that exception and there is no need to add a try catch block */
@Component
@Repository
public class CSVReader_And_Database_Populator 
{
	private static final int PERSON_ID_IDX = 0;
	private static final int PERSON_FIRSTNAME_IDX =1;
	private static final int PERSON_LASTNAME_IDX =2;
	private static final int PERSON_EMAIL_IDX =3;
	private static final int PERSON_ROLE_IDX =4;

	@Autowired
	PersonRepository personRepository;

	public void csvReaderAndDatabasePopulator() throws Exception 
	{
		BufferedReader fileReader = null;

		List<Person> persons = new ArrayList<Person>();
		String line = "";
		fileReader = new BufferedReader(new FileReader("C:\\Users\\Mark\\Desktop\\Personal Dev\\Pfister\\people.csv"));

		// Read CSV header
		fileReader.readLine();

		// Continue reading people data line by line
		while ((line = fileReader.readLine()) != null) 
		{			
			String[] tokens = line.split(";");

			if (tokens.length > 0) 
			{
				if (tokens[PERSON_LASTNAME_IDX].toString().contains("a\'"))
					tokens[PERSON_LASTNAME_IDX]=tokens[PERSON_LASTNAME_IDX].toString().replaceAll("a\'", "á");

				Person person = new Person(
						Long.parseLong(tokens[PERSON_ID_IDX]),
						tokens[PERSON_FIRSTNAME_IDX], 
						tokens[PERSON_LASTNAME_IDX], 
						tokens[PERSON_EMAIL_IDX], 
						tokens[PERSON_ROLE_IDX]);

				persons.add(person);
			}
		}

		this.populatePersonDatabaseTable(persons);

		fileReader.close();
	}

	// Method saves the given entity if the object is not null or the list is empty
	private void populatePersonDatabaseTable(List<Person> persons) 
	{
		if(Objects.isNull(persons) || persons.isEmpty()) 
			return;

		else 
			personRepository.save(persons);
	}

	// 
	public void displayQueryResults() 
	{   
		System.out.println("\nfindAll()");
		for (Person person : personRepository.findAll()) 
			System.out.println(person);
		
		System.out.println("\nfindByEmail(String email)");
		for (Person person : personRepository.findByEmail("marc.chataka@company.com")) 
			System.out.println(person);
	}
}