package src;
import java.util.*;
import static src.Constants.CatConstants.*;

class Cat extends Pet{
    private SimulatorAnimation catAnimation;

    public Cat(String name, SimulatorAnimation catAnimation) {
        super(name);
        this.catAnimation = catAnimation;
    }

    //handles commands and generates cat response
    @Override
    public void talkToPet(String command) {
        System.out.println("\nYou: " + command);

        // Simulate whether the cat listens or not

        // Perform the detected action
        switch (command) 
        {
            case "eat_command":
                eat();
                break;
            case "sleep_command":
                sleep();
                break;
            case "play_command":

                if (!shouldListen()) 
                {
                    System.out.println(name + " ignores you... 😾");
                    return;
                }
                play();
                break;
            case "sit_command":
                
                if (!shouldListen()) 
                {
                    System.out.println(name + " ignores you... 😾");
                    return;
                }
                sit();
                break;
            default:
                System.out.println(" Meaowww?? 🤨 (Unrecognized command)");
                catAnimation.setThought(" Meaowww?? 🤨", 10);
                break;
        }
    }


    private void eat() {
        if (hunger > 2) 
        {
            hunger -= 2;
            mood += 1;
            System.out.println(name + " eats happily! 😸 (Hunger: " + hunger + ", Mood: " + mood + ")");

            catAnimation.setAnimation(EAT);
            catAnimation.resetAnimationTimer(10);
            catAnimation.setThought("Nom nom num 🍖🍖🍖", 10);
        } else {
            System.out.println(name + " is full and refuses to eat more. 😼");
            catAnimation.setThought(name + " is full and refuses to eat more. 😼", 10);
        }
    }

    private void sleep() {
        if (mood < 8) 
        {
            mood += 2;
            hunger += 3;
            System.out.println(name + " takes a cozy nap. 💤 (Hunger: " + hunger + ", Mood: " + mood + ")");

            catAnimation.setAnimation(SLEEP);
            catAnimation.resetAnimationTimer(20);
            catAnimation.setThought(name + " takes a cozy nap. 💤", 10);
        } else {
            System.out.println(name + " is not tired and ignores you. 🙀");
            catAnimation.setAnimation(SIT);
            catAnimation.setThought(name + " is not tired and ignores you. 🙀", 10);
        }
    }

    private void play() {
        if (mood > 3 && hunger < 8) 
        {
            mood -= 2;
            hunger += 2;
            System.out.println(name + " chases a toy mouse! 🐭 (Hunger: " + hunger + ", Mood: " + mood + ")");

            catAnimation.setAnimation(DANCE);
        } else {
            System.out.println(name + " is too tired or hungry to play. 😿");
        }
    }

    private void sit() {

        if (mood > 3 && hunger < 8) 
        {
            mood -= 3;
            hunger += 1;
            System.out.println(name + " sits patiently (Hunger: " + hunger + ", Mood: " + mood + ")");

            catAnimation.setAnimation(SIT);
            catAnimation.resetAnimationTimer(10);
        } else {
            System.out.println(name + " is too tired or hungry to listen to you. 😿");
        }
    }
}