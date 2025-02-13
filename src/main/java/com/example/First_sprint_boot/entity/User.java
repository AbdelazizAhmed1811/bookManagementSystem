package com.example.First_sprint_boot.entity;

import com.example.First_sprint_boot.helper.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String firstname;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String lastname;
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return role.getAuthorities();
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
