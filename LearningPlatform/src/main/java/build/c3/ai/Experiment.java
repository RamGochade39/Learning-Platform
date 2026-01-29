package build.c3.ai;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import build.c3.ai.entity.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/add")
public class Experiment {
	
	@PostMapping("")
	public String postMethodName(@RequestBody User entity) {
		
		return "";
	}
	
	

}
