package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
