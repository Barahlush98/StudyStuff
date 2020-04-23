package model;
import javax.xml.bind.*;
import java.io.File;
public class StateManager {
    public static final String STATE_FILENAME = "state.xml";
    private File file;
    private JAXBContext context;

    public StateManager() throws JAXBException {
        this.file = new File(STATE_FILENAME);
        this.context = JAXBContext.newInstance(RectanglesState.class);
    }

    public Rectangle getRectangle1State() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return ((RectanglesState)unmarshaller.unmarshal(file)).getRectangle1();
    }

    public Rectangle getRectangle2State() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return ((RectanglesState)unmarshaller.unmarshal(file)).getRectangle2();
    }

    public double getIntersectionAreaState() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return ((RectanglesState)unmarshaller.unmarshal(file)).getIntersectionArea();
    }

    public void setState(Rectangle r1, Rectangle r2) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new RectanglesState(r1, r2), file);
    }
}
