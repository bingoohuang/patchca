/*
 * Copyright (c) 2009 Piotr Piastucki
 * 
 * This file is part of Patchca CAPTCHA library.
 * 
 *  Patchca is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Patchca is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Patchca. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.bingoohuang.patchca.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;


public class SingleColorBackgroundFactory implements BackgroundFactory {

	private ColorFactory colorFactory;
	
	public SingleColorBackgroundFactory() {
		 SingleColorFactory scf = new SingleColorFactory(Color.WHITE);
		 colorFactory = scf;
	}

	public SingleColorBackgroundFactory(Color color) {
		colorFactory = new SingleColorFactory(color);
	}
	
	public void setColorFactory(ColorFactory colorFactory) {
		this.colorFactory = colorFactory;
	}

	@Override
	public void fillBackground(BufferedImage dest) {
		Graphics g = dest.getGraphics();
		g.setColor(colorFactory.getColor(0));
		g.fillRect(0, 0, dest.getWidth(), dest.getHeight());
	}

}
