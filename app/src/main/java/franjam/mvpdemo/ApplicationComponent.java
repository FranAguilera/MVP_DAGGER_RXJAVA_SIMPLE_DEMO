package franjam.mvpdemo;

import javax.inject.Singleton;

import dagger.Component;
import franjam.mvpdemo.activities.EntryPointActivity;

@Singleton
@Component(modules = {ApplicationModule.class, EntryPointModule.class})
public interface ApplicationComponent {
    // This is where the presenter will be injected
    void inject(EntryPointActivity target);
}
