package com.github.mrcsabatoth.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.github.mrcsabatoth.core.CardGame;

public class CardGameActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new CardGame());
  }
}
