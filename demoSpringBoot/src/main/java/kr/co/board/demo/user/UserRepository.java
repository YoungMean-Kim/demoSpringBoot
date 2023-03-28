package kr.co.board.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BbsUser, Long>{
	Optional<BbsUser> findByUsername(String username);
}
