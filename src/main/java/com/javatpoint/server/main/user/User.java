package com.javatpoint.server.main.user;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user") 
@Entity  
public class User   
{  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  
	@Size(min=5, message="Name should have atleast 5 charcters.")
	@ApiModelProperty (notes="name should have atleast 5 characters")
	private String name;  
	@Past (message="Date of Birth cannot be in future.")
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date dob;  
	@OneToMany(mappedBy="user")  
	private List<Post> posts;  
	
	//default constructor     
	protected User()  
	{  
	      
	}  
	
public User(Integer id, String name, Date dob)   
{  
super();  
this.id = id;  
this.name = name;  
this.dob = dob;  
}  

public Integer getId()   
{  
return id;  
}  
public void setId(Integer id)   
{  
this.id = id;  
}  
public String getName()   
{  
return name;  
}  
public void setName(String name)   
{  
this.name = name;  
}  
public Date getDob()   
{  
return dob;  
}  
public void setDob(Date dob)   
{  
this.dob = dob;  
}  
@Override  
public String toString()   
{  
//return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";  
return String.format("User [id=%s, name=%s, dob=%s]", id, name, dob);  
}

//public List<Post> getPosts() {
//	return posts;
//}
//
//public void setPosts(List<Post> posts) {
//	this.posts = posts;
//}  
}  
