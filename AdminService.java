package com.klef.fsd.sdp.service;

import java.util.List;
import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;

public interface AdminService 
{
    public Admin checkadminlogin(String username, String password);
    public List<Customer> displaycustomers();
    public String deletecustomer(int cid);
    public long displaycustomercount();
    public long displaypackagecount();
}
