package DataBase;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Represents the database used for the game
 * @author Bilal Brarou, Othman Belarabi, Mounir Laity, Phillipe Nguyen, Mohamed
 * Chetouani
 */
public class Data extends Observable{

    public static Connection connexion = null;
    public static String sqliteServer = "jdbc:sqlite:";
    public static String url = "";

    /**
     * Checks if there is already a database at the given path
     * @param dbFilePath
     * @return 
     */
    public static boolean databaseExists(String dbFilePath) {
        File dbFile = new File(dbFilePath);
        return dbFile.exists();
    }

    /**
     * Establishes the connection to sqlite
     * @param bath the path of the database
     */
    public void connect(String bath) {
        try {
            url = bath;
            // db parameters
            sqliteServer = "jdbc:sqlite:";
            String getFilePath = new File("").getAbsolutePath();

            System.out.println(sqliteServer);
            System.out.println(getFilePath);
            System.out.println(url);
            // create a connection to the database

            if (databaseExists(url)) {
                System.out.println("DB Selection: ");
                connexion = DriverManager.getConnection(sqliteServer + url);
                System.out.println("Connection to SQLite has been established.");
            } else {
                try {
                    createNewDatabase();
                } catch (Exception ex) {
                    System.out.println("Error: " + ex);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a database containing the settings of the table game and record
     */
    public void createNewDatabase() {

        String getFilePath = new File("").getAbsolutePath();
        String fileAbsolutePath = "";

        fileAbsolutePath = getFilePath.concat("/data/sqlite/demineur.db");
        url = fileAbsolutePath;
        System.out.println(url);
        try {
            if (connexion != null) {
                DatabaseMetaData meta = connexion.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                Statement statement = connexion.createStatement();
                statement.executeUpdate("CREATE TABLE GAMES (id			INTEGER 	PRIMARY KEY AUTOINCREMENT"
                        + ", nbRow	INTEGER		NOT NULL, nbColumn	INTEGER		NOT NULL, bombs	INTEGER		NOT NULL, CONSTRAINT parametre	UNIQUE(nbRow, nbColumn, bombs));");
                statement.executeQuery("CREATE TABLE RECORDS (	id			INTEGER PRIMARY KEY AUTOINCREMENT,	score		INTEGER,"
                        + "dateRecord		TEXT	NOT NULL,"
                        + "id_game		INTEGER	NOT NULL,"
                        + "name  	TEXT	NOT NULL,"
                        + "FOREIGN KEY(id_game) REFERENCES GAMES(id));	");
                System.out.println("Database Has Been Created!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Creates two table on the database containing the settings of the table game and record
     */
    public void createDatabaseTable() {
        try ( Connection conn = DriverManager.getConnection(sqliteServer + url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Database Path: " + url);

                Statement statement = conn.createStatement();
                statement.executeUpdate("CREATE TABLE GAMES (id			INTEGER 	PRIMARY KEY AUTOINCREMENT"
                        + ", nbRow	INTEGER		NOT NULL, nbColumn	INTEGER		NOT NULL, bombs	INTEGER		NOT NULL, CONSTRAINT parametre	UNIQUE(nbRow, nbColumn, bombs));");
                System.out.println("Table Has Been Created!");
                statement.executeQuery("CREATE TABLE RECORDS (	id			INTEGER PRIMARY KEY AUTOINCREMENT,	score		INTEGER,"
                        + "dateRecord		TEXT	NOT NULL,"
                        + "id_game		INTEGER	NOT NULL,"
                        + "name  	TEXT	NOT NULL,"
                        + "FOREIGN KEY(id_game) REFERENCES GAMES(id));	");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert data into the Games's table.
     * @param row number of row in the current game
     * @param column number of column in the current game
     * @param bombs number of bombs in the current game
     */
    public void insertDataGame(int row, int column, int bombs) {
        String sql = "INSERT INTO GAMES (nbRow,nbColumn, bombs) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, row);
            pstmt.setInt(2, column);
            pstmt.setInt(3, bombs);
            pstmt.executeUpdate();

            System.out.println("Data Inserted!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert data into the Records's table.
     * @param score The score of the current game
     * @param id_game The id of the current game
     * @param name The name of the player in the current game
     */
    public void insertDataRecords(int score, int id_game, String name) {
        String sql = "INSERT INTO RECORDS (score, dateRecord, id_game, name) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setInt(1, score);
            pstmt.setString(2, java.util.Calendar.getInstance().getTime().toString());
            pstmt.setInt(3, id_game);
            pstmt.setString(4, name);
            pstmt.executeUpdate();

            System.out.println("Data Inserted!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Get the id_game of the current game if it's exist if not it create it.
     * @param row number of row in the current game
     * @param column number of column in the current game
     * @param bombs number of bombs in the current game
     * @return the id_game of the current game
     */
    public int getGameId(int row, int column, int bombs) {
        int id = -1;
        try {
            Statement stmt = connexion.createStatement();

            String query = "SELECT id FROM GAMES WHERE nbRow = " + row + " AND nbColumn = " + column + " AND bombs = " + bombs;

            ResultSet result = stmt.executeQuery(query);

            id = result.getInt(1);

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode()+" : num");
            if(ex.getErrorCode()== 1){
                createDatabaseTable();
            }
            System.out.println("DEMO_SELECT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
            if (ex.getSQLState() == null) {
                insertDataGame(row, column, bombs);
                id = getGameId(row, column, bombs);
            }
        }
        return id;
    }

    /**
     * Get the best record of the current game
     * @param idGame The id of the current game
     * @return the Record of the best player on this game
     */
    public Record getBestRecord(int idGame) {
        Record record = null;
        try {
            Statement stmt = connexion.createStatement();
            String query = "SELECT MIN(score),id, name, dateRecord, id_game   FROM RECORDS WHERE id_game = " + idGame;

            ResultSet result = stmt.executeQuery(query);
            record = new Record();
            record.setId(result.getInt(2));
            record.setScore(result.getInt(1));
            record.setDate(result.getString(4));
            record.setId_game(result.getInt(5));
            record.setName(result.getString(3));
        } catch (SQLException ex) {
            System.out.println("DEMO_SELECT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return record;
    }

    /**
     * Get all the record of a specific game
     * @param idGame The id of the current game
     * @return a list of all record of this id_game
     */
    public List<Record> getListRecords(int idGame) {
        List<Record> records = new ArrayList<>();
        try {
            Statement stmt = connexion.createStatement();
            String query = "SELECT score,id, name, dateRecord, ROW_NUMBER() OVER(ORDER BY score ASC) AS Row   FROM RECORDS WHERE id_game = " + idGame + " ORDER BY score";

            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                Record record = new Record();
                record.setId(result.getInt(2));
                record.setScore(result.getInt(1));
                record.setDate(result.getString(4));
                record.setId_game(result.getInt(5));
                record.setName(result.getString(3));
                records.add(record);
            }
        } catch (SQLException ex) {
            System.out.println("DEMO_SELECT | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        System.out.println(records.size() + "");
        return records;
    }


}
