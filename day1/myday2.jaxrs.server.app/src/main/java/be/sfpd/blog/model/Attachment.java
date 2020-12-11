package be.sfpd.blog.model;

public class Attachment {


	private String name;

	public Attachment(String name, byte[] data) {
		this.name = name;
		this.data = data;
	}

	public Attachment() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	private byte[] data;



}
