package com.exam.enitity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserRole {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long userRoleId;


    @ManyToOne (fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Role role;


    public UserRole() {

    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
