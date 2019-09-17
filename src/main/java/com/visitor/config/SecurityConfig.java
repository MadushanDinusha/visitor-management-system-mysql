package com.visitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private DataSource dataSource;

        private final String USERS_QUERY = "select username, password, active from users where username=?";
        private final String ROLES_QUERY = "select u.username, r.role from users u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.username=?";

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .usersByUsernameQuery(USERS_QUERY)
                    .authoritiesByUsernameQuery(ROLES_QUERY)
                    .dataSource(dataSource)
                    .passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/","/logout","/home/**").permitAll()
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN","POWER_ADMIN")
                    .antMatchers("/powerAdmin/**").hasAuthority("POWER_ADMIN")
                    .antMatchers("/userHome/**").hasAnyAuthority("POWER_ADMIN","ADMIN","USER","GUARD")
                    .antMatchers("/user/**").hasAnyAuthority("USER","POWER_ADMIN")
                    .antMatchers("/guard/**").hasAnyAuthority("POWER_ADMIN","GUARD")
                    .anyRequest().authenticated().and().csrf().disable()
                    .formLogin().loginPage("/login").permitAll()
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and().logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
                    .and().exceptionHandling().accessDeniedPage("/access_denied");
        }

        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
            db.setDataSource(dataSource);
            return db;
        }
}