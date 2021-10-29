package group1.interfaces;

public interface Observable
{
    public void registerObserver(Observer o);
    public void notifyObservers();
    public void unregisterObserver(Observer o);
}
