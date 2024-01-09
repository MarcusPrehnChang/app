package com.javawhizz.App;

import com.javawhizz.App.clothing.CombinedData;
import com.javawhizz.App.clothing.testClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.javawhizz.App.clothing.Tag;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {

	}


	@Test
	void testWithoutFilters(){
		List<Tag> preferences = new ArrayList<Tag>();
		preferences.add(new Tag("jeans", 155));
		preferences.add(new Tag("ripped jeans", 75));
		preferences.add(new Tag("loose hoodie", 235));
		List<String> filter = new ArrayList<>();
		filter.add("any");

		testClass tester = new testClass();


		int any = 0;
		int jeans = 0;
		int r_jeans = 0;
		int l_hoodie = 0;
		CombinedData data = new CombinedData(preferences, filter);


		for (int i = 0; i < 1000000; i++){
			ResponseEntity<List<Tag>> response = tester.processResource(data);

			for (Tag e : Objects.requireNonNull(response.getBody())){
				if(e.name.equals("jeans")){
					jeans++;
				}else if (e.name.equals("ripped jeans")){
					r_jeans++;
				}else if (e.name.equals("loose hoodie")){
					l_hoodie++;
				}else{
					any++;
				}
			}
		}

		System.out.println("Jeans was hit : " + jeans);
		System.out.println("Ripped jeans was hit : " + r_jeans);
		System.out.println("Loose hoodie was hit : " + l_hoodie);
		System.out.println("any was hit : " + any);


	}


	@Test
	void testWithFilters(){
		List<Tag> preferences = new ArrayList<Tag>();
		preferences.add(new Tag("jeans", 155));
		preferences.add(new Tag("ripped jeans", 75));
		preferences.add(new Tag("loose hoodie", 235));
		preferences.add(new Tag("bucket hats", 175));
		preferences.add(new Tag("leather gloves", 25));
		List<String> filter = new ArrayList<>();
		filter.add("jeans");
		filter.add("bucket hats");

		testClass tester = new testClass();


		int any = 0;
		int jeans = 0;
		int r_jeans = 0;
		int l_hoodie = 0;
		int b_hats = 0;
		int l_gloves = 0;
		CombinedData data = new CombinedData(preferences, filter);


		for (int i = 0; i < 100000; i++){
			ResponseEntity<List<Tag>> response = tester.processResource(data);

			for (Tag e : Objects.requireNonNull(response.getBody())){
				switch (e.name) {
					case "jeans" -> jeans++;
					case "ripped jeans" -> r_jeans++;
					case "loose hoodie" -> l_hoodie++;
					case "bucket hats" -> b_hats++;
					case "leather gloves" -> l_gloves++;
					default -> any++;
				}
			}
		}

		System.out.println("Jeans was hit : " + jeans);
		System.out.println("Ripped jeans was hit : " + r_jeans);
		System.out.println("Loose hoodie was hit : " + l_hoodie);
		System.out.println("Bucket hats was hit : " + b_hats);
		System.out.println("Leather gloves was hit : " + l_gloves);
		System.out.println("any was hit : " + any);

	}

	@Test
	void testWithMissing(){
		List<Tag> preferences = new ArrayList<Tag>();
		preferences.add(new Tag("jeans", 155));
		preferences.add(new Tag("ripped jeans", 75));
		preferences.add(new Tag("loose hoodie", 235));
		preferences.add(new Tag("bucket hats", 175));
		List<String> filter = new ArrayList<>();
		filter.add("jeans");
		filter.add("bucket hats");
		filter.add("leather gloves");

		testClass tester = new testClass();


		int any = 0;
		int jeans = 0;
		int r_jeans = 0;
		int l_hoodie = 0;
		int b_hats = 0;
		int l_gloves = 0;
		CombinedData data = new CombinedData(preferences, filter);


		for (int i = 0; i < 100000; i++){
			ResponseEntity<List<Tag>> response = tester.processResource(data);

			for (Tag e : Objects.requireNonNull(response.getBody())){
				switch (e.name) {
					case "jeans" -> jeans++;
					case "ripped jeans" -> r_jeans++;
					case "loose hoodie" -> l_hoodie++;
					case "bucket hats" -> b_hats++;
					case "leather gloves" -> l_gloves++;
					default -> any++;
				}
			}
		}

		System.out.println("Jeans was hit : " + jeans);
		System.out.println("Ripped jeans was hit : " + r_jeans);
		System.out.println("Loose hoodie was hit : " + l_hoodie);
		System.out.println("Bucket hats was hit : " + b_hats);
		System.out.println("Leather gloves was hit : " + l_gloves);
		System.out.println("any was hit : " + any);

	}

}
