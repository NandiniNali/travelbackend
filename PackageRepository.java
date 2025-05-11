package com.klef.fsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.klef.fsd.sdp.model.Package;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    public List<Package> findByCategory(String category);

	public List<Package> findByIsBookedTrue();
	
	
}