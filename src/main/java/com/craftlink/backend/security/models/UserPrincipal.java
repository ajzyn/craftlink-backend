package com.craftlink.backend.security.models;

import com.craftlink.backend.specialist.entities.SpecializationEntity;
import com.craftlink.backend.user.entities.UserEntity;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserPrincipal implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Set<String> specializationSlugs;

    public UserPrincipal(UserEntity user) {
        id = user.getId();
        username = user.getEmail();
        password = user.getPassword();
        authorities = user.getAuthorities()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getCode()))
            .collect(Collectors.toSet());

        if (user.getSpecialist() != null && user.getSpecialist().getSpecializations() != null) {
            specializationSlugs = user.getSpecialist()
                .getSpecializations()
                .stream()
                .map(SpecializationEntity::getSlug)
                .collect(Collectors.toSet());
        } else {
            specializationSlugs = Set.of();
        }
    }
}
