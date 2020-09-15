package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
//@NamedQueries({
//    @NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Member"),
//    @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Member m"),
//    @NamedQuery(name = "Movie.getByTitle", query = "SELECT m FROM Member m WHERE m.name LIKE CONCAT('%',:title,'%')"),
//    @NamedQuery(name = "Movie.getById", query = "SELECT m FROM Member m WHERE m.year = :year")
//})
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
    private String name;
    private int studentId;
    private String colour;

    public Member(String name, int studentId, String colour) {
        this.name = name;
        this.studentId = studentId;
        this.colour = colour;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
