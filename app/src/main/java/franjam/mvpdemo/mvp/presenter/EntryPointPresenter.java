package franjam.mvpdemo.mvp.presenter;

import franjam.mvpdemo.mvp.view.EntryPointView;

public interface EntryPointPresenter {
    void initialize();
    void onStop();
    void setView(EntryPointView view);
}
