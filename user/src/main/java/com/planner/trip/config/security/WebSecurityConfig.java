package com.planner.trip.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//https://www.slideshare.net/madvirus/ss-36809454 spring security filter chain doc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomUserDetailService customUserDetailService;
    private CustomAuthEntryPoint customAuthEntryPoint;
    private JwtManager jwtManager;
    @Autowired
    public WebSecurityConfig(CustomUserDetailService customUserDetailService,
                             CustomAuthEntryPoint customAuthEntryPoint,
                             JwtManager jwtManager){
        this.customUserDetailService = customUserDetailService;
        this.customAuthEntryPoint = customAuthEntryPoint;
        this.jwtManager = jwtManager;
    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    //TODO CSRF는 세션기반, 쿠키기반이 있음
    //쿠키기반의 경우 제출 당할경우 자동으로 제출되기떄문에 안전하지 않을 수 있음
    //  rest api는 stateless 이기 때문 세션 기반 사용못함
    //현재는 쿠키기반으로 적용되어있음  추후 수정 필요
    //PATCH, POST, PUT,DELETE  method 는 csrf 적용
    //https://docs.spring.io/spring-security/site/docs/3.2.0.CI-SNAPSHOT/reference/html/csrf.html
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(customAuthEntryPoint)
                    .and()
                .authorizeRequests()
                .antMatchers("/test").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();
        //https://luvstudy.tistory.com/79
        // OncePerRequestFilter 상속한 필터는 빈설정하면 자동으로 filter 등록됨
        //하지만 UsernamePasswordAuthenticationFilter 타기전에 인증후
        //UsernamePasswordAuthenticationToken 생성해야하기떄문에 UsernamePasswordAuthenticationToken전에 등록
        http.addFilterBefore(new JwtAuthenticationFilter(jwtManager,customUserDetailService),UsernamePasswordAuthenticationFilter.class);
    }

}
