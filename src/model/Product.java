package model;

import java.util.List;

public class Product {
	int id;
	String name;
	String imageBase64;
	Integer price;
	Float screenSize;
	String screenTechnology;
	String os;
	String frontCamera;
	String posteriorCamera;
	String cpu;
	String ram;
	String internalMemory;
	String memoryStick;
	String sim;
	boolean waterproof;
	boolean fastCharging;
	boolean dualCamera;
	boolean faceSecurity;
	boolean fingerprintSecurity;
	String pin;
	Manufacturer manufacturer;
	List<String> colors;
	boolean status;
	
	public Product() {
		super();
	}
	
	public Product(int id) {
		super();
		this.id = id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		if (pin != null && pin.length() != 0) {
			this.pin = pin;
		} else {
			this.pin = null;
		}
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

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Float screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenTechnology() {
		return screenTechnology;
	}

	public void setScreenTechnology(String screenTechnology) {
		if (screenTechnology != null && screenTechnology.length() != 0) {
			this.screenTechnology = screenTechnology;
		} else {
			this.screenTechnology = null;
		}
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		if (os != null && os.length() != 0) {
			this.os = os;
		} else {
			this.os = null;
		}
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		if (frontCamera != null && frontCamera.length() != 0) {
			this.frontCamera = frontCamera;
		} else {
			this.frontCamera = null;
		}
	}

	public String getPosteriorCamera() {
		return posteriorCamera;
	}

	public void setPosteriorCamera(String posteriorCamera) {
		if (posteriorCamera != null && posteriorCamera.length() != 0) {
			this.posteriorCamera = posteriorCamera;
		} else {
			this.posteriorCamera = null;
		}
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		if (cpu != null && cpu.length() != 0) {
			this.cpu = cpu;
		} else {
			this.cpu = null;
		}
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		if (ram != null && ram.length() != 0) {
			this.ram = ram;
		} else {
			this.ram = null;
		}
	}

	public String getInternalMemory() {
		return internalMemory;
	}

	public void setInternalMemory(String internalMemory) {
		if (internalMemory != null && internalMemory.length() != 0) {
			this.internalMemory = internalMemory;
		} else {
			this.internalMemory = null;
		}
	}

	public String getMemoryStick() {
		return memoryStick;
	}

	public void setMemoryStick(String memoryStick) {
		if (memoryStick != null && memoryStick.length() != 0) {
			this.memoryStick = memoryStick;
		} else {
			this.memoryStick = null;
		}
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		if (sim != null && sim.length() != 0) {
			this.sim = sim;
		} else {
			this.sim = null;
		}
	}

	public boolean getWaterproof() {
		return waterproof;
	}

	public void setWaterproof(boolean waterproof) {
		this.waterproof = waterproof;
	}

	public boolean getFastCharging() {
		return fastCharging;
	}

	public void setFastCharging(boolean fastCharging) {
		this.fastCharging = fastCharging;
	}

	public boolean getDualCamera() {
		return dualCamera;
	}

	public void setDualCamera(boolean dualCamera) {
		this.dualCamera = dualCamera;
	}

	public boolean getFaceSecurity() {
		return faceSecurity;
	}

	public void setFaceSecurity(boolean faceSecurity) {
		this.faceSecurity = faceSecurity;
	}

	public boolean getFingerprintSecurity() {
		return fingerprintSecurity;
	}

	public void setFingerprintSecurity(boolean fingerprintSecurity) {
		this.fingerprintSecurity = fingerprintSecurity;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public String getMoney() {
		if (this.price == null) {
			return null;
		}

		String str = this.price.toString();
		int count = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			count++;
			if (count == 3 && i != 0) {
				str = str.substring(0, i) + "." + str.substring(i, str.length());
				count = 0;
			}
		}
		return str + "đ";
	}
	
	public String getColorsString() {
		String str = "";
		if (colors == null || colors.size() == 0)
		{
			return str;
		}
		
		int i, n = colors.size() - 1;
		for (i = 0; i < n; i++) {
			str += colors.get(i) + ", ";
		}
		str += colors.get(i);
		return str;
	}
}
