package com.github.mrcsabatoth.html;

import com.google.gwt.user.client.Window;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import com.github.mrcsabatoth.core.CardGame;

public class CardGameHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform.Config config = new HtmlPlatform.Config();
    // use config to customize the HTML platform, if needed
    try {
      config.scaleFactor = Float.parseFloat(Window.Location.getParameter("scale"));
    } catch (Exception e) {} // oh well
    HtmlPlatform platform = HtmlPlatform.register(config);
    platform.assets().setPathPrefix("cardgame0/");
    PlayN.run(new CardGame(new CardGame.DeviceService() {
        public String info() {
          return "HTML [userAgent=" + Window.Navigator.getUserAgent() + "]";
        }
      }));
  }
}
