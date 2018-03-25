package com.khalturin.dmitriy.presentation.view.state;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 25.03.18 15:43.
 */

public class NewsState extends ViewModel {

  public ObservableField<String> title = new ObservableField<>();
  public ObservableField<String> description = new ObservableField<>();
  public ObservableField<String> date = new ObservableField<>();
  public ObservableField<Drawable> image = new ObservableField<>();
  public ObservableField<Boolean> isWasRead = new ObservableField<>();

}
