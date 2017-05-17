package franjam.mvpdemo;

import android.app.Application;


public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate(){
        super.onCreate();

        setDaggerAppComponent();
    }

    public ApplicationComponent getComponent(){
        return component;
    }

    private void setDaggerAppComponent(){
        // NOTE: DaggerApplicationComponent will be generated automatically after 'make project'

        //noinspection deprecation
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .entryPointModule(new EntryPointModule())
                .build();
    }
}
