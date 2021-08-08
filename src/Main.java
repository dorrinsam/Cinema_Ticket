import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        Statement statement = connection.createStatement();
        System.out.println("به سامانه مدیریت سینماهای تبریز خوش آمدید");
        System.out.println();
        System.out.println("لطفا تمامی مقادیر را بدون هیچگونه فاصله ای وارد نمایید");
        System.out.println("برای اضافه کردن سالن عدد 1 را وارد کنید");
        System.out.println("برای اضافه کردن فیلم عدد 2 را وارد کنید");
        System.out.println("برای خرید بلیط عدد 3 را وارد کنید");
        System.out.println("برای رهگیری بلیط عدد 4 را وارد کنید");
        System.out.println("برای رویت سود سینمای مورد نظر عدد 5 را وارد کنید");
        System.out.println("برای رویت سود فیلم مورد نظر عدد 6 را وارد کنید");
        System.out.println("برای رویت سود فروش فیلم در سینمای مورد نظر عدد 7 را وارد کنید");
        System.out.println("برای رویت پرفروش ترین سینما عدد 8 را وارد کنید");
        System.out.println("برای رویت پرفروش ترین فیلم عدد 9 را وارد کنید");
        System.out.println("برای رویت پرمخاطب ترین زمان های روز عدد 10 را وارد کنید");
        System.out.println("برای رویت لیست پخش سینمای مورد نظر عدد 11 را وارد کنید");
        System.out.println("برای حذف فیلم از سینما ها عدد 12 را وارد کنید");
        System.out.println("برای حذف سینما عدد 13 را وارد کنید");
        System.out.println("برای حذف بلیط عدد 14 را وارد کنید");
        System.out.println("برای خروج عدد 0 را وارد کنید");





    loop:while (true) {
            String order = scanner.next();
            switch (order) {
                case "1":
                    System.out.println("نام سینما را وارد کنید");
                    String cinema_name = scanner.next();
                    add_cinema(cinema_name, statement);
                    break;

                case "2":
                    System.out.println("نام فیلم را وارد کنید");
                    String film_name = scanner.next();
                    System.out.println("نام سینما را وارد کنید");
                    String cinema = scanner.next();
                    System.out.println("گنجایش سالن برای این فیلم را وارد کنید");
                    int cap = scanner.nextInt();
                    System.out.println("قیمت این فیلم را وارد کنید");
                    int price = scanner.nextInt();
                    System.out.println("ساعت این فیلم را وارد کنید");
                    int time = scanner.nextInt();
                    System.out.println("روز این فیلم را وارد کنید");
                    String day = scanner.next();
                    add_film(cinema, film_name, time, day, cap, price, statement, connection);
                    break;
                case "3":
                    System.out.println("نام خود را وارد کنید");
                    String username = scanner.next();
                    System.out.println("نام سینما را وارد کنید");
                    String tic_cinema = scanner.next();
                    System.out.println("نام فیلم را وارد کنید");
                    String tic_film = scanner.next();
                    System.out.println("بلیط را برای چه روزی میخواهید؟");
                    String tic_day = scanner.next();
                    System.out.println("بلیط را برای چه ساعتی میخواهید؟");
                    int tic_time = scanner.nextInt();
                    System.out.println("چه تعداد بلیط میخواهید؟");
                    int tic_num = scanner.nextInt();
                    add_ticket(username, tic_day, tic_time, tic_film, tic_cinema, tic_num, statement);
                    break;
                case "4":
                    System.out.println("نام خود را وارد کنید");
                    go_to_ticket(statement,scanner.next());
                    break;
                case "5":
                    System.out.println("نام سینما را وارد کنید");
                    System.out.println(sood_ticket_cinema(scanner.next(), statement));
                    break;
                case "6":
                    System.out.println("نام فیلم را وارد کنید");
                    System.out.println(film_sood(scanner.next(), statement));
                    break;
                case "7":
                    System.out.println("نام سینما را وارد کنید");
                    String cinemasood = scanner.next();
                    System.out.println("نام فیلم را وارد کنید");
                    System.out.println(cinema_film_sood(cinemasood, scanner.next(), statement));
                    break;
                case "8":
                    System.out.println(best_cinema(statement));
                    break;
                case "9":
                    System.out.println(best_film(statement));
                    break;
                case "10":
                    System.out.println(best_time(statement));
                    break;
                case "11":
                    System.out.println("نام سینما را وارد کنید");
                    String cinemaplay = scanner.next();
                    for (int i = 0; i < playlist(statement, cinemaplay).size(); i++) {
                        System.out.println("playlist " + (i + 1));
                        System.out.println();
                        System.out.println("نام فیلم   " + playlist(statement, cinemaplay).get(i).getFilmname());
                        System.out.println("روز پخش   " + playlist(statement, cinemaplay).get(i).getDay());
                        System.out.println("ساعت پخش   " + playlist(statement, cinemaplay).get(i).getTime());
                        System.out.println("ظرفیت باقیمانده   " + playlist(statement, cinemaplay).get(i).getCapacity());
                        System.out.println();
                    }
                    break;
                case "12":
                    System.out.println("نام فیلم را وارد کنید");
                    delete_film(statement, scanner.next());
                    break;
                case "13":
                    System.out.println("نام سینما را وارد کنید");
                    delete_cinema(statement, scanner.next());
                    break;

                case "14":
                    System.out.println("نام فرد را وارد کنید");
                    String del_name=scanner.next();
                    System.out.println("بلیط کدام سینما را میخواهد حذف کنید");
                    String del_cinema=scanner.next();
                    System.out.println("بلیط کدام فیلم را میخواهید حذف کنید");
                    String del_film=scanner.next();
                    System.out.println("بلیط کدام روز را میخواهید حذف کنید؟");
                    String del_day=scanner.next();
                    System.out.println("بلیط کدام ساعت را میخواهید حذف کنید؟");
                    int del_time=scanner.nextInt();
                    delete_ticket(statement,del_name,del_cinema,del_film,del_day,del_time);
                    break;

                case "0":
                   break loop;



            }

        }

    }

    private static void add_cinema(String name, Statement statement) {

        try {
            boolean is = false;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sqlite_master WHERE type = 'table'\n" +
                    "  AND NOT (name LIKE 'sqlite_sequence' OR\n" +
                    "           name LIKE 'android_metadata') ");
            while (resultSet.next()) {
                if (resultSet.getString("name").equalsIgnoreCase(name)) {
                    is = true;
                }
            }
            if (is) {
                System.out.println("این سالن از قبل تعریف شده است");
            } else {
                statement.execute("CREATE TABLE " + name + " ( " +
                        "id INTEGER PRIMARY KEY ," +
                        "film TEXT ," +
                        "day TEXT ," +
                        "time INTEGER ," +
                        "capacity INTEGER ," +
                        "price INTEGER ," +
                        "sell INTEGER ," +
                        "allsell INTEGER )");
                System.out.println("سالن مورد نظر اضافه شد");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void add_film(String cinema, String film_name, int time, String day, int capacity, int price, Statement statement, Connection connection) {
        boolean is_queue = false;
        List<String> table_names = new ArrayList<>();
        List<Integer> times = new ArrayList<>();

        for (int i = 9; i <= 19; i++) {
            if (i != 14) {
                times.add(i);
            }
        }
        try {
            ResultSet tname = connection.getMetaData().getTables(null, null, null, null);
            while (tname.next()) {
                table_names.add(tname.getString("TABLE_NAME"));
            }
            if (!table_names.contains(cinema)) {
                System.out.println("سینمای مورد نظر در لیست وجود ندارد");
            } else {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + cinema);
                while (resultSet.next()) {
                    if (resultSet.getString("day").equalsIgnoreCase(day) && resultSet.getInt("time") == time) {
                        is_queue = true;
                    }
                }
                if (!times.contains(time)) {
                    System.out.println("زمان انتخاب شده غبر مجاز است");
                } else if (is_queue) {
                    System.out.println("در این سینما،در این زمان صف پخش پر است");
                } else {
                    statement.executeUpdate("INSERT INTO " + cinema + " (film,time,day,capacity,price) VALUES ( '" + film_name + "'," + time + ",'" + day + "'," + capacity + "," + price + ")");
                    System.out.println("فیلم مورد نظر در صف پخش قرار گرفت");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void add_ticket(String name, String day, int time, String film_name, String cinema, int tedad, Statement statement) {
        int count = 0;
        int count2 = 0;
        int cap = 0;
        int sell = 0;
        int perice = 0;
        boolean perm = false;

        try {

            ResultSet res = statement.executeQuery("SELECT COUNT(*) FROM " + cinema);
            while (res.next()) {
                count = res.getInt(1);
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + cinema + " WHERE day = '" + day + "' AND time =" + time);
            while (resultSet.next()) {
                if (count + tedad >= resultSet.getInt("capacity")) {
                    perm = true;
                }
            }
            ResultSet rs = statement.executeQuery("SELECT * FROM ticket WHERE name = '" + name + "' AND time = " + time + " AND day= '" + day + "'");
            while (rs.next()) {
                count2 = rs.getInt(1);
            }

            if (perm) {
                System.out.println("ظرفیت سالن در این زمان پر است.");
            } else if (count2 > 0) {
                System.out.println("بلیط این فرد در این زمان در سینمای دیگری صادر شده است");
            } else {

                statement.executeUpdate("INSERT INTO ticket (name,day,time,film,cinema,number) VALUES ('" + name + "','" + day
                        + "'," + time + ",'" + film_name + "','" + cinema + "'," + tedad + ")");
                System.out.println("بلیط مورد نظر صادر گردید");
                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM " + cinema + " WHERE film ='" + film_name + "' AND " +
                        " day ='" + day + "' AND time = " + time + " ORDER BY rowid DESC LIMIT 1");
                while (resultSet1.next()) {
                    cap = resultSet1.getInt("capacity");
                }
                cap = cap - tedad;
                statement.executeUpdate("UPDATE " + cinema + " SET capacity = " + cap + " WHERE film='" + film_name + "' AND " +
                        " day ='" + day + "' AND time = '" + time + "'");
                ResultSet resultSet2 = statement.executeQuery("SELECT * FROM " + cinema + " WHERE film ='" + film_name + "' AND " +
                        " day ='" + day + "' AND time = " + time + " ORDER BY rowid DESC LIMIT 1");
                while (resultSet2.next()) {
                    perice = resultSet1.getInt("price");
                    sell = resultSet.getInt("sell");
                }
                sell += perice * tedad;
                statement.executeUpdate("UPDATE " + cinema + " SET sell = " + sell + " WHERE film='" + film_name + "' AND " +
                        " day ='" + day + "' AND time = '" + time + "'");
                statement.executeUpdate("UPDATE " + cinema + " SET allsell = " + sell + " WHERE id = 1");

            }


        } catch (SQLException e) {
            System.out.println("خطایی رخ داده است");
        }
    }

    private static int sood_ticket_cinema(String cinema, Statement statement) throws SQLException {
        int sell = 0;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM '" + cinema + "'");
        while (resultSet.next()) {
            sell += resultSet.getInt("sell");
        }
        return sell;
    }

    private static int film_sood(String film_name, Statement statement) throws SQLException {
        int sells = 0;
        List<String> cinema = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket WHERE film= '" + film_name + "'");
        while (resultSet.next()) {
            cinema.add(resultSet.getString("cinema"));
        }
        for (int i = 0; i < cinema.size(); i++) {
            ResultSet set = statement.executeQuery("SELECT * FROM '" + cinema.get(0) + "' WHERE film= '" + film_name + "'");
            while (set.next()) {
                sells += set.getInt("sell");
            }
        }
        return sells;
    }

    private static int cinema_film_sood(String cinema, String film, Statement statement) throws SQLException {
        int sell = 0;
        ResultSet set = statement.executeQuery("SELECT * FROM '" + cinema + "' WHERE film = '" + film + "'");
        while (set.next()) {
            sell += set.getInt("sell");
        }
        return sell;
    }

    private static String best_cinema(Statement statement) throws SQLException {
        List<String> cinema = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT * FROM ticket");
        while (set.next()) {
            cinema.add(set.getString("cinema"));
        }

        return findPopular(cinema);
    }

    private static String best_film(Statement statement) throws SQLException {
        List<String> films = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT * FROM ticket");
        while (set.next()) {
            films.add(set.getString("film"));
        }

        return findPopular(films);
    }

    private static String best_time(Statement statement) throws SQLException {
        List<String> times = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT * FROM ticket");
        while (set.next()) {
            times.add(String.valueOf(set.getInt("time")));
        }

        return findPopular(times);
    }

    private static List<PlayList> playlist(Statement statement, String cinema_name) throws SQLException {
        List<PlayList> playLists = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT * FROM '" + cinema_name + "'");
        while (set.next()) {
            String filmname = set.getString("film");
            String day = set.getString("day");
            String time = String.valueOf(set.getInt("time"));
            String capacity = String.valueOf(set.getInt("capacity"));
            PlayList list = new PlayList(filmname, day, time, capacity);
            playLists.add(list);
        }
        return playLists;
    }

    private static void delete_film(Statement statement, String film_name) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT cinema FROM ticket WHERE film = '" + film_name + "'");
        while (resultSet.next()) {
            String cinema = resultSet.getString("cinema");
            statement.executeUpdate("DELETE FROM '" + cinema + "' WHERE film= '" + film_name + "'");
            System.out.println("این فیلم از سینماها حذف شد");
        }
        statement.executeUpdate("DELETE FROM ticket WHERE film= '" + film_name + "'");
        System.out.println("بلیط های مربوط به این فیلم حذف شدند");
    }

    private static void delete_cinema(Statement statement, String cinema_name) throws SQLException {
        statement.executeUpdate("DROP TABLE '" + cinema_name + "'");
        System.out.println("سینمای مورد نظر حذف شد");
        statement.executeUpdate("DELETE FROM ticket WHERE cinema = '" + cinema_name + "'");
        System.out.println("بلیط های مربوط به این سینما حذف شدند");

    }

    private static void delete_ticket(Statement statement, String name,String cinema,String film_name,String day,int time) throws SQLException {
        int number=0;
        int cap;
        int new_cap=0;
        ResultSet set = statement.executeQuery("SELECT * FROM ticket WHERE name='" + name + "' AND film = '"+film_name+"' AND cinema = '"+cinema+"' AND time= '"+time+"' AND day ='"+day+"'");
        while (set.next()) {
            number=set.getInt("number");
        }
        statement.executeUpdate("DELETE FROM ticket WHERE name = '"+name+"'");
        System.out.println("بلیط مورد نظر حذف گردید");

        ResultSet resultSet=statement.executeQuery("SELECT * FROM '"+cinema+"' WHERE film = '"+film_name+"' AND day='"+day+"' AND time='"+time+"'");
        while (resultSet.next()){
            cap=resultSet.getInt("capacity");
            new_cap=cap+number;
        }
        statement.executeUpdate("UPDATE '"+cinema+"' SET capacity ='"+new_cap+"'WHERE film = '"+film_name+"' AND day='"+day+"' AND time='"+time+"'");
    }

    private static void go_to_ticket(Statement statement,String name) throws SQLException{
        ResultSet resultSet=statement.executeQuery("SELECT * FROM ticket WHERE name='"+name+"'");
        while (resultSet.next()){
            System.out.println("سینمای پخش فیلم   "+resultSet.getString("cinema"));
            System.out.println("نام فیلم   "+resultSet.getString("film"));
            System.out.println("روز پخش فیلم   "+resultSet.getString("day"));
            System.out.println("زمان پخش فیلم   "+resultSet.getInt("time"));
            System.out.println("تعداد بلیط ها   "+resultSet.getInt("number"));
        }
    }


    public static String findPopular(List<String> list) {

        Map<String, Integer> stringsCount = new HashMap<String, Integer>();
        for (String string : list) {
            if (string.length() > 0) {
                string = string.toLowerCase();
                Integer count = stringsCount.get(string);
                if (count == null) count = 0;
                count++;
                stringsCount.put(string, count);
            }
        }
        Map.Entry<String, Integer> mostRepeated = null;
        for (Map.Entry<String, Integer> e : stringsCount.entrySet()) {
            if (mostRepeated == null || mostRepeated.getValue() < e.getValue())
                mostRepeated = e;
        }
        try {
            return mostRepeated.getKey();
        } catch (NullPointerException e) {
            return "";
        }

    }
}
