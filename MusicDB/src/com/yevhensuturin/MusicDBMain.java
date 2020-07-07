package com.yevhensuturin;

import com.yevhensuturin.model.Artist;
import com.yevhensuturin.model.DataSource;
import com.yevhensuturin.model.SongArtist;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class MusicDBMain {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();

        if(!dataSource.open()){
            System.out.println("Can't open DataSource");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_ASC);
        if(artists != null){
            artists.forEach(artist -> System.out.println("ID = " + artist.getId() + " name " + artist.getName()));
        } else {
            System.out.println("No artists!");
        }

        List<String> albumForArtists = dataSource.queryAlbumsForArtist("Iron Maiden", DataSource.ORDER_BY_ASC);
        albumForArtists.forEach(item -> System.out.println(item));

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        songArtists.forEach(item -> System.out.println(item.getAlbumName() + " : " + item.getArtistName() + " : " + item.getTrack()));

        dataSource.querySongsMetaDate();

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number songs is: "+count);

        dataSource.createViewForSongArtists();

        String title = "Go Your Own Way";
        //SQL injection attack example
        title = "Go Your Own Way\" or 1=1 or \"";

        dataSource.querySongInfoView(title).forEach(
                songArtist -> System.out.println(songArtist.getArtistName() + ":" + songArtist.getTrack() + ":" + songArtist.getAlbumName()
                ));


        dataSource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);

        dataSource.close();
    }
}
