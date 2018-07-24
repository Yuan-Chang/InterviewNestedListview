package intuit.com.interviewnestedlistview;

import java.util.ArrayList;

/**
 * Created by ychang3 on 7/23/18.
 */

public class AlbumList {
    int selectedIndex = -1;
    int scrollPos = -1;

    ArrayList<Album> albums;

    public ArrayList<Album> getAlbums() {
        if (albums == null)
            albums = new ArrayList<>();
        return albums;
    }

    public void addAlbums(ArrayList<Album> albums) {
        ArrayList list = getAlbums();
        list.addAll(albums);
    }

    Album getSelectedAlbum(){
        return albums.get(selectedIndex);
    }

    public int getScrollPos() {
        return scrollPos;
    }

    public void setScrollPos(int scrollPos) {
        this.scrollPos = scrollPos;
    }

}
