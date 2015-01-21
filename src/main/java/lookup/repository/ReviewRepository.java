package lookup.repository;

import java.util.List;

import lookup.domain.Review;

public interface ReviewRepository {
	
	/*
	 * Get all Reviews
	 * */
	public List<Review> getAllReviews();

	/*
	 * Get all reviews by category item
	 * */
	public List<Review> getReviewByCategoryItem(int categoryItemId);
	
	/*
	 * Get all reviews which contain a list of keywords
	 * */
	public List<Review> getReviewByKeywords(List<String> keywords);
	
	/*
	 * Get review by id
	 * */
	public Review getReviewByID(int id);

	/*
	 * Add new Review
	 * */
	public int saveReview(Review review);
	
	/*
	 * Update existing Review
	 * */
	public int updateReview(Review review);
	
}