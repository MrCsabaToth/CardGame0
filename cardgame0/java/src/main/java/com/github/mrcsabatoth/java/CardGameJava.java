package com.github.mrcsabatoth.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.github.mrcsabatoth.core.CardGame;

public class CardGameJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);
    PlayN.run(new CardGame());
  }
}
