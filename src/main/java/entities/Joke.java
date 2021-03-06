package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE FROM Joke"),
@NamedQuery(name = "Joke.getAll", query = "SELECT j FROM Joke j"),
@NamedQuery(name = "Joke.getById", query = "SELECT j FROM Joke j WHERE j.id = :id"),
@NamedQuery(name = "Joke.getByType", query = "SELECT j FROM Joke j WHERE j.jokeType = :jokeType")
      
})
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    
  
    private String theJoke;
    private String jokeType;

    public Joke(String theJoke, String jokeType) {
        this.theJoke = theJoke;
        this.jokeType = jokeType;
    }

    public Joke() {
    }

    public String getTheJoke() {
        return theJoke;
    }

    public void setTheJoke(String theJoke) {
        this.theJoke = theJoke;
    }

    public String getJokeType() {
        return jokeType;
    }

    public void setJokeType(String jokeType) {
        this.jokeType = jokeType;
    }
    
    
}
