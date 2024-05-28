package Food_Delivery.database.service;

import Food_Delivery.domain.*;

import java.sql.*;
import java.util.*;

public class ProdusDBService extends GenericDBService<Produs> { // Singleton
    private static ProdusDBService instance = null;

    private ProdusDBService() {
        super();
    }

    public static ProdusDBService getInstance() {
        if (instance == null) {
            instance = new ProdusDBService();
        }
        return instance;
    }


    /// Utility Method
    @Override
    public Produs mapRowToObject(ResultSet rs) throws SQLException {

        String denumire = rs.getString("denumire");
        double pret = rs.getDouble("pret");
        String tipCarne = rs.getString("tipCarne");
        String ingredienteStr = rs.getString("ingrediente");

        List<String> ingrediente = Arrays.asList(ingredienteStr.split(","));

        if(tipCarne.isEmpty() && !ingredienteStr.isEmpty()) {
            return new Pizza(denumire, pret, ingrediente);
        } else if (!tipCarne.isEmpty() && ingredienteStr.isEmpty()) {
            return new Burger(denumire, pret, tipCarne);
        }

        return null;
    }

    // Create/Drop Table
    @Override
    public void createTable() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Produs (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    denumire VARCHAR(255) NOT NULL,
                    pret DOUBLE NOT NULL,
                    tipCarne VARCHAR(255),
                    ingrediente VARCHAR(255)
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
        String dropTableSQL = "DROP TABLE IF EXISTS Produs";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    ///


    /// CRUD Operations

    // Create
    public void addProdus(Produs produs) { // Prepared Statement
        String query = "INSERT INTO Produs (id, denumire, pret, tipCarne, ingrediente) VALUES (null, ?, ?, ?, ?)";

        String denumire = produs.getDenumire();
        double pret = produs.getPret();
        String tipCarne = "";
        String ingrediente = "";

        if(produs instanceof Pizza) {
            ingrediente = String.join(",", ((Pizza) produs).getIngrediente());
        } else {
            tipCarne = ((Burger) produs).getTipCarne();
        }

        create(query, denumire, pret, tipCarne, ingrediente);
    }

    // Read
    public List<Produs> getAllProdus() {
        String query = "SELECT * FROM Produs";
        return readAll(query);
    }

    public List<Produs> getAllProdus_LowerPrice() {
        String query = "SELECT * FROM Produs ORDER BY pret ASC";
        return readAll(query);
    }

    public Produs getProdus_byID(int id) {
        String query = "SELECT * FROM Produs WHERE id = ?";
        return readOne(query, id);
    }

    // Update
    public void updateProdus_byID(Produs produs, int id) {
        String query = "UPDATE Produs SET denumire = ?, pret = ?, tipCarne = ?, ingrediente = ? WHERE id = ?";

        String denumire = produs.getDenumire();
        double pret = produs.getPret();
        String tipCarne = "";
        String ingrediente = "";

        if(produs instanceof Pizza) {
            ingrediente = String.join(",", ((Pizza) produs).getIngrediente());
        } else {
            tipCarne = ((Burger) produs).getTipCarne();
        }

        update(query, denumire, pret, tipCarne, ingrediente, id);
    }

    // Delete
    public void deleteProdus_byID(int id) {
        String query = "DELETE FROM Produs WHERE id = ?";
        delete(query, id);
    }
}

