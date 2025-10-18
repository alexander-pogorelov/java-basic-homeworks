package ru.otus.java.basic.homeworks.homework13;

import java.io.*;
import java.net.Socket;

public class DataStreamClient implements Closeable {
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public DataStreamClient(Socket socket) throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void send(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    public String read() throws IOException {
        return inputStream.readUTF();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
