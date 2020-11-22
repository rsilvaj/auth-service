package com.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.auth.model.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, K> extends CrudRepository<E, K> {

	public List<E> findAllByActive(boolean active);
	
}
