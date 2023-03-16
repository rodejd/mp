package com.example.oauth.service;

import com.example.oauth.dto.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;


    /*
    *
    * DB 에서 유저 정보 조회 해서 대조 하는 기능+
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = new Account();
        account.setId("dall011@naver.com");
        account.setPassword(passwordEncoder.encode("abc"));

        UserDetails userDetails = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                return authorities;
            }

            @Override
            public String getPassword() {
                return account.getPassword();
            }

            @Override
            public String getUsername() {
                return account.getId();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }


            /*
             * 30 일마다 패스워드 변경 시
             * */
            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            /*
             * 오래되서 아이디 비활성화
             * */
            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        return userDetails;
    }



}
