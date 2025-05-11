package com.klef.fsd.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController 
{
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/")
    public String home()
    {
        return "FSD SDP Project";
    }
    
    @PostMapping("/registration")
    public ResponseEntity<String> customerregistration(@RequestBody Customer customer)
    {
        try
        {
            String output = customerService.customerregistration(customer);
            return ResponseEntity.ok(output);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Customer Registration Failed ...");
        }
    }
    
    @PostMapping("/checkcustomerlogin")
    public ResponseEntity<?> checkcustomerlogin(@RequestBody Customer customer) 
    {
        try 
        {
            Customer c = customerService.checkcustomerlogin(customer.getUsername(), customer.getPassword());

            if (c!=null) 
            {
                return ResponseEntity.ok(c);
            } 
            else 
            {
                return ResponseEntity.status(401).body("Invalid Username or Password");
            }
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }
    
    @PutMapping("/updateprofile")
    public ResponseEntity<String> customerupdateprofile(@RequestBody Customer customer)
    {
        try
        {
            System.out.println(customer.toString());
            String output = customerService.customerupdateprofile(customer);
            return ResponseEntity.ok(output);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Failed to Update Customer ... !!"); 
        }
    }
}