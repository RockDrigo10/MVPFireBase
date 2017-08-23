package com.example.admin.mvpfirebase.view.mainactivity;

import com.example.admin.mvpfirebase.BasePresenter;
import com.example.admin.mvpfirebase.BaseView;

/**
 * Created by Admin on 8/22/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView{

        void onDataSaved(boolean isSaved);
        void sendToView(String s);
    }
    interface Presenter extends BasePresenter<View>{
        void saveDataToCloud(String s);
        void getDataToCloud();
    }
}
