package de.fabiandarga;

import java.sql.*;

public class DatabaseUtils {
    Connection connection;

    public DatabaseUtils(String url, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void saveStudentData(int id, byte[] data) {
        String sql =  "INSERT INTO students_data (student_id, data) VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setBytes(2, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getStudentDataById(int id) {
        String sql = "SELECT data FROM students_data WHERE student_id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getBytes("data");
            }

            throw new RuntimeException("Student not found with id "+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
