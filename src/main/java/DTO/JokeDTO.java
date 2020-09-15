package DTO;
import entities.Joke;

public class JokeDTO {
    
    private long id;
    private String theJoke;
    private String jokeType;

    public JokeDTO(Joke joke) {
        this.id = joke.getId();
        this.theJoke = joke.getTheJoke();
        this.jokeType = joke.getJokeType();
    }

    public long getId() {
        return id;
    }

    public String getTheJoke() {
        return theJoke;
    }

    public String getJokeType() {
        return jokeType;
    }

    @Override
    public String toString() {
        return "JokeDTO{" + "id=" + id + ", theJoke=" + theJoke + ", jokeType=" + jokeType + '}';
    }
    
    
}
