package com.halturin.dmitry.rssreader.presenter;

import com.halturin.dmitry.rssreader.view.SettingsView;

/**
 * Created by Dmitry Halturin <dmitry.halturin.86@gmail.com> on 19.02.17 14:01.
 */

public class SettingsPresenter extends RssPresenterImpl {

//==================================================================================================
//    Class Variables
//==================================================================================================

    private SettingsView view = null;

//==================================================================================================
//    Class Constructor
//==================================================================================================

    public SettingsPresenter(SettingsView view){
        this.view = view;
    }

//==================================================================================================
//    Class Callbacks
//==================================================================================================

    @Override
    public void onResume(){
        super.onResume();

        // TODO: implementation observer view actions
    }

}