package entities.OutsideEntities.Monsters;

public abstract class Boss extends Monster{
    // Boss class
    public Boss(String name, int health, int damage, double physical_def, double magical_def, boolean flying, int gold_drop) {
        super(name, health, damage, physical_def, magical_def, flying, gold_drop);
    }

}
