import java.util.Objects;

public class Vendor {
	
	String name;
	Address address;
	
	public Vendor(String name, Address address) {
		
		this.name = name;
		this.address = address;
	}
	
	/**should there be an empty vendor?
	public Vendor() {
		
		this.name = "MISC";
		this.address = null;
	}*/

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	//what should the toString print?
	@Override
	public String toString() {
		return "Vendor Name: " + name + address.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
			
		if (obj == null) {
			return false;
		}
			
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Vendor other = (Vendor) obj;
		
		return Objects.equals(address, other.address) && Objects.equals(name, other.name);
	}


}
