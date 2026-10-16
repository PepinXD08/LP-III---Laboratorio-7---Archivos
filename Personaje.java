package juancho;

public class Personaje {
	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;
	private int alcance;

	public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
		this.nombre = nombre;
		setVida(vida);
		setAtaque(ataque);
		setDefensa(defensa);
		setAlcance(alcance);
	}

	public String getNombre() {
		return nombre; 
	}
	     
	public void setNombre(String nombre) { 
		this.nombre = nombre; 
	}

	public int getVida() { return vida; }
	public void setVida(int vida) {
		if (vida > 0) {
			this.vida = vida;
		}
		else throw new IllegalArgumentException("La vida debe ser mayor a 0");
	}

	public int getAtaque() { 
		return ataque; 
	}
	
	public void setAtaque(int ataque) {
		if (ataque > 0) {
			this.ataque = ataque;
		}
		else throw new IllegalArgumentException("El ataque debe ser mayor a 0");
	}

	public int getDefensa() { 
		return defensa; 
	}
	
	public void setDefensa(int defensa) {
		if (defensa > 0) {
			this.defensa = defensa;
		}
		else throw new IllegalArgumentException("La defensa debe ser mayor a 0");
	}

	public int getAlcance() { 
		return alcance; 
	}
	
	public void setAlcance(int alcance) {
		if (alcance > 0) {
			this.alcance = alcance;
		}
		else throw new IllegalArgumentException("El alcance debe ser mayor a 0");
	}

	@Override
	public String toString() {
		return "Personaje: " + nombre + " | Vida: " + vida + " | Ataque: " + ataque + " | Defensa: " + defensa + " | Alcance: " + alcance;
	}
}
