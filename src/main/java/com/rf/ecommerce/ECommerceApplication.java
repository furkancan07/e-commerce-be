package com.rf.ecommerce;

import com.rf.ecommerce.Entity.Admin.Admin;
import com.rf.ecommerce.Entity.Product.Category;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Service.Admin.AdminService;
import com.rf.ecommerce.Service.Product.CategoryService;
import com.rf.ecommerce.Service.User.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }
 /*  @Bean
    CommandLineRunner startRun(UserService userService, AdminService adminService, CategoryService categoryService){

       return  new CommandLineRunner() {
           @Override
           public void run(String... args) throws Exception {
               User user=new User();
               Admin admin=new Admin();
               user.setPassword("Ef123456789");
               user.setEmail("canfurkan903@gmail.com");
               user.setUsername("furkan");
               user.setPassword(userService.passwordEncoder.encode(user.getPassword()));
               userService.save(user);
               admin.setPassword("Ef1234567879");
               admin.setUsername("admin");
               admin.setPassword(adminService.passwordEncoder.encode(admin.getPassword()));
               adminService.save(admin);
               Category category=new Category();
               category.setName("Teknoloji");
               categoryService.save(category);
           }
       };}*/

}
