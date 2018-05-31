package com.ftech.savelock;

import com.ftech.savelock.utils.Prefs;

public class Application extends android.app.Application {

    private Prefs prefs;
    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        prefs = new Prefs(this,0);

    }

    public static Application getApp() {
        return app;
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public void setPrefs(Prefs prefs) {
        this.prefs = prefs;
    }


}
