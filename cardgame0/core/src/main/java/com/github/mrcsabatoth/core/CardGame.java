package com.github.mrcsabatoth.core;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.github.mrcsabatoth.core.about.AboutScene;
import com.github.mrcsabatoth.core.board.BoardScene;

import playn.core.Game;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.Touch;

public class CardGame extends Game.Default {

  private final Set<Key> backKeys = EnumSet.of(Key.ESCAPE, Key.BACK);
  private final AppScene menuScene = new Menu(this);
  private AppScene activeScene;
  private long activeStamp;

  public interface DeviceService {
    /** Returns info on the device. */
    String info();
  }

  public final DeviceService deviceService;

  public final List<AppScene> scenes = new ArrayList<AppScene>(); {
    scenes.add(new AboutScene());
    scenes.add(new BoardScene());
  }

  public CardGame(DeviceService deviceService) {
    super(AppScene.UPDATE_RATE);
    this.deviceService = deviceService;
  }

  public boolean shouldExitOnBack() {
    // the BACK button will get procesesd by Android immediately *after* we move to the main menu,
    // so we want to debounce things so only if you press back after you're already on the main
    // menu do we allow the app to exit via the normal back button processing
    return (activeScene == menuScene) &&
      (System.currentTimeMillis() - activeStamp) > 500L;
  }

  public void activateScene(AppScene scene) {
    if (activeScene != null) {
      activeScene.shutdown();
    }
    if (activeScene != scene) {
      activeScene = scene;
      activeScene.init();
      activeStamp = System.currentTimeMillis();
    }
  }

  @Override
  public void init() {
    PlayN.keyboard().setListener(new Keyboard.Adapter() {
      @Override
      public void onKeyDown(Keyboard.Event event) {
        if (backKeys.contains(event.key())) {
          activateScene(menuScene);
        } else {
          Keyboard.Listener delegate = activeScene.keyboardListener();
          if (delegate != null) {
            delegate.onKeyDown(event);
          }
        }
      }

      @Override
      public void onKeyUp(Keyboard.Event event) {
        Keyboard.Listener delegate = activeScene.keyboardListener();
        if (delegate != null) {
          delegate.onKeyUp(event);
        }
      }
    });

    try {
      PlayN.touch().setListener(new Touch.Adapter() {
        public void onTouchStart(Touch.Event[] touches) {
          if (touches.length > 1)
            activateScene(menuScene);
        }
      });
    } catch (UnsupportedOperationException e) {
      // no support for touch; no problem
    }

    activateScene(menuScene);
  }

  @Override
  public void update(int delta) {
    activeScene.update(delta);
  }

  @Override
  public void paint(float alpha) {
    activeScene.paint(alpha);
  }
}
