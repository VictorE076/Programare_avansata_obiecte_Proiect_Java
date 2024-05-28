package Food_Delivery.database.service;

import Food_Delivery.domain.DatePersonale;
import Food_Delivery.domain.Sofer;

import java.sql.*;
import java.util.*;

public class SoferDBService extends GenericDBService<Sofer> { // Singleton
    private static SoferDBService instance = null;

    private SoferDBService() {
        super();
    }

    public static SoferDBService getInstance() {
        if (instance == null) {
            instance = new SoferDBService();
        }
        return instance;
    }


    /// Utility Method
    @Override
    public Sofer mapRowToObject(ResultSet rs) throws SQLException {

        String nume = rs.getString("nume");
        String prenume = rs.getString("prenume");
        String cnp = rs.getString("cnp");
        String vehicul = rs.getString("vehicul");

        return new Sofer(new DatePersonale(nume, prenume, cnp), vehicul);
    }

    // Create/Drop Table
    @Override
    public void createTable() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Sofer (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nume VARCHAR(255) NOT NULL,
                    prenume VARCHAR(255) NOT NULL,
                    cnp VARCHAR(255) NOT NULL,
                    vehicul VARCHAR(255) NOT NULL
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
        String dropTableSQL = "DROP TABLE IF EXISTS Sofer";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    ///


    /// CRUD Operations

    // Create
    public void addSofer(Sofer sofer) { // Prepared Statement
        String query = "INSERT INTO Sofer (id, nume, prenume, cnp, vehicul) VALUES (null, ?, ?, ?, ?)";

        String nume = sofer.getDatePers().getNume();
        String prenume = sofer.getDatePers().getPrenume();
        String cnp = sofer.getDatePers().getCnp();
        String vehicul = sofer.getVehicul();

        create(query, nume, prenume, cnp, vehicul);
    }

    // Read
    public List<Sofer> getAllSoferi() {
        String query = "SELECT * FROM Sofer";
        return readAll(query);
    }

    public Sofer getSofer_byID(int id) {
        String query = "SELECT * FROM Sofer WHERE id = ?";
        return readOne(query, id);
    }

    // Update
    public void updateSofer_byID(Sofer sofer, int id) {
        String query = "UPDATE Sofer SET nume = ?, prenume = ?, cnp = ?, vehicul = ? WHERE id = ?";

        String nume = sofer.getDatePers().getNume();
        String prenume = sofer.getDatePers().getPrenume();
        String cnp = sofer.getDatePers().getCnp();
        String vehicul = sofer.getVehicul();

        update(query, nume, prenume, cnp, vehicul, id);
    }

    // Delete
    public void deleteSofer_byID(int id) {
        String query = "DELETE FROM Sofer WHERE id = ?";
        delete(query, id);
    }
}

