package franjam.mvpdemo.networking.request;

import franjam.mvpdemo.mvp.model.GiphyData;
import franjam.mvpdemo.networking.contract.GiphyContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GiphyRequest {
    private static final String BASE_URL = "http://api.giphy.com";
    private final GiphyContract contract;
    private final PicsCallback picsCallback;

    public GiphyRequest(PicsCallback picsCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.contract = retrofit.create(GiphyContract.class);
        this.picsCallback = picsCallback;
    }

    public Subscription getPics(final String queryText) {

        return contract.getGiphy(queryText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GiphyData>>() {
                    @Override
                    public Observable<? extends GiphyData> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GiphyData>() {
                    @Override
                    public void onCompleted() {
                        picsCallback.onFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        picsCallback.onError(e);
                    }

                    @Override
                    public void onNext(GiphyData flickrData) {
                        picsCallback.onSuccess(flickrData);
                    }
                });
    }

    public interface PicsCallback {
        void onSuccess(GiphyData flickrData);
        void onError(Throwable networkError);
        void onFinished();
    }
}
