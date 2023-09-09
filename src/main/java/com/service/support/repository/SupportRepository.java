package com.service.support.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.support.entity.Support;
import com.service.support.entity.User;


@Repository
public interface SupportRepository extends JpaRepository<Support, String> {

	Support findByIdAndActive(long supportId, boolean b);
	
	List<Support> findByUser(User user);

	List<Support> findAllByActive(boolean b);

	Optional<Support> findById(long supportId);

}
