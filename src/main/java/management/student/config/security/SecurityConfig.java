package management.student.config.security;


import management.student.config.customer.CustomAccessDeniedHandler;
import management.student.config.customer.RestAuthenticationEntryPoint;
import management.student.config.filter.JwtAuthenticationFilter;
import management.student.model.user.User;
import management.student.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    //Chay dau tien khi khoi tao project.
    public void init() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            User user = new User();
            user.setUsername("admin6");
            user.setPassword(passwordEncoder.encode("admin6"));
            userService.save(user);
        }
    }

    @Bean
    public JwtAuthenticationFilter jwtAthenticationFillter() {

        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint());
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.PUT,   "/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/admin/**").hasAnyAuthority("TEACHER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admin/**").hasAnyAuthority("TEACHER", "ADMIN")
                .antMatchers(HttpMethod.POST,  "/admin/**").hasAnyAuthority("TEACHER", "ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAthenticationFillter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
