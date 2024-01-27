package edu.school21.chat.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Message {
    private long id;

    private long authorMessage;
    private User author;

    private long idRoom;
    private Chat chat;

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
        if (chat != null ? !chat.equals(message.chat) : message.chat != null) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        return date != null ? date.equals(message.date) : message.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (authorMessage ^ (authorMessage >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (int) (idRoom ^ (idRoom >>> 32));
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", authorMessage=" + authorMessage +
                ", author=" + author +
                ", idRoom=" + idRoom +
                ", chat=" + chat +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
