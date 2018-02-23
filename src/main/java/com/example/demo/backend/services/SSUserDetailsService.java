package com.example.demo.backend.services;

import com.example.demo.backend.domains.Resume;
import com.example.demo.backend.domains.Role;
import com.example.demo.backend.domains.User;
import com.example.demo.backend.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            User user = userRepository.findUserByUsername(username);
            if(user==null)
            {
                throw new UsernameNotFoundException(username+" not found");
            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthorities(user));


        }catch (Exception e)
        {
            throw new UsernameNotFoundException("User not found");

        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: user.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
            System.out.println(grantedAuthority.toString());
        }

        return authorities;
    }
}
