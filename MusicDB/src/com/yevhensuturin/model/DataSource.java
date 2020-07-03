package com.yevhensuturin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\h241705\\IdeaProjects\\JavaDataBaseJDBC\\MusicDB\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;


    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;


    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int  INDEX_SONG_ID = 1;
    public static final int  INDEX_SONG_TRACK = 2;
    public static final int  INDEX_SONG_TITLE = 3;
    public static final int  INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NON = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


    private Connection connection;

    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e){
            System.out.println("Couldn't connect to the database " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e){
            System.out.println("Couldn't close database " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder){
        StringBuilder builder = new StringBuilder("SELECT * FROM ");
        builder.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NON){
            builder.append(" ORDER BY ");
            builder.append(COLUMN_ARTIST_NAME);
            builder.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                builder.append("DESC");
            }else{
                builder.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(builder.toString())){

                List<Artist> artists = new ArrayList<>();
                while (results.next()) {
                    Artist artist = new Artist();
                    artist.setId(results.getInt(INDEX_ARTIST_ID));
                    artist.setName(results.getString(INDEX_ARTIST_NAME));
                    artists.add(artist);
                }

                return artists;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage() );
            e.printStackTrace();
            return null;
        }
    }
}
