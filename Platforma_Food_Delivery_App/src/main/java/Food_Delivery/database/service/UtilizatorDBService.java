package Food_Delivery.database.service;

import Food_Delivery.domain.DatePersonale;
import Food_Delivery.domain.Utilizator;
import Food_Delivery.domain.UtilizatorPremium;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UtilizatorDBService extends GenericDBService<Utilizator> { // Singleton
    private static UtilizatorDBService instance = null;

    private UtilizatorDBService() {
        super();
    }

    public static UtilizatorDBService getInstance() {
        if (instance == null) {
            instance = new UtilizatorDBService();
        }
        return instance;
    }


    /// Utility Method
    @Override
    public Utilizator mapRowToObject(ResultSet rs) throws SQLException {

        String nume = rs.getString("nume");
        String prenume = rs.getString("prenume");
        String cnp = rs.getString("cnp");
        String adresa = rs.getString("adresa");
        double discount = rs.getDouble("discount");

        if(discount == 0) {
            return new Utilizator(new DatePersonale(nume, prenume, cnp), adresa);
        }
        return new UtilizatorPremium(new DatePersonale(nume, prenume, cnp), adresa, discount);
    }

    // Create/Drop Table
    @Override
    public void createTable() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Utilizator (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nume VARCHAR(255) NOT NULL,
                    prenume VARCHAR(255) NOT NULL,
                    cnp VARCHAR(255) NOT NULL,
                    adresa VARCHAR(255) NOT NULL,
                    discount DOUBLE
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
        String dropTableSQL = "DROP TABLE IF EXISTS Utilizator";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    ///


    /// CRUD Operations

    // Create
    public void addUtilizator(Utilizator user) { // Prepared Statement
        String query = "INSERT INTO Utilizator (id, nume, prenume, cnp, adresa, discount) VALUES (null, ?, ?, ?, ?, ?)";

        String nume = user.getDatePers().getNume();
        String prenume = user.getDatePers().getPrenume();
        String cnp = user.getDatePers().getCnp();
        String adresa = user.getAdresa();
        double discount = 0.0;
        if(user instanceof UtilizatorPremium) {
            discount = ((UtilizatorPremium) user).getDiscount();
        }

        create(query, nume, prenume, cnp, adresa, discount);
    }

    // Read
    public List<Utilizator> getAllUtilizatori() {
        String query = "SELECT * FROM Utilizator";
        return readAll(query);
    }

    public Utilizator getUtilizator_byID(int id) {
        String query = "SELECT * FROM Utilizator WHERE id = ?";
        return readOne(query, id);
    }

    // Update
    public void updateUtilizator_byID(Utilizator user, int id) {
        String query = "UPDATE Utilizator SET nume = ?, prenume = ?, cnp = ?, adresa = ?, discount = ? WHERE id = ?";

        String nume = user.getDatePers().getNume();
        String prenume = user.getDatePers().getPrenume();
        String cnp = user.getDatePers().getCnp();
        String adresa = user.getAdresa();
        double discount = 0.0;
        if(user instanceof UtilizatorPremium) {
            discount = ((UtilizatorPremium) user).getDiscount();
        }

        update(query, nume, prenume, cnp, adresa, discount, id);
    }

    // Delete
    public void deleteUtilizator_byID(int id) {
        String query = "DELETE FROM Utilizator WHERE id = ?";
        delete(query, id);
    }
}
