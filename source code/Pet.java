package src;
import java.util.Random;

abstract class Pet {
    protected String name;
    protected int hunger; // 0 (Full) - 10 (Very Hungry)
    protected int mood;   // 0 (Happy) - 10 (Grumpy)
    protected Random random;

    public Pet(String name) {
        this.name = name;
        this.hunger = 5; // Neutral state
        this.mood = 5;   // Neutral mood
        this.random = new Random();
    }

    public abstract void talkToPet(String command);

    //randomized behavior of the cat depending on mood and hunger level of the cat
    //if the cat is well fed and had a good sleep, positive reactions are favoured
    protected boolean shouldListen() {
        // Higher values = more obedient
        int obedienceChance = (10 - hunger) * 2 + (10 - mood) * 2;
        return random.nextInt(20) < obedienceChance;
    }
}