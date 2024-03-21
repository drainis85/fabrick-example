package com.fabrick.example.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Type {

	@Id
	private Long id;
	private String enumeration;
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
