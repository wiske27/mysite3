package kr.co.dhflour.mysite.vo;

public class GridVo {
	private Long no;
	private String name;
	private Integer age;
	private Integer country;
	private String address;
	private String married;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	
	@Override
	public String toString() {
		return "GridVo [no=" + no + ", name=" + name + ", age=" + age + ", country=" + country + ", address=" + address
				+ ", married=" + married + "]";
	}
	
	
}
