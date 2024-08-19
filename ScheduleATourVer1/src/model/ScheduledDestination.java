package model;

import java.util.ArrayList;

public class ScheduledDestination {
	public Tour tour;
	public ArrayList<ScheduledService> schedulelist;
	
	public ScheduledDestination() {
		schedulelist = new ArrayList<>();
	}
	public void addSchedule(ScheduledService scheser) {
		schedulelist.add(scheser);
	}
	
	public ScheduledService getSchedule(int index) {
		if (index >= 0 && index < schedulelist.size()) {
            return schedulelist.get(index);
        } 
		else {
            System.out.println("Index out of bounds");
            return null;
        }
	}
	public int getSize() {
        return schedulelist.size();
    }
	
	public void removeSchedule(int index) {
        if (index >= 0 && index < schedulelist.size()) {
        	schedulelist.remove(index);
        } else {
            System.out.println("Index out of bounds");
        }
    }
	
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	
	public boolean CheckIfEmpty() {
        return schedulelist.isEmpty();
    }
}


