package trafficlight.gui;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> observers = new ArrayList<>();

    // This part is run only once - when the observer are subscribing to the subject

    public void addObservers(Observer o) {
        observers.add(o);
    }

//    public void removeObserver(Observer o) {
//        observers.remove(o);
//    }

    // This part is run every cycle of the light. The subject notifies the observer
    // who is the next in the cue.

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();

        }

    }


}
