/**
 * Copyright 2011 The ForPlay Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.mrcsabatoth.core;

import playn.core.GroupLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.util.Clock;

import react.UnitSlot;

import tripleplay.ui.Background;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.Style;
import tripleplay.ui.layout.AxisLayout;

import static playn.core.PlayN.*;

/**
 * A demo that displays a menu of the available demos.
 */
public class Menu extends AppScene
{
  private final Keyboard.Listener keyListener = new Keyboard.Adapter() {
    @Override
    public void onKeyDown(Keyboard.Event event) {
      // this is a bit hacky, but serves our purpose
      int sceneIndex = event.key().ordinal() - Key.K1.ordinal();
      if (sceneIndex >= 0 && sceneIndex < gameMain.scenes.size()) {
    	  gameMain.activateScene(gameMain.scenes.get(sceneIndex));
      }
    }
  };

  private final CardGame gameMain;

  private Interface iface;
  private GroupLayer layer;

  public Menu (CardGame gameMain) {
    this.gameMain = gameMain;
  }

  @Override
  public String name() {
    return "Menu";
  }

  @Override
  public void init() {
    layer = graphics().createGroupLayer();
    graphics().rootLayer().add(layer);

    // create our UI manager and configure it to process pointer events
    iface = new Interface();

    // create our scene interface
    Root root = iface.createRoot(AxisLayout.vertical().gap(15), SimpleStyles.newSheet());
    root.setSize(graphics().width(), graphics().height());
    root.addStyles(Style.BACKGROUND.is(Background.solid(0xFF99CCFF).inset(5)));
    layer.add(root.layer);

    Group buttons;
    root.add(new Label("PlayN Demos:"),
             buttons = new Group(AxisLayout.vertical().offStretch()),
             new Label("ESC/BACK key or two-finger tap returns to menu from demo"),
             new Label("(renderer: " + graphics().getClass().getName() + ")"),
             new Label("(device: " + gameMain.deviceService.info() + ")").addStyles(
               Style.TEXT_WRAP.is(true)));

    int key = 1;
    for (final AppScene scene : gameMain.scenes) {
      Button button = new Button(key++ + " - " + scene.name());
      buttons.add(button);
      button.clicked().connect(new UnitSlot() {
        @Override
        public void onEmit() {
        	gameMain.activateScene(scene);
        }
      });
    }
  }

  @Override
  public void shutdown() {
    if (iface != null) {
      pointer().setListener(null);
      iface = null;
    }
    if (layer != null)
    {
      layer.destroy();
      layer = null;    	
    }
  }

  @Override
  public void update(int delta) {
    _clock.update(delta);
    if (iface != null) {
      iface.update(delta);
    }
  }

  @Override
  public void paint(float alpha) {
    _clock.paint(alpha);
    if (iface != null) {
      iface.paint(_clock);
    }
  }

  @Override
  public Keyboard.Listener keyboardListener() {
    return keyListener;
  }

  protected final Clock.Source _clock = new Clock.Source(UPDATE_RATE);
}
