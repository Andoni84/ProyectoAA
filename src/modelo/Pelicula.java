package modelo;

public class Pelicula {
	
	/**
	 * Clase Pelicula
	 * 
	 * Crea objetos pelicula y un codigo unico.
	 * 
	 * 15/11/2018
	 *
	 * @author Andoni
	 * @version v1
	 */

	
	// Clase Peliculas con atributos name,year,genre,views,rating,codigo.
	// El codigo se puede generarar automaticamente.
	
	private String name;
	private int year;
	private int idGenre;
	private int views;
	private double rating;
	private int isbn;
	
	
	
	
	public Pelicula() {
	}

	public Pelicula(String name, int year, int idGenre, int views, double rating) {
		this.name = name;
		this.year = year;
		this.idGenre = idGenre;
		this.views = views;
		this.rating = rating;
		this.isbn= hashCode();
	}
	
	public Pelicula(String name, int year, int idGenre, int views, double rating, int isbn) {
		super();
		this.name = name;
		this.year = year;
		this.idGenre = idGenre;
		this.views = views;
		this.rating = rating;
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return "Pelicula [name=" + name + ", year=" + year + ", genre=" + idGenre + ", views=" + views + ", rating="
				+ rating + ", isbn=" + isbn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		result = prime * result + idGenre;
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
		Pelicula other = (Pelicula) obj;
		if (idGenre != other.idGenre)
			return false;
		if (isbn != other.isbn)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getidGenre() {
		return idGenre;
	}
	public void setidGenre(String genre) {
		this.idGenre = idGenre;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	

}
