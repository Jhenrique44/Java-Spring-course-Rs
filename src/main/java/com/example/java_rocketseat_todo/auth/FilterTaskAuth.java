package com.example.java_rocketseat_todo.auth;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.java_rocketseat_todo.view.repositories.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            var auth = request.getHeader("Authorization");
            auth.substring("Basic".length()).trim();

            byte[] decoded = Base64.getDecoder().decode(auth);

            String authString = new String(decoded);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            //Validate User
            var user = this.userRepository.findByName(username);
            if (user == null) {
                response.sendError(401);
            } else {

                //Validate Password
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (!passwordVerify.verified) {
                    response.sendError(401);
                } else
                    filterChain.doFilter(request, response);
                    request.setAttribute("idUser", user.getId());
            }
        }else {
            filterChain.doFilter(request, response);
        }

    }

}
