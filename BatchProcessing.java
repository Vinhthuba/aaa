import java.sql.*;
public class BatchProcessing {
    public static void main(String[] args) {
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop","root",""
                );
                Statement stmt =conn.createStatement();
        )
        {
              conn.setAutoCommit(false);
              stmt.addBatch("insert into books values (8001,'Java ABC','Kevin Jones',1.1,99)");
              stmt.addBatch("insert into books values (8002,'Java XYZ','Kevin Jones',1.1,99)");
              stmt.addBatch("update books set price = 11.11 where id=8001 or id=8002");
              int[] returnCodes = stmt.executeBatch();

            System.out.println("Return codes are: ");
            for (int code:returnCodes)
            {
                System.out.println(code+ " ");
            }
            System.out.println();
            conn.commit();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
