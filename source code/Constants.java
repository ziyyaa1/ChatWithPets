package src;

public class Constants {

    public static class CatConstants {

        public static final int IDLE_1 = 0;
        public static final int IDLE_2 = 1;
        public static final int SLEEP = 2;
        public static final int DANCE = 3;
        public static final int SLEEPY = 4;
        public static final int SIT = 6;
        public static final int SAD = 7;
        public static final int EAT = 13;
        public static final int CONFUSED = 14;

        //get total available sprites in a single row to loop
        public static int GetAnimationSize(int catAction){

            switch (catAction) {
                case IDLE_1:
                case IDLE_2:
                    return 10;
                case SLEEP:
                case DANCE:
                    return 4;
                case SLEEPY:
                    return 8;
                case SIT:
                    return 12;
                case SAD:
                    return 9;
                case EAT:
                    return 15;
                case CONFUSED:
                    return 6;
                default:
                    return 1;
            }
        }

        //check if the aanimatio is available or not
        public static boolean IsAnimationAvailable(int catAction){

            switch (catAction) {
                case IDLE_1:
                case IDLE_2:
                case SLEEP:
                case DANCE:
                case SLEEPY:
                case SIT:
                case SAD:
                case EAT:
                case CONFUSED:
                    return true;
                default:
                    return false;
            }
        }

        //get the best speed for every action
        public static int GetBestAnimationSpeed(int catAction){
            switch (catAction) {
                case IDLE_1:
                case IDLE_2:
                    return 5;
                case SLEEP:
                    return 37;
                case DANCE:
                    return 7;
                case SLEEPY:
                    return 10;
                case SIT:
                    return 9;
                case SAD:
                    return 10;
                case EAT:
                    return 9;
                case CONFUSED:
                    return 18;
                default:
                    return 0;
            }
        }
        
    }

}