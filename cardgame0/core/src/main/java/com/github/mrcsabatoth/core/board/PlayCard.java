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

import static playn.core.PlayN.log;

import playn.core.GroupLayer;
import playn.core.util.Callback;

public class PlayCard {

  public static String IMAGE = "sprites/cardsprites.png";
  public static String JSON = "sprites/cardsprites.json";
  private Sprite sprite;
  private CardSuit cardSuit = CardSuit.SUIT_SPADE;
  private CardValue cardValue = CardValue.VALUE_A;
  private boolean hasLoaded = false;

  public PlayCard(final GroupLayer boardLayer, final float x, final float y, CardSuit suit, CardValue value) {
    cardSuit = suit;
    cardValue = value;
	sprite = SpriteLoader.getSprite(IMAGE, JSON);

    sprite.addCallback(new Callback<Sprite>() {
      @Override
      public void onSuccess(Sprite sprite) {
        sprite.setSprite(cardSuit, cardValue);
        sprite.layer().setOrigin(0, 0);
        sprite.layer().setTranslation(x, y);
        boardLayer.add(sprite.layer());
        hasLoaded = true;
      }

      @Override
      public void onFailure(Throwable err) {
        log().error("Error loading image!", err);
      }
    });
  }

  public void update(int delta) {
    if (hasLoaded) {
      // Do some animated stuff
      //if (Math.random() > 0.95) {
      //}
      //sprite.layer().setRotation(angle);
    }
  }
}
