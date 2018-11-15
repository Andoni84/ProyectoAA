package modelo;

import java.sql.Date;

public class Usuario {
	
	/**
	 * Clase Usuario
	 * 
	 * Crea objetos Usuario y codigo unico.
	 * 
	 * 15/11/2018
	 *
	 * @author Sergio
	 * @version v1.2
	 */

	
	//Clase Usuario con atributos name,fecha nacimiento,city,plan abono,codigo user_id. 
	//El codigo se puede generarar automaticamente.
	
	
	private String name;
	private Date birth;
	private String city;
	private int plan;
	private int user_id;
	
	// SETTERS,GETTERS,TOSTRING
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public Usuario() {
	}

	
	public Usuario(String name, Date birth, String city, int plan) {
		super();
		this.name = name;
		this.birth = birth;
		this.city = city;
		this.plan = plan;
		this.user_id = this.hashCode();
	}
	
	public Usuario(String name, Date birth){
		super();
		this.name = name;
		this.birth = birth;
	}
	
	
	
	public Usuario(String name, Date birth, String city, int plan, int user_id) {
		super();
		this.name = name;
		this.birth = birth;
		this.city = city;
		this.plan = plan;
		this.user_id = user_id;
	}
	//METODOS
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [name=");
		builder.append(name);
		builder.append(", birth=");
		builder.append(birth);
		builder.append(", city=");
		builder.append(city);
		builder.append(", abono=");
		builder.append(plan);
		builder.append(", User_id=");
		builder.append(user_id);
		builder.append("]");
		return builder.toString();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	


}
