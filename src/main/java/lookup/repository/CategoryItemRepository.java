package lookup.repository;

import java.util.List;

import lookup.domain.CategoryItem;

public interface CategoryItemRepository {

	/*
	 * Get All Category Items
	 * */
	public List<CategoryItem> getAllCategoryItems();
	
	/*
	 * Get category items by id
	 * */
	public List<CategoryItem> getCategoryItemByCategory(int categoryID);
	
	/*
	 * Get category items by category item name
	 * */
	public List<CategoryItem> getCategoryItemByName(String categoryItemName);
	
	/*
	 * Get category items by user id
	 * */
	public List<CategoryItem> getCategoryItemByUser(int userID);
	
	/*
	 * Get category item by id
	 * */
	public CategoryItem getCategoryItemByID(int categoryItemID);
	
	/*
	 * Add a new Category Item
	 * */
	public int saveCategoryItem(CategoryItem categoryItem);
	
	/*
	 * Update existing Category Item
	 * */
	public int updateCategoryItem(CategoryItem categoryItem);
	
}
