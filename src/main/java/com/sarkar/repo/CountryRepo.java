package com.sarkar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarkar.entity.CountryEntity;
@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Integer>{

}
