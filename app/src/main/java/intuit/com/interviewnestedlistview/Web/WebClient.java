package intuit.com.interviewnestedlistview.Web;

import intuit.com.interviewnestedlistview.Album;
import intuit.com.interviewnestedlistview.Song;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ychang3 on 9/26/16.
 */

public interface WebClient {

    @GET("/albums")
    public Observable<Album[]> loadAlbums(@Query("start") int start, @Query("limit") int limit);

    @GET("/songs")
    public Observable<Song[]> loadSongs(@Query("id") String albumID);

}
