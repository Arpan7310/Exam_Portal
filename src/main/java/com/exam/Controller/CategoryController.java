package com.exam.Controller;

import com.exam.enitity.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    //addCategory


    @PostMapping("/")

    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        Category category1=this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }


    //getCategory

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId){

        return  this.categoryService.getCategory(categoryId);
    }


    //getCategories

    @GetMapping("/")
    public ResponseEntity<?> getCategories() {

        return ResponseEntity.ok(this.categoryService.getCategories());
    }


    //update Category

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }


    //delete Category


    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }



}
