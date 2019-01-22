package ge.mgl.controller;

import ge.mgl.dto.AuthenticationResponseDTO;
import ge.mgl.pojo.UserAuthPOJO;
import ge.mgl.pojo.UserAuthenticationPOJO;
import ge.mgl.pojo.UserSuccessPOJO;
import ge.mgl.security.SpringSecurityUser;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.TokenUtils;
import ge.mgl.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by user on 11/6/17.
 */
@RestController
@RequestMapping("/auth")
public class GAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getMe(){

        Principal user = SecurityContextHolder.getContext().getAuthentication();
        if(user != null){
            SpringSecurityUser profile = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(user.getName());
            if(profile != null){
                return ResponseEntity.status(HttpStatus.OK).body(
                    new UserAuthenticationPOJO.Builder()
                        .setSuccess(true)
                        .setUser(profile)
                        .setCode(200)
                        .build()
                );
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            new UserAuthenticationPOJO.Builder()
                .setSuccess(false)
                .setMessage(Constants.ACCESS_IS_DENIED)
                .setCode(401)
                .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticationRequest(@Valid @RequestBody UserAuthPOJO authenticationRequest) {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = this.tokenUtils.generateToken(user);

        // Return the token
        return ResponseEntity.ok(new UserSuccessPOJO(token, user));
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if(token == null){
            return ResponseEntity.ok(Constants.EMPTY_OR_WRONG_TOKEN);
        }
        String username = this.tokenUtils.getUsernameFromToken(token);
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token)) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponseDTO(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value="logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        return ResponseEntity.ok(new RequestResponse.RequestResponseBuilder().setSuccess(true).build());
    }
}
