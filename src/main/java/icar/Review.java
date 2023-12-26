package icar;

public class Review {

    private int reviewId;
    private String content;
    private int rating;
    private User reviewer;
    private Product reviewedProduct;
    private InstallationRequest reviewedInstallationRequest;

    public Review(int reviewId, String content, int rating, User reviewer, Product reviewedProduct) {
        this.reviewId = reviewId;
        this.content = content;
        this.rating = rating;
        this.reviewer = reviewer;
        this.reviewedProduct = reviewedProduct;
    }

    public Review(int reviewId, String content, int rating, User reviewer, InstallationRequest reviewedInstallationRequest) {
        this.reviewId = reviewId;
        this.content = content;
        this.rating = rating;
        this.reviewer = reviewer;
        this.reviewedInstallationRequest = reviewedInstallationRequest;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public User getReviewer() {
        return reviewer;
    }

    public Product getReviewedProduct() {
        return reviewedProduct;
    }

    public InstallationRequest getReviewedInstallationRequest() {
        return reviewedInstallationRequest;
    }
}

