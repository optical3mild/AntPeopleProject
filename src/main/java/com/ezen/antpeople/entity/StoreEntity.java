package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezen.antpeople.dto.user.StoreDTO;

import lombok.Getter;

@Getter
@Entity
@Table(name="store")
//StoreDB - store_id, store
public class StoreEntity implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int store_id;
	
	@Column(name="store")
	private String store;
	
	public StoreEntity() {}
	
	public StoreEntity(StoreDTO store) {
		this.store_id = store.getStore_id();
		this.store = store.getStore();
	}
	
	public StoreDTO buildDTO() {
		return new StoreDTO(this.store_id, this.store);
	}

}
