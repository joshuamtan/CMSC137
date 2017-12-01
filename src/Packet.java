import java.io.Serializable;

public class Packet implements Serializable {
    private String type;
    private Object actual;

    public Packet(String type, Object actual) {
        this.type = type;
        this.actual = actual;
    }

    public String getType() {
        return type;
    }

    public Object getActual() {
        return actual;
    }
}
