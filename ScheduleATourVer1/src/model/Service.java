package model;

public class Service {
	public int id;
	public String name;
	public String type;
	public String sp;
	public int price;
	public int DesID;
	
	
	public Service() {
		super();
	}

	public Service(int id, String name, String type, String sp, int price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sp = sp;
		this.price = price;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public int getDesID() {
		return DesID;
	}

	public void setDesID(int desID) {
		DesID = desID;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", type=" + type + ", sp=" + sp + ", price=" + price
				+ ", DesID=" + DesID + "]";
	}
	
}
