package intuit.com.interviewnestedlistview;

/**
 * Created by ychang3 on 7/23/18.
 */

public class Album extends Song{

    private Song[] songs;

    public Album(String name) {
        super(name);
    }

    public Song[] getSongs(){
        if (songs == null)
            songs = new Song[]{};
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

}
