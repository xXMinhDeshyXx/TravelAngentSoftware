package model;

import java.util.ArrayList;

public class ScheduledService {
    public ArrayList<Service> servicelist;
    public Destination destination;
    
    public ScheduledService() {
        servicelist = new ArrayList<>();
    }
    
    public void addSer(Service service) {
        servicelist.add(service);
    }
    
    public Service getSer(int index) {
        if (index >= 0 && index < servicelist.size()) {
            return servicelist.get(index);
        } else {
            System.out.println("Index out of bounds");
            return null;
        }
    }
    
    public int getSize() {
        return servicelist.size();
    }
    
    public void removeSer(int index) {
        if (index >= 0 && index < servicelist.size()) {
            servicelist.remove(index);
        } else {
            System.out.println("Index out of bounds");
        }
    }
    
    public boolean CheckIfEmpty() {
        return servicelist.isEmpty();
    }
    
    public void addDes(Destination destination) {
        this.destination = destination;
    }
    public Destination getDes() {
    	return destination;
    }
}
