package src.Methods;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.Connectivity.Database_Connection;

public class BooksInfo_Method {

    public static void displayTableStructure(String tableName) {
        try (Connection connection = Database_Connection.connect()) {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();

                // Get columns for the specified table
                ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
                System.out.println("Table: " + tableName);
                System.out.println("Columns:");
                while (columnResultSet.next()) {
                    String columnName = columnResultSet.getString("COLUMN_NAME");
                    String columnType = columnResultSet.getString("TYPE_NAME");
                    System.out.println("   " + columnName + " : " + columnType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayPrimaryKeyInfo(String tableName) {
        try (Connection connection = Database_Connection.connect()) {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();

                // Get primary key columns for the specified table
                ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(null, null, tableName);
                System.out.println("Table: " + tableName);
                System.out.println("Primary Key Information:");
                while (primaryKeyResultSet.next()) {
                    String primaryKeyName = primaryKeyResultSet.getString("COLUMN_NAME");
                    System.out.println("   Primary Key: " + primaryKeyName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayForeignKeyInfo(String tableName) {
        try (Connection connection = Database_Connection.connect()) {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();

                // Get foreign key columns for the specified table
                ResultSet foreignKeyResultSet = metaData.getImportedKeys(null, null, tableName);

                System.out.println("Table: " + tableName);
                System.out.println("Foreign Key Information:");

                if (foreignKeyResultSet.next()) {
                    do {
                        String foreignKeyName = foreignKeyResultSet.getString("FKCOLUMN_NAME");
                        String referencedTableName = foreignKeyResultSet.getString("PKTABLE_NAME");
                        String referencedColumnName = foreignKeyResultSet.getString("PKCOLUMN_NAME");
                        System.out.println("   Foreign Key: " + foreignKeyName +
                                ", Referenced Table: " + referencedTableName + ", Referenced Column: " + referencedColumnName);
                    } while (foreignKeyResultSet.next());
                } else {
                    System.out.println("   No foreign keys found for the table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Specify the table name "booksinfo"
        String tableName = "booksinfo";

        // Display structure of the specified table
        displayTableStructure(tableName);

        // Display primary key information for the specified table
        displayPrimaryKeyInfo(tableName);

        // Display foreign key information for the specified table
        displayForeignKeyInfo(tableName);
    }
}
