public class PlayList {
    String filmname;
    String Day;
    String time;
    String capacity;

    public PlayList(String filmname, String day, String time, String capacity) {
        this.filmname = filmname;
        Day = day;
        this.time = time;
        this.capacity = capacity;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
