package com.github.mrcsabatoth.core.board;

import com.github.mrcsabatoth.core.AppScene;

import static playn.core.PlayN.*;

import playn.core.CanvasImage;
import playn.core.GroupLayer;
import playn.core.ImageLayer;
import playn.core.Image;

public class BoardScene extends AppScene {
    private GroupLayer base;

	@Override
	public String name() {
	    return "Play";
	}

	@Override
	public void init() {
      // create and add background image layer
      base = graphics().createGroupLayer();
      graphics().rootLayer().add(base);

      // draw a green flat background
      CanvasImage bgtile = graphics().createImage(64, 64);
      bgtile.canvas().setFillColor(0xFF5da52e);
      bgtile.canvas().fillRect(0, 0, 64, 64);
      bgtile.canvas().setStrokeColor(0xFF39611e);
      bgtile.canvas().strokeRect(0, 0, 64, 64);
      bgtile.setRepeat(true, true);

      ImageLayer bg = graphics().createImageLayer(bgtile);
      bg.setWidth(graphics().width());
      bg.setHeight(graphics().height());
      base.add(bg);
      
      // MT - Card load test - Show 3 of hearts
      // Each card is 300px x 300px
      Image cardImage = assets().getImage("images/CardArray.png");
      
      ImageLayer layer = graphics().createImageLayer(bgtile);
      layer.setScale(.2f, .2f);
      layer.setTranslation(0, 200);
      //Usage --
      //base.setImage(current.image().subImage(current.x(), current.y(),
      //layer.width(), current.height()));
      layer.setImage(cardImage.subImage(0, 0, 300, 300));
      base.add(layer);
      
      // MT - Test to see how to load multiple cards
      ImageLayer layer2 = graphics().createImageLayer(bgtile);
      layer2.setScale(.2f, .2f);
      layer2.setTranslation(300, 200);
      layer2.setImage(cardImage.subImage(600, 900, 300, 300));      
      base.add(layer2);
	}

	@Override
	public void shutdown() {
      base.destroy();
      base = null;
	}
}
