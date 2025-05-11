package com.klef.fsd.sdp.model;

import java.sql.Blob;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "package_table")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="package_id")
    private int id;

    @Column(name="package_category", nullable = false, length = 50)
    private String category;

    @Column(name="package_name", nullable = false, length = 100)
    private String name;

    @Column(name="package_desc", nullable = false, length = 500)
    private String description;

    @Column(name="package_cost", nullable = false)
    private double cost;

    @Column(name="package_duration", nullable = false, length = 50)
    private String duration;

    @Column(name="package_image", nullable = false)
    private Blob image;

    @Column(name = "is_booked", nullable = false, columnDefinition = "boolean default false")
    private boolean isBooked;
    
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
    
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
