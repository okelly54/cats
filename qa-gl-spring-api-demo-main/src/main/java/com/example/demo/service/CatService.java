package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Cat;

public interface CatService {	

	Cat createCat(Cat c);
	Cat get(int id);
	List<Cat> getAll();
	Cat delete(int id);
	Cat update(int id, Boolean hasWhiskers, String name,  Boolean evil, Integer length);

}
