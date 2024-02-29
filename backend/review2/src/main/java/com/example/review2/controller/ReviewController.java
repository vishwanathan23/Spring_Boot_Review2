package com.example.review2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.review2.model.Customer;
import com.example.review2.repository.CustomerRepo;

@RestController
public class ReviewController{
@Autowired
private final CustomerRepo repo;



public ReviewController(CustomerRepo repo)
{
this.repo=repo;
}
@GetMapping("/getall")
public List <Customer>getall()
{
return repo.findAll();
}

@GetMapping("/getPaginated")
public Page<Customer>getPaginated(@RequestParam int number,@RequestParam int pageSize,@RequestParam String field){
return repo.findAll(PageRequest.of(number,pageSize,Sort.by(field)));
}

@GetMapping("/getpage/{pageno}/{pagesize}")
public List<Customer>getall(@PathVariable int pageno,@PathVariable int pagesize)
{
PageRequest page=PageRequest.of(pageno,pagesize);
return repo.findAll(page).get().toList();
}



@PostMapping("/insert")
public ResponseEntity<String>insert(@RequestBody List<Customer>obj){
repo.saveAll(obj);
return ResponseEntity.ok("DataSaved");
}
}