package model;

import java.util.Objects;

// YELLOW
// В конструкторах лишние отступы, лучше стараться их избегать и придерживаться чек-стайла
public class Subtask extends Task {
    private long epicID;

    public Subtask(String name, String description, String status, long epicID) {
        super(name, description, status);
        this.epicID = epicID;


    }

    public Subtask(long ID, String name, String description, String status, long epicID) {
        super(ID, name, description, status);
        this.ID = ID;
        this.epicID = epicID;


    }

    public long getEpicID() {
        return epicID;
    }

    public void setEpicID(long epicID) {
        this.epicID = epicID;
    }


    @Override
    public String toString() {
        return "Subtask{" +
                "epicID=" + epicID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ID=" + ID +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicID == subtask.epicID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicID);
    }
}
