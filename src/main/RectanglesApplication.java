package main;
import model.StateManager;
import view.RectanglesUI;
import javax.xml.bind.JAXBException;

public class RectanglesApplication {
    private StateManager stateManager;
    private RectanglesUI ui;

    public RectanglesApplication() {
        try {
            this.stateManager = new StateManager();
        }
        catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        this.ui = new RectanglesUI(this);
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void run() {
        ui.run();
    }
}
