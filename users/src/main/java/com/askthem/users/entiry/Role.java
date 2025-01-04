//package com.askthem.users.entiry;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import static com.askthem.users.entiry.Permission.*;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public enum Role {
//  ADMIN(
//          Set.of(
//                  ADMIN_READ,
//                  ADMIN_CREATE,
//                  MEMBER_READ,
//                  MEMBER_CREATE
//          )
//  ),
//  MEMBER(
//          Set.of(
//                  MEMBER_READ,
//                  MEMBER_CREATE
//          )
//  )
//
//  ;
//
//  @Getter
//  private final Set<Permission> permissions;
//
//  public List<SimpleGrantedAuthority> getAuthorities() {
//    var authorities = getPermissions()
//            .stream()
//            .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
//            .collect(Collectors.toList());
//    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//    return authorities;
//  }
//}