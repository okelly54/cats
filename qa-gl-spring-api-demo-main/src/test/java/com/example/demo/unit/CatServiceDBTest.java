package com.example.demo.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.Cat;
import com.example.demo.repo.CatRepo;
import com.example.demo.service.CatService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {

	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	void TestUpdate() {
		int id = 1;
		Cat existing = new Cat((long) id, true, "rat", true, 1);
		Cat updated = new Cat((long) id, false, "crispy", false, 2);
		Mockito.when(this.repo.findById((long) id)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

		Assertions.assertEquals(updated, this.service.update(id, updated.getHasWhiskers(), updated.getName(),
				updated.getEvil(), updated.getLength()));

	}

	@Test
	void TestGet() {
		Cat created = new Cat(1L, true, "rat", true, 1);
		Long id = 1L;
		Mockito.when(this.repo.getReferenceById(id)).thenReturn(created);
	}

	@Test
	void TestGetAll() {
		Cat created = new Cat(true, "rat", true, 1);
		List<Cat> cats = new ArrayList<>();
		cats.add(created);
		Mockito.when(this.repo.findAll()).thenReturn(cats);
	}

	@Test
	void TestCreateCat() {
		Cat created = new Cat(true, "rat", true, 1);
		Mockito.when(this.repo.save(created)).thenReturn(created);
	}

}
