package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    // for authentication using database
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.ldapAuthentication()
                    .userDnPatterns("uid={0},ou=people")
                    .groupSearchBase("ou=groups")
                    .contextSource()
                    .url("ldap://localhost:8389/dc=springframework,dc=org")
                    .and()
                    .passwordCompare()
                    .passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute("userPassword");

    }

    //for authorization
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and().formLogin();
    }

    @Bean

    public PasswordEncoder getGetPassword() {
        return NoOpPasswordEncoder.getInstance();
    }
}
