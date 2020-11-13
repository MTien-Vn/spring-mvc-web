package com.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmt.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

}
