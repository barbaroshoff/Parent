package com.indomdi.com.web.config;

import com.indomdi.com.core.dao.UsersDao;
import com.indomdi.com.core.persistent.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class PermissionFilter  extends GenericFilterBean {

    @Autowired
    private UsersDao usersDao;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String username = null;
         try {
             username = SecurityContextHolder.getContext().getAuthentication().getName();
         }catch(Exception e){
             System.out.println("no username found");
         }
         if(username != null) {
            System.out.println(username);
            Users usr = null;
            try {
                usr = usersDao.findByUsername(username).get();
            }catch(Exception e){
                System.out.println("user not found");
            }
            if (usr != null && usr.isEnabled() == false)
                throw new IOException();
        }

        chain.doFilter(request, response);
    }
}
