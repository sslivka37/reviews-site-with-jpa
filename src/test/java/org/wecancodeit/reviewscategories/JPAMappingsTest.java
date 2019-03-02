package org.wecancodeit.reviewscategories;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.containsInAnyOrder;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	
	@Test
	public void should_Save_And_Load_Category () {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional <Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));		
		
	}
	
	
	@Test
	public void should_Generate_Category_Id() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
		
	}
	
	
	@Test
	public void should_Save_And_Load_Review() {
		Review review = new Review("review name", "content", "image");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional <Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review name"));		
	}
	
	
	@Test
	public void should_Establish_Review_To_Category_Relationship() {
	
	
	Category mmo = categoryRepo.save(new Category("MMO"));
	Category fps = categoryRepo.save(new Category("First Person Shooter"));
	
	Review theDivision = new Review("The Division", "description", "Image", mmo, fps);
	theDivision = reviewRepo.save(theDivision);
	long divisionId = theDivision.getId();
	
	entityManager.flush();
	entityManager.clear();
	
	Optional<Review> result = reviewRepo.findById(divisionId);
	theDivision = result.get();
	
	assertThat(theDivision.getCategories(), containsInAnyOrder(mmo, fps));
		
	}
	
	
	@Test
	public void should_Find_Reviews_For_Categories() {
		Category fps = categoryRepo.save(new Category("FPS"));
		Review halo = reviewRepo.save(new Review("Halo", "description", "image", fps));
		Review theDivision = reviewRepo.save(new Review("The Division", "description", "image", fps));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesContains(fps);
		
		assertThat(reviewsForCategory, containsInAnyOrder(halo, theDivision));
		
	}
	
	@Test
	public void should_Find_Reviews_For_Category_Id() {
		Category fps = categoryRepo.save(new Category("FPS"));
		long categoryId = fps.getId();
		Review halo = reviewRepo.save(new Review("Halo", "description", "image", fps));
		Review theDivision = reviewRepo.save(new Review("The Division", "description", "image", fps));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesId(categoryId);
		
		assertThat(reviewsForCategory, containsInAnyOrder(halo, theDivision));		
		
	}
	
		
	
	
	

}
