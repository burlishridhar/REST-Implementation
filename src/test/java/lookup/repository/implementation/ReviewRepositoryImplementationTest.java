package lookup.repository.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import lookup.domain.Review;

public class ReviewRepositoryImplementationTest extends TestCase {

	private ReviewRepositoryImplementation r;
	
	@Override
	protected void setUp() throws Exception {
		 r = new ReviewRepositoryImplementation();
	}

	
	public void testGetAllReviews() {
		List<Review> list = r.getAllReviews();
		assertTrue(list!=null && list.size()!=0);
	}

	
	public void testGetReviewByCategoryItem() {
		List<Review> list = r.getReviewByCategoryItem(1);
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetReviewByKeywords() {
		List<String> keywords = new ArrayList<String>();
		keywords.add("Chocolate");
		keywords.add("Jelly");
		
		List<Review> list = r.getReviewByKeywords(keywords);
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetReviewByID() {
		Review review = r.getReviewByID(1);
		assertTrue(review!=null);
	}

	
	public void testSaveReview() {
		Review review = new Review();
		review.setCategoryItemID(1);
		review.setRating(4.5);
		review.setComment("Test Review Insertion");
		review.setUserID(1);
		review.setCreatedDate(new Date());
		
		int result = r.saveReview(review);
		assertTrue(result!=-1);
		
	}

	public void testUpdateReview() {
		Review review = new Review();
		review.setId(1);
		review.setRating(4.5);
		review.setComment("Test Review Update");
		review.setUserID(4);
		
		int result = r.updateReview(review);
		assertTrue(result!=-1);
	}

	
	@Override
	protected void tearDown() throws Exception {
		r = null;
	}

	
}
