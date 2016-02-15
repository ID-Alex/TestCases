package io.sunyi.cases.orm.dao;

import io.sunyi.cases.orm.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User, Long>
{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public User findByUserId(Long userId);
	
	@Query("select u from User u where username like :un")
	public List<User> findByUsername(@Param("un") String username);
	
	public List<User> findByUsernameLike(String username);
	

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select u from User u")
	public List<User> findAllForUpdate(Pageable pageable);
}
