package com.auth.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "active", nullable = false)
	private boolean active;
    
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public void setActive(boolean active) { this.active = active; }
	
	public boolean isActive() { return active; }
	
	public boolean isInactive() { return !active; }
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
