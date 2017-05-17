package franjam.mvpdemo.mvp.view;

import franjam.mvpdemo.mvp.model.GiphyData;

public interface EntryPointView {
    void updateRecyclerViewData(GiphyData giphyData);
    void displayMessage(String message);
}
