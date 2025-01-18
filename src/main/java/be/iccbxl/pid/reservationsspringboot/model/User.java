package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String langue;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime created_at;

    @ManyToMany(mappedBy = "users")
    private List<Representation> representations = new ArrayList<>();

    public List<Representation> getRepresentations() {
        return representations;
    }

    public User addRepresentation(Representation representation) {
        if(!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.addUser(this);
        }

        return this;
    }

    public User removeRepresentation(Representation representation) {
        if(this.representations.contains(representation)) {
            this.representations.remove(representation);
            representation.getUsers().remove(this);
        }

        return this;
    }


    @Override
    public String toString() {
        return login + "(" + firstname + " " + lastname + " - " + role + ")";
    }

}
