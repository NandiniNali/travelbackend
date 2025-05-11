package com.klef.fsd.sdp.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsd.sdp.model.Package;
import com.klef.fsd.sdp.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepository packageRepository;

    @Override
    public String addPackage(Package pkg) {
        packageRepository.save(pkg);
        return "Package Added Successfully";
    }

    @Override
    public List<Package> viewAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Package viewPackageById(int pid) {
        return packageRepository.findById(pid).orElse(null);
    }

    @Override
    public List<Package> viewPackagesByCategory(String category) {
        return packageRepository.findByCategory(category);
    }

    @Override
    public void bookPackageStatus(int packageId) {
        Package pkg = packageRepository.findById(packageId)
            .orElseThrow(() -> new RuntimeException("Package not found"));
        
        pkg.setBooked(true);
        pkg.setBookingDate(LocalDateTime.now());
        packageRepository.save(pkg);
    }

    @Override
    public List<Package> getBookedPackagesStatus() {
        return packageRepository.findByIsBookedTrue();
    }
}