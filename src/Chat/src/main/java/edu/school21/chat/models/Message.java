package edu.school21.chat.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class Message {
    private long id;

    private long authorMessage;
    private Optional<User> author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorMessage() {
        return authorMessage;
    }

    public void setAuthorMessage(long authorMessage) {
        this.authorMessage = authorMessage;
    }

    public Optional<User> getAuthor() {
        return author;
    }

    public void setAuthor(Optional<User> author) {
        this.author = author;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public Optional<Chat> getChat() {
        return room;
    }

    public void setChat(Optional<Chat> chat) {
        this.room = chat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    private long idRoom;
    private Optional<Chat> room;

    private String text;
    private Timestamp date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (authorMessage != message.authorMessage) return false;
        if (idRoom != message.idRoom) return false;
        if (author != null ? !author.equals(message.author) : message.author != null) return false;
        if (room != null ? !room.equals(message.room) : message.room != null) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        return date != null ? date.equals(message.date) : message.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (authorMessage ^ (authorMessage >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (int) (idRoom ^ (idRoom >>> 32));
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "\n\tid=" + id +
                ",\n\tauthor=" + author.get() +
                ",\n\tchat=" + room.get() +
                ",\n\ttext='" + text + '\'' +
                ",\n\tdate=" + date +
                '\n' +
                '}';
    }
}
