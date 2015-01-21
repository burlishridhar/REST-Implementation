package lookup.repository.implementation;

import java.util.List;

import junit.framework.TestCase;
import lookup.domain.CategoryItem;

public class CategoryItemRepositoryImplementationTest extends TestCase {

	private CategoryItemRepositoryImplementation c;
	
	@Override
	protected void setUp() throws Exception {
		c = new CategoryItemRepositoryImplementation();
	}

	public void testGetAllCategoryItems() {
		List<CategoryItem> list = c.getAllCategoryItems();
		assertTrue(list!=null && list.size()!=0);
	}
	
	public void testGetCategoryItemByCategory() {
		List<CategoryItem> list = c.getCategoryItemByCategory(1);
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetCategoryItemByName() {
		List<CategoryItem> list = c.getCategoryItemByName("isys");
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetCategoryItemByUser() {
		List<CategoryItem> list = c.getCategoryItemByUser(1);
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetCategoryItemByID() {
		CategoryItem categoryItem = c.getCategoryItemByID(1);
		assertTrue(categoryItem!=null);
	}

	public void testSaveCategoryItem() {
		CategoryItem categoryItem = new CategoryItem();
		categoryItem.setCategoryID(1);
		categoryItem.setName("Massachusetts Institute of Technology");
		categoryItem.setUserID(1);
		int result = c.saveCategoryItem(categoryItem);
		
		assertTrue(result!=-1);
	}

	public void testUpdateCategoryItem() {
		CategoryItem categoryItem = new CategoryItem();
		categoryItem.setId(17);
		categoryItem.setCategoryID(3);
		categoryItem.setName("ISYS 636");
		categoryItem.setUserID(1);
		int result = c.saveCategoryItem(categoryItem);
		
		assertTrue(result!=-1);
	}
	
	@Override
	protected void tearDown() throws Exception {
		c = null;
	}

}
