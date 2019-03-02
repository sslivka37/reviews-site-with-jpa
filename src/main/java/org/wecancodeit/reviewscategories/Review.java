package org.wecancodeit.reviewscategories;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;


@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;	
	
	
	@Lob
	private String content;
	
	private String name;
	private String image;
	
	@ManyToMany
	private Collection<Category> categories;
	
	//default constructor
	public Review() {
		
	}

	public Review(String name, String content, String image, Category...categories) {
		this.name = name;
		this.content = content;
		this.image = image;
		this.categories = new HashSet<>(Arrays.asList(categories));
		
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getUrl() {
		return image;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
