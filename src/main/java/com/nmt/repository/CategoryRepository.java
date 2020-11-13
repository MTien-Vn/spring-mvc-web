package com.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmt.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
}
