package shoesStore.model;

public class HeaderImage {
	private int id;
	private byte[] image;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeaderImage(int id,String name ,byte[] image) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
	}
	
}
