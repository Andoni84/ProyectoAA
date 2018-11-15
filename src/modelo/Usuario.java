package modelo;

public class Usuario {
	
	private String name;
	private String birth;
	private String city;
	private int abono;
	private int User_id;
	
	// SETTERS,GETTERS,TOSTRING
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getAbono() {
		return abono;
	}
	public void setAbono(int abono) {
		this.abono = abono;
	}
	
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public Usuario() {
	}
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
		builder.append(abono);
		builder.append(", User_id=");
		builder.append(User_id);
		builder.append("]");
		return builder.toString();
	}
	
	
	//METODOS
	
	
	// hasCode para el Usuario_id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + User_id;
		return result;
	}

}
