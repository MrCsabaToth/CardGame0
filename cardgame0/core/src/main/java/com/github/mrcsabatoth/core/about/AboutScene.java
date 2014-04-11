package com.github.mrcsabatoth.core.about;

import com.github.mrcsabatoth.core.AppScene;

import static playn.core.PlayN.*;

import playn.core.CanvasImage;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Layer;
import playn.core.TextFormat;
import playn.core.TextLayout;

public class AboutScene extends AppScene {
    private GroupLayer base;

	@Override
	public String name() {
	    return "About";
	}

	@Override
	public void init() {
      // create and add background image layer
      base = graphics().createGroupLayer();
      graphics().rootLayer().add(base);

      Image bgImage = assets().getImage("images/bg.png");
      ImageLayer bgLayer = graphics().createImageLayer(bgImage);
      base.add(bgLayer);
      
      final float MARGIN = 10;
      float xpos = MARGIN, maxYPos = 0;

      Font titleFont = graphics().createFont("Helvetica", Style.PLAIN, 32);
      TextFormat format = new TextFormat().withFont(titleFont);
      TextLayout layout = graphics().layoutText("Card Game Concept", format);      

      float ypos = MARGIN, maxWidth = 0;
      Layer layer = createTextLayer(layout, 0xFF000000);
      layer.setTranslation(xpos, ypos);
      base.add(layer);
      ypos += layout.height();
      maxWidth = Math.max(maxWidth, layout.width());
      maxYPos = Math.max(ypos, maxYPos);

      Font font = graphics().createFont("Museo-300", Style.PLAIN, 24);
      TextFormat format2 = new TextFormat().withFont(font);
      TextLayout layout2 = graphics().layoutText("59 Days of Code, 2014", format2);      

      Layer layer2 = createTextLayer(layout2, 0xFF000000);
      layer2.setTranslation(xpos, ypos);
      base.add(layer2);
      ypos += layout2.height();
      maxWidth = Math.max(maxWidth, layout2.width());
      maxYPos = Math.max(ypos, maxYPos);
	}

	@Override
	public void shutdown() {
      base.destroy();
      base = null;
	}


	protected Layer createTextLayer(TextLayout layout, int color) {
      CanvasImage image = graphics().createImage((int)Math.ceil(layout.width()),
                                                 (int)Math.ceil(layout.height()));
      image.canvas().setFillColor(color);
      image.canvas().fillText(layout, 0, 0);
      return graphics().createImageLayer(image);
    }
}
