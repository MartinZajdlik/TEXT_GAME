package martingame.domain;

import martingame.ability.Ability;

import java.util.Map;

public abstract class GameCharacter {

    protected String name;
    protected Map<Ability, Integer> abilities;

    public GameCharacter(String name, Map<Ability, Integer> abilities) {
        this.name = name;
        this.abilities = abilities;
    }

    public void recieveDamage(int damage) {
        abilities.put(Ability.HEALTH, Math.max(0,abilities.get(Ability.HEALTH)- damage));
    }



    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }

    public String getName() {
        return name;
    }
}