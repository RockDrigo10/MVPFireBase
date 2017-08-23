package com.example.admin.mvpfirebase.Injection.mainactivity;

import com.example.admin.mvpfirebase.view.mainactivity.MainActivity;

import dagger.Component;

/**
 * Created by Admin on 8/22/2017.
 */
@Component(modules =  MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
