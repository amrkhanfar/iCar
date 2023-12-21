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
        System.out.println("Product review added successfully.");
    }

    public void addInstallationRequestReview(User reviewer, InstallationRequest installationRequest, String content, int rating) {
        Review review = new Review(generateReviewId(), content, rating, reviewer, installationRequest);
        reviews.add(review);
        System.out.println("Installation request review added successfully.");
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

    public void viewInstallationRequestReviews(InstallationRequest installationRequest) {
        System.out.println("Installation Request Reviews for Request ID " + installationRequest.getId() + ":\n");
        for (Review review : reviews) {
            if (review.getReviewedInstallationRequest() != null && review.getReviewedInstallationRequest().equals(installationRequest)) {
                displayReviewDetails(review);
            }
        }
    }

    private void displayReviewDetails(Review review) {
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
