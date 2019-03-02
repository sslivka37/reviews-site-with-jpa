package org.wecancodeit.reviewscategories;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMVCTest {
	
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Test
	public void should_Route_To_Single_Review_View() throws Exception {
		long arbitraryReviewId =1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
		
	}
	
	@Test
	public void should_Be_Ok_For_Single_Review() throws Exception {
		long arbitraryReviewId =1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());	
		
	}
	
	@Test
	public void should_Not_Be_Ok_For_Single_Review() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());	
		
	}
	
	
	@Test
	public void should_Put_Single_Review_Into_Model() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(review)));
	}
	
	
	@Test
	public void should_Route_To_All_Reviews_View() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	
	@Test
	public void should_Be_Ok_For_All_Reviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	
	@Test
	public void should_Put_All_Reviews_Into_Model() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is (allReviews)));
	}
	
	
	@Test
	public void should_Route_To_Single_Category_View() throws Exception {
		long arbitraryCategoryId =42;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=42")).andExpect(view().name(is("category")));
		
	}
	
	@Test
	public void should_Be_Ok_For_Single_Category() throws Exception {
		long arbitraryCategoryId =42;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=42")).andExpect(status().isOk());	
	}
	
	@Test
	public void should_Not_Be_Ok_For_Single_Category() throws Exception {
		mvc.perform(get("/category?id=42")).andExpect(status().isNotFound());	
		
	}
	
	@Test
	public void should_Put_Single_Category_Into_Model() throws Exception {
		when(categoryRepo.findById(42L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=42")).andExpect(model().attribute("categories", is(category)));
	}
	
	@Test
	public void should_Be_Ok_For_All_Categories() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void should_Put_All_Categories_Into_Model() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		mvc.perform(get("/show-categories")).andExpect(model().attribute("categories", is (allCategories)));
	}
	
	
	
	

}
