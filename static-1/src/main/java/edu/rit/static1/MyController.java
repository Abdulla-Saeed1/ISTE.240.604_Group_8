package edu.rit.static1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
@Controller //mapping to url paths
public class MyController {

    @RequestMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @RequestMapping("/signin")
    public String getSignInPage(){
        return "login.html";
    }

    @RequestMapping("/") //base url;
    public String getHomePage(){
        return "home.html";
    }

    Product[] getProducts(){
        Product[] products = new Product[2];
    products[0]= new Product("iphone","mobile",2500);
        products[0]= new Product("samsung","phone",9500);
    }

    // returns student names from the database
    String[] getStudentNames(){
        String[] studentnames = new String[5];
        studentnames[0]="John";
        studentnames[1]="Jim";
        studentnames[2]="Jerry";
        studentnames[3]="Jack";
        studentnames[4]="Jaden";
        return studentnames;

    }

    @RequestMapping("/welcome") //base url;
    public String getWelcomePage( Model model){
        // We need 2 placeholders for the variable parameters
        model.addAttribute("name", "get");
        model.addAttribute("color", "Blue");
        model.addAttribute("names", getStudentNames());
        return "welcome";
    }

}