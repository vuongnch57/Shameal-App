package com.example.shameal.data;

public class FeedItem {
    private int id,rate;
    private String name, description, address, avatar, image;
 
    public FeedItem() {
    }
 
    public FeedItem(int id, String name, String address, String image, String description,
            String avatar, int rate) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.description = description;
        this.avatar = avatar;
        this.rate = rate;
    }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
 
    
}
