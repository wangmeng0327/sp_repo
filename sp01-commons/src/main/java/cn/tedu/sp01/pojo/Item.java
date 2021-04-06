package cn.tedu.sp01.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5084093690801378062L;
	private Integer id;
	private String name;
	private Integer number;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Item() {
		super();
	}
	public Item(Integer id, String name, Integer number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
	
	
}
