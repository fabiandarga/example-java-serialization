package de.fabiandarga;

import java.io.*;

public class SerializationUtils {


    public static byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream bOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bOut)) {
            out.writeObject(obj);
            return bOut.toByteArray();
        }
    }

    public static <T> T deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream bIn = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(bIn)) {
            //noinspection unchecked
            return (T) in.readObject();
        }
    }
}
