package franjam.mvpdemo;

import dagger.Module;
import dagger.Provides;
import franjam.mvpdemo.mvp.presenter.EntryPointPresenterImplementation;

@Module
public class EntryPointModule {

    @Provides
    public EntryPointPresenterImplementation provideMainEntryPresenter(){
        return new EntryPointPresenterImplementation();
    }
}
