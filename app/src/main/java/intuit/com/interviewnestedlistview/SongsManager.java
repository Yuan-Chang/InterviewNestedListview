package intuit.com.interviewnestedlistview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ychang3 on 7/23/18.
 */

public class SongsManager {
    private static SongsManager instance;

    AlbumList albumList = new AlbumList();

    static public SongsManager getInstance(){
        if(instance == null)
        {
            instance = new SongsManager();
        }
        return instance;
    }

    public Album getSelectedAlbum(){
        return albumList.getSelectedAlbum();
    }

    public int getSelectedIndex(){
        return albumList.selectedIndex;
    }

    public void setSelectedAlbumIndex(int index)
    {
        albumList.selectedIndex = index;
    }

    public void addAlbumList(Album[] albums, int total){

        if (total <= getAlbumList().size())
            return;

        albumList.addAlbums(new ArrayList<Album>(Arrays.asList(albums)));
    }

    public ArrayList<Album> getAlbumList() {

        return albumList.getAlbums();
    }

    public void setScrollPos(int pos)
    {
        albumList.setScrollPos(pos);
    }

    public int getScrollPos()
    {
        return albumList.getScrollPos();
    }


}
