public class Character {
	public String name;
	public int strength;
	public int health;
	public int defense; 
	
	public static String[] nameList = {"RyuKurae", "Kurae", "Yaoi"};
	
	public Character()
	{
		this.name = nameList[Arena.generator.nextInt(nameList.length)];
	}
	public Character(int str, int spd, int def, int health)
	{
		this();
	}
	
	public int takeDamage( int damage ) {    
		int damageTaken = damage - this.defense;
		this.health -= damageTaken;
		return damageTaken;
	}
	public int attack(Character target){
		int damage = this.strength * 2;
		int damageDealt = target.takeDamage(damage);
		return damageDealt; 
	}
	public boolean isAlive(){
		return this.health > 0;
	}
}