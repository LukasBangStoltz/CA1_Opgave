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
    @NamedQuery(name = "Member.deleteAllRows", query = "DELETE from Member"),
    @NamedQuery(name = "Member.getAll", query = "SELECT m FROM Member m"),
    @NamedQuery(name = "Member.getByName", query = "SELECT m FROM Member m WHERE m.name = :name"),
    @NamedQuery(name = "Member.getByStudentId", query = "SELECT m FROM Member m WHERE m.studentId = :studentId"),
    //@NamedQuery(name = "Movie.getByYear", query = "Select m FROM Member m WHERE m.year = :year")
})

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
    private String favoriteSeries;

    public Member(String name, int studentId, String favoriteSeries) {
        this.name = name;
        this.studentId = studentId;
        this.favoriteSeries = favoriteSeries;
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

    public String getFavoriteSeries() {
        return favoriteSeries;
    }

    public void setFavoriteSeries(String favoriteSeries) {
        this.favoriteSeries = favoriteSeries;
    }

}
