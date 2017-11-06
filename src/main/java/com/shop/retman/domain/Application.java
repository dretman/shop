package com.shop.retman.domain;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "APPLICATION")
public class Application implements Serializable {
    private long id;

    @NotNull
    @NotBlank(message = "{name.blank}")
    @Size(min = 2, max = 50, message = "{name.size}")
    private String applicationName;

    @NotNull
    @NotBlank(message = "{description.blank}")
    @Size(min = 5, max = 250, message = "{description.size}")
    private String description;

    private Category category;
    private String packageName;
    private String picture128;
    private String picture512;
    private Long downloadsQuantity;
    private Date uploadDate;
    private byte[] image128;
    private byte[] image512;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @OneToOne
    public Category getCategory() {
        return category;
    }

    @Column(name = "APPLICATION_NAME")
    public String getApplicationName() {
        return applicationName;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PACKAGE_NAME")
    public String getPackageName() {
        return packageName;
    }

    @Column(name = "PICTURE_128")
    public String getPicture128() {
        return picture128;
    }

    @Column(name = "PICTURE_512")
    public String getPicture512() {
        return picture512;
    }

    @Column(name = "IMAGE_128")
    public byte[] getImage128() {
        return image128;
    }

    @Column(name = "IMAGE_512")
    public byte[] getImage512() {
        return image512;
    }

    @Column(name = "DOWNLOADS_QUANTITY")
    public Long getDownloadsQuantity() {
        return downloadsQuantity;
    }

    @Column(name = "UPLOAD_DATE")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPicture128(String picture128) {
        this.picture128 = picture128;
    }

    public void setPicture512(String picture512) {
        this.picture512 = picture512;
    }

    public void setImage128(byte[] image128) {
        this.image128 = image128;
    }

    public void setImage512(byte[] image512) {
        this.image512 = image512;
    }

    public void setDownloadsQuantity(Long downloadsQuantity) {
        this.downloadsQuantity = downloadsQuantity;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
