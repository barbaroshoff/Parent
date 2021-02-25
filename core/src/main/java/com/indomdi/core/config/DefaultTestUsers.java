package com.indomdi.core.config;

import com.indomdi.core.dao.AuthoritiesDao;
import com.indomdi.core.dao.UsersDao;
import com.indomdi.core.persistent.Authorities;
import com.indomdi.core.persistent.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class DefaultTestUsers {
    @Autowired
    private AuthoritiesDao authoritiesDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        Optional<Users> op = usersDao.findByUsername("admin");
        if (!op.isPresent()) {
            Users users = new Users();
            users.setUsername("admin");
            users.setEnabled(true);



            users.setEmail("email@emai.com");
            users.setFirstName("aaa");
            users.setLastName("aaa");
            users.setCountry("Moldova");
            users.setSecurityQuestion("dsvdvfd");
            users.setSecurityAnswer("sada");
            users.setOrganization("aaaa");
            users.setCity("Chisinau");
            users.setGuiRoles(GuiRoles.ADMIN);
            final String pass = passwordEncoder.encode("admin");
            users.setPassword(pass);

            users = usersDao.save(users);

            final Authorities authority = new Authorities();
            authority.setUser(users);
            authority.setAuthority(GuiRoles.ADMIN.authority().getAuthority());
            authoritiesDao.save(authority);
        }

        op = usersDao.findByUsername("user");
        if (!op.isPresent()) {
            Users users = new Users();
            users.setUsername("user");
            users.setEmail("email@emai.com");
            users.setEnabled(true);

            users.setEmail("email@emai.com");
            users.setFirstName("aaa");
            users.setLastName("aaa");
            users.setCountry("Moldova");
            users.setSecurityQuestion("dsvdvfd");
            users.setSecurityAnswer("sada");
            users.setOrganization("aaaa");
            users.setCity("Chisinau");
            users.setGuiRoles(GuiRoles.USER);

            final String pass = passwordEncoder.encode("pass");
            users.setPassword(pass);
            users = usersDao.save(users);

            final Authorities authority = new Authorities();
            authority.setUser(users);
            authority.setAuthority(GuiRoles.USER.authority().getAuthority());
            authoritiesDao.save(authority);
        }


        op = usersDao.findByUsername("barbos");
        if (!op.isPresent()) {
            Users users = new Users();
            users.setUsername("barbos");
            users.setEmail("barbarosh.96e@gmai.com");
            users.setEnabled(true);

            users.setEmail("barbarosh.96e@emai.com");
            users.setFirstName("egor");
            users.setLastName("bar");
            users.setCountry("Mobldova");
            users.setGuiRoles(GuiRoles.USER);
            final String pass = passwordEncoder.encode("pass");
            users.setPassword(pass);
            usersDao.save(users);

            final Authorities authority = new Authorities();
            authority.setUser(users);
            authority.setAuthority(GuiRoles.USER.authority().getAuthority());
            authoritiesDao.save(authority);
        }

    }
}
