import java.sql.*;

public class Employee {

    private static final String URL = "jdbc:mysql://localhost:3306/test4?serverTimezone=UTC";
    private static final String USER = "root1";
    private static final String PASSWORD = "Password12";

    public static void main(String[] args){

        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

            String tableSql = "CREATE TABLE IF NOT EXISTS employees"
                    + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                    + "position varchar(30), salary double)";
            stmt.execute(tableSql);//is used to add table to the database.....
            //executeUpdate() is for updating or inserting into a table.
            //executeQuery() is for SELECT instructions
/*
            String insertSql = "INSERT INTO employees(name, position, salary)"
                    + " VALUES('Adetokunbo Ige', 'Officer 3', 280000.00)";
            stmt.executeUpdate(insertSql); */

            String deleteSql = "DELETE FROM test4.employees WHERE emp_id = 9";
            //stmt.executeUpdate(deleteSql);

            String updatePositionSql = "UPDATE employees SET position = ? AND SET salary = ? WHERE emp_id = ?";
            String sql;
            PreparedStatement pstmt = conn.prepareStatement(updatePositionSql);
            pstmt.setString(1, "Officer 4");
            pstmt.setDouble(2, 350000);
            pstmt.setInt(3, 10);
           // pstmt.executeUpdate();

            String selectSql = "SELECT * FROM test4.employees";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int countColumn = resultSetMetaData.getColumnCount();

            while (resultSet.next()){
                for (int i = 1; i <= countColumn; ++i){
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
