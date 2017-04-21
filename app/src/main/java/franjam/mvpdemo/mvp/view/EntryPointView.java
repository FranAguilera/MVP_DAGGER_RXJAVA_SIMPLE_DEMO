package franjam.mvpdemo.mvp.view;

import franjam.mvpdemo.mvp.model.GiphyData;

public interface EntryPointView {
    void updateRecyclerView(GiphyData flickData);
    void displayMessage(String message);
}
