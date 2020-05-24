package grit.android.androidsql;

public class Philosopher {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public int getDied() {
        return died;
    }

    public void setDied(int died) {
        this.died = died;
    }

    private int id;
    private String name;
    private int born;
    private int died;


}
