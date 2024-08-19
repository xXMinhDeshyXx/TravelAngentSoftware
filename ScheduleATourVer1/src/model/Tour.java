package model;

public class Tour {
	public int id;
	public String name;
	public String description;
	
	
	
	public Tour() {
		super();
	}

	public Tour(String name, String description) {
		super();
		this.id = 0;
		this.name = name;
		this.description=description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Tour [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

	
}
