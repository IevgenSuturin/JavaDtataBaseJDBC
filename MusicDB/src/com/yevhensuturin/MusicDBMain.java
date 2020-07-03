package com.yevhensuturin;

import com.yevhensuturin.model.Artist;
import com.yevhensuturin.model.DataSource;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

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

        dataSource.close();
    }
}
