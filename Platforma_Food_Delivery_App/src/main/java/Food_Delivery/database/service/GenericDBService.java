package Food_Delivery.database.service;

import Food_Delivery.audit.AuditService;
import Food_Delivery.database.config.DatabaseConfiguration;

import java.sql.*;
import java.util.*;

public abstract class GenericDBService<T> {
    protected Connection connection;

    // Audit
    protected AuditService auditService;

    public GenericDBService() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();

        // Audit
        this.auditService = AuditService.getInstance();
    }


    /// Utility Methods
    private void setParams(PreparedStatement stmt, Object... params) throws SQLException {
        int index = 1;
        for (Object param : params) {
            stmt.setObject(index++, param);
        }
    }

    // Create/Drop Table
    public abstract void createTable();
    public abstract void dropTable();

    public abstract T mapRowToObject(ResultSet rs) throws SQLException;

    ///


    /// CRUD Operations

    // Create
    public void create(String query, Object... params) { // varArgs
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<T> readAll(String query) {
        List<T> resultList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                resultList.add(mapRowToObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public T readOne(String query, int id) {
        T result = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                result = mapRowToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Update
    public void update(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(String query, Object... params) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParams(stmt, params);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
