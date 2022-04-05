package com.furnitureshop.security.service;

import com.furnitureshop.role.entity.Group;
import com.furnitureshop.security.dto.UserDetailsDto;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsernameWithGroups(username);

        if(!user.isPresent())
            throw new UsernameNotFoundException("Username is not existed.");

        Set<GrantedAuthority> authorities = getAuthorities(user.get().getGroups());

        return new UserDetailsDto(username, user.get().getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Group> groups) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Group group : groups) {
            authorities.add(new SimpleGrantedAuthority(group.getName()));
        }

        return authorities;
    }

}