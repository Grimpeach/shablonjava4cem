package practice8.Template;

class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started.");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished.");
    }
}

class Basketball extends Game {
    @Override
    void initialize() {
        System.out.println("Basketball Game Initialized.");
    }

    @Override
    void startPlay() {
        System.out.println("Basketball Game Started.");
    }

    @Override
    void endPlay() {
        System.out.println("Basketball Game Finished.");
    }
}
