package com.example.demo.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Tell Spring this is a table
@Entity
//POJO - Plain Old Java Object
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	boolean hasWhiskers;
	String name;
	boolean evil;
	int length;

	public Cat() {

	}

	public Cat(Long id, boolean hasWhiskers, String name, boolean evil, int length) {
		super();
		this.id = id;
		this.hasWhiskers = hasWhiskers;
		this.name = name;
		this.evil = evil;
		this.length = length;
	}

	public Cat(boolean hasWhiskers, String name, boolean evil, int length) {
		super();
		this.hasWhiskers = hasWhiskers;
		this.name = name;
		this.evil = evil;
		this.length = length;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getHasWhiskers() {
		return hasWhiskers;
	}

	public void setHasWhiskers(boolean hasWhiskers) {
		this.hasWhiskers = hasWhiskers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getEvil() {
		return evil;
	}

	public void setEvil(boolean evil) {
		this.evil = evil;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		return Objects.hash(evil, hasWhiskers, id, length, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		return evil == other.evil && hasWhiskers == other.hasWhiskers && Objects.equals(id, other.id)
				&& length == other.length && Objects.equals(name, other.name);
	}

}
