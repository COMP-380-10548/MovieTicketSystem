package org.ScrumLords;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
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

    /**
     * Obtain the DatabaseService singleton instance
     * @return The singleton referencing the instantiated DatabaseService object
     */
    public static synchronized DatabaseService getInstance() {
        if(instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    /**
     * Request a specific collection with a set of documents from the database based on its name
     * @param name The name of the desired collection to obtain
     * @return The MongoCollection object and its associated arbitrary documents 
     */
    public MongoCollection<Document> getCollection(String name) {
        return database.getCollection(name);
    }

    /**
     * Close the connection to DatabaseService singleton and its associated MongoClient
     */
    public void close() {
        client.close();
    }
}
