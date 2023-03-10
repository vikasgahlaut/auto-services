package com.javatpoint.server.main.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;  
//Controller  
@RestController  
public class HelloWorldController   
{  
	
@Autowired   
private MessageSource messageSource;  
	
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
//internationalization  
@GetMapping(path="/hello-world-internationalized")  
public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale)  
{  
	return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());  
}  
}  