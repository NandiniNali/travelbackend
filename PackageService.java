package com.klef.fsd.sdp.service;

import java.util.List;
import com.klef.fsd.sdp.model.Package;

public interface PackageService {
    String addPackage(Package pkg);
    List<Package> viewAllPackages();
    Package viewPackageById(int pid);
    List<Package> viewPackagesByCategory(String category);
    void bookPackageStatus(int packageId);  // Changed from addPackageStatus
    List<Package> getBookedPackagesStatus();  // Fixed return type
}