package ch.pfister.data_access_object.queries;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.pfister.model.Person;
import java.util.List;
import java.util.stream.Stream;


/* PersonRepository inherits from the CrudRepository interface, in Spring JPA,  
 * which provides it with CRUD functions */

public interface PersonRepository extends CrudRepository<Person, Long> 
{
	/* Query method should return more than one result, so we can use the following types: 
	 * List<Type> : Query method will return a List that contains the query results or an empty list */
	List<Person> findByEmail(String email);

	/* Stream<Type> : Query method will return a Stream that can be used to access the query results, 
	 * or an empty Stream */
	@Query("select p from Person p where p.email = :email")
	Stream<Person> findByEmailReturnStream(@Param("email") String email);
}