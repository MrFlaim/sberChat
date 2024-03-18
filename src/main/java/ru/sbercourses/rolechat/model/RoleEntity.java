package ru.sbercourses.rolechat.model;

import ru.sbercourses.rolechat.model.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    public RoleEntity() {
    }

    public RoleEntity(Long id, Role role, List<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public RoleEntity(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
