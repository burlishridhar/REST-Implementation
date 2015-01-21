package lookup.repository.implementation;

import java.util.List;

import junit.framework.TestCase;
import lookup.domain.Category;

public class CategoryRepositoryImplementationTest extends TestCase {

	private CategoryRepositoryImplementation c;
	
	@Override
	protected void setUp() throws Exception {
		c = new CategoryRepositoryImplementation();
	}

	public void testGetAllCategories() {
		List<Category> list = c.getAllCategories();
		assertTrue(list!=null && list.size()!=0);
	}

	
	 public void testGetCategoryByName() {
		 List<Category> list = c.getCategoryByName("University");
			assertTrue(list!=null && list.size()!=0);
	}

	public void testGetCategoryByUserID() {
		List<Category> list = c.getCategoryByUserID(1);
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetCategorybyID() {
		Category category = c.getCategorybyID(1);
		assertTrue(category!=null);
	}

	
	public void testSaveCategory() {
		Category category = new Category();
		category.setName("TestCategory");
		category.setUserID(1);
		int result = c.saveCategory(category);
		assertTrue(result!=-1);
	}

	public void testUpdateCategory() {
		Category category = new Category();
		category.setId(2);
		category.setName("Company Interviews");
		category.setUserID(1);
		int result = c.updateCategory(category);
		assertTrue(result!=-1);
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		c= null;
	}
}
