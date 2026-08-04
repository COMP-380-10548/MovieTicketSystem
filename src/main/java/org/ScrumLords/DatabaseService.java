package org.ScrumLords;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseService {
    private static DatabaseService instance;
    private MongoClient client;
    private MongoDatabase database;

    private DatabaseService() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");

        this.client = MongoClients.create(uri);
        this.database = client.getDatabase("MovieTicketSystem");
    }

    public static synchronized DatabaseService getInstance() {
        if(instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public void close() {
        client.close();
    }
}
