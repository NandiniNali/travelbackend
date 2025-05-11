package com.klef.fsd.sdp.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.klef.fsd.sdp.dto.PackageDTO;
import com.klef.fsd.sdp.model.Package;
import com.klef.fsd.sdp.service.PackageService;

@RestController
@RequestMapping("/package")
@CrossOrigin("*")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PostMapping("/addpackage")
    public ResponseEntity<String> addPackage(
            @RequestParam String category,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double cost,
            @RequestParam String duration,
            @RequestParam String url,
            @RequestParam("packageImage") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

            Package pkg = new Package();
            pkg.setCategory(category);
            pkg.setName(name);
            pkg.setDescription(description);
            pkg.setCost(cost);
            pkg.setDuration(duration);
            pkg.setImage(blob);

            String output = packageService.addPackage(pkg);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/viewallpackages")
    public ResponseEntity<List<PackageDTO>> viewAllPackages() {
        List<Package> packageList = packageService.viewAllPackages();
        List<PackageDTO> packageDTOList = new ArrayList<>();

        for (Package p : packageList) {
            PackageDTO dto = new PackageDTO();
            dto.setId(p.getId());
            dto.setCategory(p.getCategory());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setCost(p.getCost());
            dto.setDuration(p.getDuration());
            packageDTOList.add(dto);
        }

        return ResponseEntity.ok(packageDTOList);
    }

    @GetMapping("/displaypackageimage")
    public ResponseEntity<byte[]> displayPackageImage(@RequestParam int id) throws Exception {
        Package pkg = packageService.viewPackageById(id);
        byte[] imageBytes = null;
        imageBytes = pkg.getImage().getBytes(1, (int) pkg.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @PostMapping("/viewpackagebyid")
    public ResponseEntity<PackageDTO> viewPackageById(@RequestParam int pid) {
        Package p = packageService.viewPackageById(pid);
        PackageDTO dto = new PackageDTO();
        dto.setId(p.getId());
        dto.setCategory(p.getCategory());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setCost(p.getCost());
        dto.setDuration(p.getDuration());
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping("/book")
    public ResponseEntity<String> bookPackage(@RequestBody Map<String, Integer> request) {
        try {
            Integer packageId = request.get("packageId");
            if (packageId == null) {
                return ResponseEntity.badRequest().body("Package ID is required");
            }
            packageService.bookPackageStatus(packageId);
            return ResponseEntity.ok("Package booked successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error booking package: " + e.getMessage());
        }
    }

    @GetMapping("/booked")
    public ResponseEntity<List<PackageDTO>> getBookedPackages() {
        try {
            List<Package> packages = packageService.getBookedPackagesStatus();
            List<PackageDTO> dtos = new ArrayList<>();
            for (Package pkg : packages) {
                PackageDTO dto = new PackageDTO();
                dto.setId(pkg.getId());
                dto.setName(pkg.getName());
                dto.setDescription(pkg.getDescription());
                dto.setCost(pkg.getCost());
                dto.setDuration(pkg.getDuration());
                dto.setBookingDate(pkg.getBookingDate());
                dtos.add(dto);
            }
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
    }
}