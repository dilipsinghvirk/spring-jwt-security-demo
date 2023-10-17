package spring.jwt.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResouces {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to alwasy be learner";
	}
	
	@GetMapping("/colors")
	public List<String> getColorsName(){
		List<String> col = new ArrayList<>();
		col.add("Red");
		col.add("Yellow");
		return col;
	}

	@Autowired
	AuthenticationManager authenticationManagerBean;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CustomeUserDetailsService customeUserDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponse> createAndAuthenticateReq(@RequestBody AuthenticateRequeset request) throws Exception{
		try {
		authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		
		}catch(Exception e) {
			throw new Exception("Invalid username or password"+e);
		}
		
		String username = request.getUsername();
		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
		String jwtToken =  jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jwtToken));
		
	}

}
