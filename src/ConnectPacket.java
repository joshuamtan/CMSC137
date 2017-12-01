import java.io.Serializable;

public class ConnectPacket implements Serializable{
    private String name;

    public ConnectPacket(String name) {
        this.name = name;
    }
}
