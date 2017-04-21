package franjam.mvpdemo.networking.contract;

import franjam.mvpdemo.mvp.model.GiphyData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/*
 * Uses giphy API, find documentation below:
 *
 * https://github.com/Giphy/GiphyAPI
 *
 * NOTE: PUBLIC_BETA_KEY is the one provided in the sample API
 */
public interface GiphyContract {
    String PUBLIC_BETA_KEY = "dc6zaTOxFJmzC";
    String targetUrl = "v1/gifs/search?api_key=" + PUBLIC_BETA_KEY;

    @GET(targetUrl)
    Observable<GiphyData> getGiphy(@Query("q")String query);
}
