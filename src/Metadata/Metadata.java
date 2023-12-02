package src.Metadata;

import src.Connectivity.Database_Connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metadata {

    public static void main(String[] args) {
        // Establish a database connection
        Connection connection = Database_Connection.connect();

        try {
            // Get the DatabaseMetaData object
            DatabaseMetaData metaData = connection.getMetaData();

            // Print information about the database
            System.out.println("Database Name: " + metaData.getDatabaseProductName());
            System.out.println("Database Version: " + metaData.getDatabaseProductVersion());

            // Get information about tables
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            System.out.println("\nTables:");

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("\nTable: " + tableName);

                // Get information about columns in the current table
                ResultSet columns = metaData.getColumns(null, null, tableName, null);

                System.out.println("Columns:");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String dataType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    boolean isNullable = (columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable);

                    System.out.println("  Column Name: " + columnName);
                    System.out.println("    Type: " + dataType);
                    System.out.println("    Size: " + columnSize);
                    System.out.println("    Nullable: " + (isNullable ? "Yes" : "No"));
                    System.out.println("------");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("\nConnection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}