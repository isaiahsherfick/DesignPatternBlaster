package group1.megaman_dp.interfaces;

public interface Observable
{
    public void registerObserver(Observer o);
    public void notifyObservers();
    public void unregisterObserver(Observer o);
}
