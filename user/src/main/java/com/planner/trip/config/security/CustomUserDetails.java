package com.planner.trip.config.security;

import com.planner.trip.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long idx;
    private String email;
    private String password;
    private String phone;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails create(User user) {
        List<GrantedAuthority> authorities
                = Arrays.asList(new SimpleGrantedAuthority(user.getAuth().name()));

        return new CustomUserDetails(
                user.getIdx(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getNickname(),
                authorities
        );
    }
    public Long getIdx(){
        return this.idx;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.nickname;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(idx, that.idx);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
