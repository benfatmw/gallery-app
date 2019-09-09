package com.lin.mobile.gallery.remote;

import com.lin.mobile.gallery.remote.model.RemoteAlbum;
import com.lin.mobile.gallery.remote.model.RemotePhoto;
import com.lin.mobile.gallery.remote.model.RemoteUser;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GetRemoteDataService {

    String HTTPS_API_ALBUMS_URL = "https://jsonplaceholder.typicode.com";

    @GET("/albums")
    Flowable<List<RemoteAlbum>> getAlbums();

    @GET("/photos")
    Flowable<List<RemotePhoto>> getPhotosByAlbumId(@Query("albumId") int albumId);

    @GET("/users")
    Flowable<List<RemoteUser>> getUserById(@Query("id") int userId);

}
