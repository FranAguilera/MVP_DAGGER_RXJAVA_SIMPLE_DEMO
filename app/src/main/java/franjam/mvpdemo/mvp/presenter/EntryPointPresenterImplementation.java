package franjam.mvpdemo.mvp.presenter;

import android.support.annotation.VisibleForTesting;

import java.lang.ref.WeakReference;

import franjam.mvpdemo.mvp.model.GiphyData;
import franjam.mvpdemo.mvp.view.EntryPointView;
import franjam.mvpdemo.networking.request.GiphyRequest;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class EntryPointPresenterImplementation implements EntryPointPresenter {
    private static final String QUERY_TEXT = "barcelona";
    private static final String INITIAL_TEXT = "Presenter initialized!";
    private static final String END_REQUEST_TEXT = "Request finished";

    private EntryPointView view;
    private CompositeSubscription compositeRxSubscription;

    @Override
    public void setView(EntryPointView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        // TODO: INITIAL_TEXT could be localized in strings.xml
        view.displayMessage(INITIAL_TEXT);

        GiphyCallback giphyCallback = new GiphyCallback(this);
        GiphyRequest request = new GiphyRequest(giphyCallback);

        this.compositeRxSubscription = new CompositeSubscription();
        Subscription rxSubscription = request.getPics(QUERY_TEXT);
        compositeRxSubscription.add(rxSubscription);
    }

    @Override
    public void onStop() {
        compositeRxSubscription.unsubscribe();
    }

    @VisibleForTesting
    static class GiphyCallback implements GiphyRequest.PicsCallback {
        private final WeakReference<EntryPointPresenterImplementation> weakRef;

        public GiphyCallback(EntryPointPresenterImplementation presenter) {
            this.weakRef = new WeakReference<>(presenter);
        }

        @Override
        public void onSuccess(GiphyData giphyData) {
            if (isValidReference()){
                weakRef.get().view.updateRecyclerView(giphyData);
            }
        }

        @Override
        public void onError(Throwable networkError) {
            if (isValidReference()) {
                weakRef.get().view.displayMessage(networkError.getMessage());
            }
        }

        @Override
        public void onFinished() {
            if (isValidReference()) {
                weakRef.get().view.displayMessage(END_REQUEST_TEXT);
            }
        }

        private boolean isValidReference() {
            return weakRef.get() != null;
        }
    }
}
