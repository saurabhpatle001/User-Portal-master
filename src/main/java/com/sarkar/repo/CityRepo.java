package com.sarkar.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarkar.entity.CityEntity;
import com.sarkar.entity.StateEntity;
@Repository
public interface CityRepo  extends JpaRepository<CityEntity, Integer>{
	public List<CityEntity> findByStateId(Integer stateId);

}
