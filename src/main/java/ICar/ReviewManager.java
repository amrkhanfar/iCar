package ICar;

import java.util.ArrayList;

public class ReviewManager {

    private ArrayList<Review> reviews;

    public ReviewManager() {
        this.reviews = new ArrayList<>();
    }

    public void addProductReview(User reviewer, Product product, String content, int rating) {
        Review review = new Review(generateReviewId(), content, rating, reviewer, product);
        reviews.add(review);
        System.out.println("Product review added successfully. (" + product.getName() + ")");
    }

    public void addInstallationRequestReview(User reviewer, InstallationRequest installationRequest, String content, int rating) {
        Review review = new Review(generateReviewId(), content, rating, reviewer, installationRequest);
        reviews.add(review);
    }

    public double getAverageRatingForProduct(Product product) {
        int totalRatings = 0;
        int count = 0;

        for (Review review : reviews) {
            if (review.getReviewedProduct() != null && review.getReviewedProduct().equals(product)) {
                totalRatings += review.getRating();
                count++;
            }
        }

        return count > 0 ? (double) totalRatings / count : 0.0;
    }


    public void viewProductReviews(Product product) {
        System.out.println("Product Reviews for " + product.getName() + ":");
        for (Review review : reviews) {
            if (review.getReviewedProduct() != null && review.getReviewedProduct().equals(product)) {
                displayReviewDetails(review);
            }
        }
    }

    public ArrayList<Review> getProductReviews(Product product) {
        ArrayList<Review> productReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (review.getReviewedProduct() != null && review.getReviewedProduct().equals(product)) {
                productReviews.add(review);
            }
        }
        return productReviews;
    }

    public ArrayList<Review> getInstallationRequestReviews(InstallationRequest installationRequest) {
        ArrayList<Review> installationReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (review.getReviewedInstallationRequest().equals(installationRequest)) {
                installationReviews.add(review);
            }
        }
        return installationReviews;
    }



    public void displayReviewDetails(Review review) {
        System.out.println("Review ID: " + review.getReviewId());
        System.out.println("Rating: " + review.getRating());
        System.out.println("Content: " + review.getContent());
        System.out.println("Reviewer: " + review.getReviewer().getName());
        System.out.println("----------------------");
    }

    private int generateReviewId() {
        return reviews.size() + 1;
    }
}
