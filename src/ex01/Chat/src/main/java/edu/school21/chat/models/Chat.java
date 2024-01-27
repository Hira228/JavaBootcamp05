package edu.school21.chat.models;

import java.util.List;

public class Chat {
    private long id;
    private String nameChat;

    private long idOwner;
    private User owner;

    private List<Message> messageList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != chat.id) return false;
        if (idOwner != chat.idOwner) return false;
        if (nameChat != null ? !nameChat.equals(chat.nameChat) : chat.nameChat != null) return false;
        if (owner != null ? !owner.equals(chat.owner) : chat.owner != null) return false;
        return messageList != null ? messageList.equals(chat.messageList) : chat.messageList == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameChat != null ? nameChat.hashCode() : 0);
        result = 31 * result + (int) (idOwner ^ (idOwner >>> 32));
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (messageList != null ? messageList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", nameChat='" + nameChat + '\'' +
                ", idOwner=" + idOwner +
                ", owner=" + owner +
                ", messageList=" + messageList +
                '}';
    }
}
