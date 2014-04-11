/**
 * Copyright 2011 The PlayN Authors
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

import playn.core.Game;
import playn.core.Keyboard;

/**
 * This interface for each feature / scene (activated by a menu item), it is kind of like
 * {@link Game} except that it has lifecycle methods for cleaning up as well as initializing. Features
 * also all share a predefined update rate (30 fps).
 */
public abstract class AppScene
{
  public static final int UPDATE_RATE = 33;  // call update every 33ms (30 times per second)

  /**
   * Returns the name of the feature.
   */
  public abstract String name();

  /**
   * Initializes this scene. Here is where listeners should be wired up and resources loaded.
   */
  public abstract void init();

  /**
   * Shuts down this scene. Listeners should be cleared and resources destroyed.
   */
  public abstract void shutdown();

  /**
   * Called every update tick while this scene is active.
   * @param delta the amount of time that has elapsed since the last update call.
   */
  public void update(int delta) {
  }

  /**
   * Called while this scene is active, to paint the scene.
   * @param alpha a value in the range [0,1) that represents the fraction of the update tick that
   * has elapsed since the last call to update.
   */
  public void paint(float alpha) {
  }

  /**
   * Because the menu uses a few keys to move between menus, a scene must not register a
   * keyboard listener directly, but instead return its listener from this method. This allows the
   * menu to intercept the keys it needs and to pass on other key events to the scene.
   */
  public Keyboard.Listener keyboardListener() {
    return null;
  }
}
