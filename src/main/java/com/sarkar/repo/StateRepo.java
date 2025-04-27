package com.sarkar.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarkar.entity.StateEntity;
@Repository
public interface StateRepo extends JpaRepository<StateEntity, Integer>{
	
	public List<StateEntity> findByCountryId(Integer countryId);

}
