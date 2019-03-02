package org.wecancodeit.reviewscategories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByCategoriesContains(Category category);
	
	Collection<Review> findByCategoriesId(Long id);

}
