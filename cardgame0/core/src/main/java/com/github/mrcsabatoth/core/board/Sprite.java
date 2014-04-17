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
package com.github.mrcsabatoth.core.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import playn.core.Asserts;
import playn.core.ImageLayer;
import playn.core.util.Callback;
import static playn.core.PlayN.log;

public class Sprite {
  private ImageLayer layer;
  private List<SpriteImage> spriteImages;
  private HashMap<String, CardSuit> spriteSuitMap;
  private HashMap<String, CardValue> spriteValueMap;
  private Callback<Sprite> callback;
  private SpriteImage current;
  private CardSuit currentSuit = CardSuit.SUIT_INVALID;
  private CardValue currentValue = CardValue.VALUE_INVALID;
  private boolean imagesDone = false; // true when images have finished loading
  private boolean dataDone = false; // true when sprite data has finished loading

  /**
   * Do not call directly. Create using {@link SpriteLoader#getSprite(String, String)}
   */
  Sprite(ImageLayer imageLayer) {
    this.layer = imageLayer;
    spriteImages = new ArrayList<SpriteImage>(0);
    spriteSuitMap = new HashMap<String, CardSuit>();
    spriteValueMap = new HashMap<String, CardValue>();
  }

  /**
   * Set callback that will be called when both the sprite data and sprite image have been loaded.
   */
  public void addCallback(Callback<Sprite> callback) {
    this.callback = callback;
    if (isReady()) {
      callback.onSuccess(this);
    }
  }

  /**
   * Return the sprite {@link ImageLayer}.
   */
  public ImageLayer layer() {
    return layer;
  }

  /**
   * Return the number of sprites.
   */
  public int numSprites() {
    return (spriteImages == null ? 0 : spriteImages.size());
  }

  /**
   * Return the height of the current sprite.
   */
  public float height() {
    if (current != null) {
      return current.height();
    } else {
      return 1;
    }
  }

  /**
   * Return true when both the sprite data and the sprite image have been loaded.
   * <p>
   * @see #addCallback(Callback)
   */
  public boolean isReady() {
    return imagesDone && dataDone;
  }

  /**
   * Set the current sprite via the index.
   * <p>
   * The index is an integer between 0 and the number of sprites (@see {@link #numSprites()})
   */
  public void setSprite(CardSuit suit, CardValue value) {
    int suitInt = suit.getValue();
    int valueInt = value.getValue();
    Asserts.checkElementIndex(suitInt * 4 + valueInt, spriteImages.size(), "Invalid card suit index");
    if (suit != currentSuit && value != currentValue) {
      current = spriteImages.get(suitInt * 4 + valueInt);
      currentSuit = suit;
      currentValue = value;
      updateLayer();
    }
  }

  /**
   * Set the current sprite via the sprite's id.
   */
  public void setSprite(String id) {
    setSprite(Asserts.checkNotNull(spriteSuitMap.get(id), "Invalid sprite suit id"),
              Asserts.checkNotNull(spriteValueMap.get(id), "Invalid sprite value id"));
  }

  /**
   * Return the width of the current sprite.
   */
  public float width() {
    if (current != null) {
      return current.width();
    } else {
      return 1;
    }
  }

  /**
   * Add a {@link SpriteImage} to the sprites.
   */
  void addSpriteImage(String key, SpriteImage spriteImage) {
    spriteSuitMap.put(key, CardSuit.getValueFromString(key));
    spriteValueMap.put(key, CardValue.getValueFromString(key));
    spriteImages.add(spriteImage);
  }

  /**
   * Should be called when the sprite data and sprite image have been loaded. Will handle calling
   * the {@link Callback} of the {@link Sprite}.
   */
  void done() {
    if (callback != null) {
      callback.onSuccess(this);
    }
  }

  /**
   * Should be called when the sprite image(s) is done loading.
   */
  void doneLoadingImages() {
    imagesDone = true;
    if (isReady()) {
      done();
    }
  }

  /**
   * Should be called when the sprite data is done loading.
   */
  void doneLoadingData() {
    dataDone = true;
    if (isReady()) {
      done();
    }
  }

  /**
   * Should be called if an error occurs when loading the sprite image or data. Will handle calling
   * the {@link Callback} of the {@link Sprite}.
   */
  void error(Throwable err) {
    if (callback != null) {
      callback.onFailure(err);
    } else {
      // don't let the error fall on deaf ears
      log().error("Error loading sprite", err);
    }
  }

  /**
   * Returns the {@link SpriteImage}s associated with this Sprite.
   */
  List<SpriteImage> spriteImages() {
    return spriteImages;
  }

  /**
   * Update the Sprite layer.
   */
  private void updateLayer() {
    if (current != null) {
      layer.setImage(current.image().subImage(current.x(), current.y(),
                                              current.width(), current.height()));
    }
  }
}
