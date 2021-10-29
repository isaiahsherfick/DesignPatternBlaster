package design_pattern_blaster.interfaces;

public interface Observable
{
    public void registerObserver(Observer o);
    public void notifyObservers();
    public void unregisterObserver(Observer o);
}
