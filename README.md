<p align="center">
<img align="center" src="wtfperminute.png" width="75%" alt=""/>
</p>

*Image from Thom Holwerda. License Attribution-NoDerivs 2.0 Generic (CC BY-ND 2.0). 
[Image Link url](https://www.flickr.com/photos/smitty/2245445147)*

# MVP + DAGGER 2 + RXJAVA 2 (SIMPLE EXAMPLE)

The purpose of this repository is to create an straightforward introduction of the MVP pattern in Android development.

In addition to MVP clean architecture, dependency injection framework (Dagger 2) and Reactive Programming (RxJava 2) will be sampled within this simple app.

## APP DEMO

<br>
<p align="center">
<img align="center" src="app_demo.gif" width="50%" alt=""/>
</p>

<br>

## MVP
MVP is a clean architecture design pattern that stands for Model-View-Presenter. The main benefits are that it allows you to decouple logic from Activities/Fragments and make the app more testable, easier to maintain, more readable, etc. These are the main components:

<br>

<p align="center">
<img align="center" src="mvp_graph.png" width="40%" alt="API Giphy logo"/>
</p>

#### Model
Will hold the data of our app. The presenter will be responsible for model retrieval/update.

#### View
Just a contract that will be implemented by our activities/fragments. 

+ The implementations of the view contract (usually by the Activitites/Fragments) should NOT have any logic and should be as 'dumb' as possible.

+ These contract methods will be called by the presenter. 

+ The view will be injected into the presenter.

+ For injecting the view into the presenter, we can use Dependency injection framework (Dagger 2)


<b>NOTE: Dagger 2 is not necessary for MVP implementation. You can inject the View contract definition via the presenter's constructor</b>

Code sample:

```java
// View Interface
public interface EntryPointView {
   void displayMessage(final String message); 
}
```


```java
// Activity/Fragment implementing the view interface
public class EntryPointActivity extends AppCompatActivity implements EntryPointView{
	
	
   //Activity instance fields
	
    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

```

#### Presenter
The presenter will handle all the logic of the app. It will be responsible for updating the model and calling the methods on the view when necessary.
Steps:

+ The view must be passed into the presenter (e.g. setView(View view) method)

+ The activity/fragment that is implementing the view will inject the presenter and will notify the presenter of Android events such as: onResume,onStop,onClick, etc.

+ Then according to these interactions, the presenter will handle when to call the proper methods on the view interface.

Code sample:

```java
public interface EntryPointPresenter {
   void initialize();
   void setView(EntryPointView view);
}
```


```java
public class EntryPointPresenterImplementation implements EntryPointPresenter {
   private EntryPointView view;
   
    @Override
    public void setView(EntryPointView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        view.displayMessage("Presenter initialized");
        // More initialization logic here
    }
}
```


```java
public class EntryPointActivity extends AppCompatActivity implements EntryPointView{

    @Inject EntryPointPresenterImplementation presenter;
   
    //Activity instance fields
    
    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.initialize();
    }
}
```

