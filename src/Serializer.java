import java.io.*;

public class Serializer {
    public static byte[] toBytes(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(baos);
        out.writeObject(object);

        return baos.toByteArray();
    }

    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bais);

        return in.readObject();
    }
}
