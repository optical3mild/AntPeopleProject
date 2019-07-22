package com.ezen.antpeople.dto.user;

import lombok.Getter;

@Getter
public class StoreDTO {
	private int store_id;
	private String store;
	
	public StoreDTO() {}
	
	public StoreDTO(int store_id) {
		this.store_id = store_id;
	}
	
	public StoreDTO(int store_id,String store) {
		this.store_id = store_id;
		this.store = store;
			
	}
}