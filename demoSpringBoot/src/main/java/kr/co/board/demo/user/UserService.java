package kr.co.board.demo.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.board.demo.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public BbsUser create(String username, String password, String email) {
		BbsUser user = new BbsUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}

	public BbsUser getUser(String username) {
		Optional<BbsUser> bbsUser = this.userRepository.findByUsername(username);
		
		if(bbsUser.isPresent()) {
			return bbsUser.get();
		} else {
			throw new DataNotFoundException("bbs user is not found");
		}
	}
	
}
