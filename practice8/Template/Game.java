package practice8.Template;

abstract class Game {
    // Шаблонный метод, который определяет общий алгоритм
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    // Методы, которые нужно реализовать в подклассах
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}