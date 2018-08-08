package ch.pfister.model;

import javax.persistence.*;


// @Entity annotation is mandatory for JPA. It defines that a class can be mapped to a table
@Entity
public class Person 
{
    /* @Id annotation is also mandatory for JPA. Automatically generate the primary key value 
     * using the JPA default sequence. allocationSize is the increment value for the sequence, 
     * and name is the label */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERS_SEQ")
    @SequenceGenerator(sequenceName = "person_seq", initialValue = 1, allocationSize = 1, name = "PERS_SEQ")
    long id;
    String firstname, lastname, email, role;

    // Default constructor
    public Person() { }
    
    // Parameterized constructor
    public Person(long id, String firstname, String lastname, String email, String role) 
    {	
    	this.id = id;
    	this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
    }

    // Convert the object to string
    @Override
    public String toString() 
    {    	
    	return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
    
    // Encapsulation : Getters
    public long getId() 
    { 
    	return id; 
    }
    
    public String getFirstname() 
    { 
    	return firstname; 
    }
    
    public String getLastname() 
    { 
    	return lastname; 
    }   
    
    public String getEmail() 
    { 
    	return email; 
    } 
    
    public String getRole() 
    { 
    	return role; 
    }    
   
    // Encapsulation : Setters
    public void setId(long id) 
    { 
    	this.id = id; 
    }
    
    public void setFirstname(String firstname) 
    { 
    	this.firstname = firstname; 
    }
    
    public void setLastname(String lastname) 
    { 
    	this.firstname = lastname; 
    }
    
    public void setEmail(String email) 
    { 
    	this.email = email; 
    }
    
    public void setRole(String role) 
    { 
    	this.role = role; 
    }
}