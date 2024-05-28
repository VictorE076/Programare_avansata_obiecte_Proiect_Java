package Food_Delivery.audit;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService { // Singleton
    private static AuditService instance = null;
    private static final String FILE_NAME = "audit.csv";

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }


    // Pentru operatii CRUD la nivelul bazei de date
    public void logOperation(String operation, String numeTabel) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(operation).append(" -> ").append(numeTabel).append(", ").append(timestamp).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Pentru actiuni / interogari la nivelul sistemului (etapa 1)
    public void logAction(String action) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(action).append(", ").append(timestamp).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
