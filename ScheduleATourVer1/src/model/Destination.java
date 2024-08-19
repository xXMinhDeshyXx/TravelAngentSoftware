package model;

public class Destination {
	public int id;
	public String name;
	public String location;
	public String starttime;
	public String endtime;
	
	
	public Destination() {
		super();
	}

	public Destination(int id, String name, String location, String starttime, String endtime) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.starttime = starttime;
		this.endtime = endtime;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Override
	public String toString() {
		return "Destination [id=" + id + ", name=" + name + ", location=" + location + ", starttime=" + starttime
				+ ", endtime=" + endtime + "]";
	}

	
}
