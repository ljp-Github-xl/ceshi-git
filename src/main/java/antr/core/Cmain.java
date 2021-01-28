package antr.core;

public class Cmain {
    public static void main(String[] args) {
        String sql = "insert into a select * from a";
        new AntlrFilter().checkAndGetTableNames(sql);
    }
}
