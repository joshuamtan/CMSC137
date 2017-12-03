import java.io.Serializable;

public class ConnectPacket implements Serializable{
    private String name;

    public ConnectPacket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
