package models;

import android.app.Application;

public class App extends Application {

    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }
}
