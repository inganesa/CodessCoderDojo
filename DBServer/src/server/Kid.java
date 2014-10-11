package server;


public class Kid {
private int age;
private String name;
private int rating;


public Kid(String name, int rating){
	this.name = name;
	this.rating = rating;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public  Kid getKid(String name1){
	if (name1.equals(name)){
		return this;
	}
	
	else {
		return null;
	}
}
}
