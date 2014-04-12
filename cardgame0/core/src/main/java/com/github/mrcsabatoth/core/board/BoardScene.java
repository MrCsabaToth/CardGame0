package com.github.mrcsabatoth.core.board;

import com.github.mrcsabatoth.core.AppScene;

import static playn.core.PlayN.*;

import playn.core.CanvasImage;
import playn.core.GroupLayer;
import playn.core.ImageLayer;

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
	}

	@Override
	public void shutdown() {
      base.destroy();
      base = null;
	}
}
