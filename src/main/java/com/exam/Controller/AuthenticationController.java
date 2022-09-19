package com.exam.Controller;

import com.exam.config.JwtUtil;
import com.exam.enitity.JwtRequest;
import com.exam.enitity.JwtResponse;
import com.exam.service.impl.UserDetailsServiceImpl;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {





    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsServiceImpl  userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;



    // generate Token

    @PostMapping("/generate-token")
    public ResponseEntity<?>  generateToken (@RequestBody JwtRequest jwtRequest) throws Exception {

        try {



            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }

        catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }


       UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

       String token= this.jwtUtil.generateToken(userDetails);

       return  ResponseEntity.ok(new JwtResponse(token));

    }


    private void authenticate(String username,String password) throws  Exception {


        try {


            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,"hello"));

        }
        catch (DisabledException e ){

            throw new Exception("USER DISABLED" + e.getMessage());
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID " + e.getMessage());
        }
    }

}
