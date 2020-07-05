package com.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.car.config.JwtTokenUtil;
import com.car.model.JwtRequest;
import com.car.model.JwtResponse;
import com.car.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		UserDetails userDetails = authenticate(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private UserDetails authenticate(String username, String password) throws Exception {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (!password.equals(userDetails.getPassword())) {
			throw new Exception("INVALID_CREDENTIALS");
		}
		return userDetails;
	}
}