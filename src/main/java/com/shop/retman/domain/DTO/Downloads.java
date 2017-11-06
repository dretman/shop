package com.shop.retman.domain.DTO;

public class Downloads {
    private Long applicationId;
    private Long downloadsQuantity;

    public Downloads() {
    }

    public Downloads(Long applicationId, Long downloadsQuantity) {
        this.applicationId = applicationId;
        this.downloadsQuantity = downloadsQuantity;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getDownloadsQuantity() {
        return downloadsQuantity;
    }

    public void setDownloadsQuantity(Long downloadsQuantity) {
        this.downloadsQuantity = downloadsQuantity;
    }
}
