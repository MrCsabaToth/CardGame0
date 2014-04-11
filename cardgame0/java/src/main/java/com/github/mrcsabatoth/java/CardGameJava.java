package com.github.mrcsabatoth.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.github.mrcsabatoth.core.CardGame;

public class CardGameJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);

    PlayN.run(new CardGame(new CardGame.DeviceService() {
        public String info () {
          Runtime rt = Runtime.getRuntime();
          return "Java [vers=" + System.getProperty("java.version") +
            ", os=" + System.getProperty("os.name") +
            ", osarch=" + System.getProperty("os.arch") +
            ", osvers=" + System.getProperty("os.version") +
            ", freemem=" + (rt.freeMemory()/1024) + "/" + (rt.totalMemory()/1024) + "k" +
            ", maxmem=" + (rt.maxMemory()/1024) + "k]";
        }
      }));
  }
}
