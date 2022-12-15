package com.javatpoint.server.main.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;  
//Controller  
@RestController  
public class HelloWorldController   
{  
//using get method and hello-world as URI  
@GetMapping(path="/hello-world")  
public String helloWorld()  
{  
return "Hello World";  
}  

@GetMapping(path="/hello-world-bean")  
public HelloWorldBean helloWorldBean()  
{  
return new HelloWorldBean("Hello World"); //constructor of HelloWorldBean  
}  

//passing a path variable  
@GetMapping(path="/hello-world/path-variable/{name}")  
public HelloWorldBean helloWorldPathVariable(@PathVariable String name)  
{  
return new HelloWorldBean(String.format("Hello World, %s", name)); //%s replace the name  
}  

}  