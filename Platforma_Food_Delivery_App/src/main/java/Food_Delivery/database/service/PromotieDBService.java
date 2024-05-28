package Food_Delivery.database.service;

import Food_Delivery.domain.Promotie;

import java.sql.*;
import java.util.*;

public class PromotieDBService extends GenericDBService<Promotie> { // Singleton
    private static PromotieDBService instance = null;

    private PromotieDBService() {
        super();
    }

    public static PromotieDBService getInstance() {
        if (instance == null) {
            instance = new PromotieDBService();
        }
        return instance;
    }


    /// Utility Method
    @Override
    public Promotie mapRowToObject(ResultSet rs) throws SQLException {

        String descriere = rs.getString("descriere");
        double reducere = rs.getDouble("reducere");

        return new Promotie(descriere, reducere);
    }

    // Create/Drop Table
    @Override
    public void createTable() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Promotie (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    descriere VARCHAR(255) NOT NULL,
                    reducere DOUBLE NOT NULL
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS Promotie";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    ///


    /// CRUD Operations

    // Create
    public void addPromotie(Promotie promotie) { // Prepared Statement
        String query = "INSERT INTO Promotie (id, descriere, reducere) VALUES (null, ?, ?)";

        String descriere = promotie.getDescriere();
        double reducere = promotie.getReducere();

        create(query, descriere, reducere);
    }

    // Read
    public List<Promotie> getAllPromotii() {
        String query = "SELECT * FROM Promotie";
        return readAll(query);
    }

    public List<Promotie> getAllPromotii_GreaterDiscount() {
        String query = "SELECT * FROM Promotie ORDER BY reducere DESC";
        return readAll(query);
    }

    public Promotie getPromotie_byID(int id) {
        String query = "SELECT * FROM Promotie WHERE id = ?";
        return readOne(query, id);
    }

    // Update
    public void updatePromotie_byID(Promotie promotie, int id) {
        String query = "UPDATE Promotie SET descriere = ?, reducere = ? WHERE id = ?";

        String descriere = promotie.getDescriere();
        double reducere = promotie.getReducere();

        update(query, descriere, reducere, id);
    }

    // Delete
    public void deletePromotie_byID(int id) {
        String query = "DELETE FROM Promotie WHERE id = ?";
        delete(query, id);
    }
}


