package com.example.First_sprint_boot.helper;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import static com.example.First_sprint_boot.helper.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN, USER
//    USER(Collections.emptySet()),
//    ADMIN(
//            Set.of(
//                    ADMIN_READ,
//                    ADMIN_UPDATE,
//                    ADMIN_DELETE,
//                    ADMIN_CREATE,
//                    MANAGER_READ,
//                    MANAGER_UPDATE,
//                    MANAGER_DELETE,
//                    MANAGER_CREATE
//            )
//    ),
//    MANAGER(
//            Set.of(
//                    MANAGER_READ,
//                    MANAGER_UPDATE,
//                    MANAGER_DELETE,
//                    MANAGER_CREATE
//            )
//    )
//
//    ;

//    private final Set<Permission> permissions;
//
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        var authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority(this.name()));
//        return authorities;
//    }
}
