package observer;

import view.Homepage;

public class StudentObserver implements Observer {
    private Homepage homepage;

    public StudentObserver(Homepage homepage) {
        this.homepage = homepage;
    }

    @Override
    public void update() {
        homepage.refreshTable();
    }
}

