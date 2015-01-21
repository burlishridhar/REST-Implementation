package lookup.repository;

import java.util.List;

import lookup.domain.Category;

public interface CategoryRepository {

	/*
	 * Get all Categories
	 * */
	public List<Category> getAllCategories();
	
	/*
	 * Get category by name
	 * */
	public List<Category> getCategoryByName(String name);
	
	/*
	 * Get category by user ID
	 * */
	public List<Category> getCategoryByUserID(int userID);
	
	/*
	 * Get category by ID
	 * */
	public Category getCategorybyID(int id);
	
	/*
	 * Add new Category
	 * */
	public int saveCategory(Category category);
	
	/*
	 * Update existing Category
	 * */
	public int updateCategory(Category category);
	
	
}
