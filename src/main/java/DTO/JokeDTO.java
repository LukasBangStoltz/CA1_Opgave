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
    
    
}
