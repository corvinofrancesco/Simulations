// Copyright 2002-2011, University of Colorado

/*
 * CVS Info -
 * Filename : $Source$
 * Branch : $Name$
 * Modified by : $Author: samreid $
 * Revision : $Revision: 47760 $
 * Date modified : $Date: 2011-01-07 18:42:54 +0100 (ven, 07 gen 2011) $
 */
package edu.colorado.phet.common.phetcommon.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Decorates ImageLoader with buffering.
 *
 * @author ?
 * @version $Revision: 47760 $
 */
public class CachingImageLoader extends ImageLoader {
    Hashtable buffer = new Hashtable();

    public BufferedImage loadImage( String image ) throws IOException {
        if ( buffer.containsKey( image ) ) {
            return (BufferedImage) buffer.get( image );
        }
        else {
            BufferedImage imageLoad = super.loadImage( image );
            buffer.put( image, imageLoad );
            return imageLoad;
        }
    }
}
