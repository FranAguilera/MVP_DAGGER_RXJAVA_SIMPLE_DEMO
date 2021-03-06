package franjam.mvpdemo.mvp.presenter;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import franjam.mvpdemo.mvp.model.GiphyData;
import franjam.mvpdemo.mvp.view.EntryPointView;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntryPointPresenterImplementationTest {
    private EntryPointPresenterImplementation presenter;
    private EntryPointView mockView;
    private EntryPointPresenterImplementation.GiphyCallback callback;

    @Before
    public void setup() throws Exception {
        mockView = mock(EntryPointView.class);
        presenter = new EntryPointPresenterImplementation();
        callback = new EntryPointPresenterImplementation.GiphyCallback(presenter);

        presenter.setView(mockView);
    }

    @Test
    public void initialize() throws Exception {
        EntryPointPresenterImplementation entryPointPresenter = spy(presenter);
        doNothing().when(entryPointPresenter).intializeSubscriptionRequest();
        doNothing().when(entryPointPresenter).executeGiphyRequest();

        entryPointPresenter.initialize();

        verify(entryPointPresenter).intializeSubscriptionRequest();
        verify(entryPointPresenter).executeGiphyRequest();
    }

    @Test
    public void stop() throws Exception {
        EntryPointPresenterImplementation entryPointPresenter = spy(presenter);
        doNothing().when(entryPointPresenter).unSubscribeRequest();

        entryPointPresenter.onStop();

        verify(entryPointPresenter).unSubscribeRequest();
    }

    @Test
    public void successResponse() {
        GiphyData mockData = mock(GiphyData.class);
        callback.onSuccess(mockData);
        verify(mockView).updateRecyclerViewData(mockData);
    }

    @Test
    public void errorResponse() {
        final String errorMessage = "404 Error";

        Throwable mockThrowable = mock(Throwable.class);
        when(mockThrowable.getMessage()).thenReturn(errorMessage);

        callback.onError(mockThrowable);
        verify(mockView).displayMessage(errorMessage);
    }

    @Test
    public void finishedResponse() {
        callback.onFinished();
        verify(mockView).displayMessage(anyString());
    }
}