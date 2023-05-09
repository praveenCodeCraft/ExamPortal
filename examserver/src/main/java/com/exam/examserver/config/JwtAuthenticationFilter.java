package com.exam.examserver.config;

import com.exam.examserver.Utils.JwtUtils;
import com.exam.examserver.service.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService ;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         final String authenticationHeader = request.getHeader("AUTHORIZATION");
         log.info(authenticationHeader);
         String username = null;
         String jwtToken = null ;

//        String path = request.getRequestURI();
//        System.out.println("Incoming path: " + path);

//         if(authenticationHeader == null){
//             List<String> allowedPaths = Arrays.asList(
//                     "/user/" , "/user/login"
//             );
//             if (allowedPaths.stream().anyMatch(path :: startsWith)){
//                 filterChain.doFilter(request,response);
//             }
//
//         }
         if (authenticationHeader!=null && authenticationHeader.startsWith("Bearer "))
        {
                jwtToken = authenticationHeader.substring(7);

                try {
                  username =   this.jwtUtils.extractUsername(jwtToken);
                }catch (ExpiredJwtException e){
                    log.info("exception is {}" , e);
                    System.out.println("Jwt token has expired");
                }catch (Exception e){
                    log.info("exception error is {}", e);
                    System.out.println("error");
                }
        }else{
             System.out.println("Invalid Token , Not started with bearer");
        }

         //Validate token
        if (username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(jwtToken , userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                log.info("token is not valid");
            }
//            filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }
}
