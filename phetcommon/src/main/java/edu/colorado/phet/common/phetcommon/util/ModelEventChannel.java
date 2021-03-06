// Copyright 2002-2011, University of Colorado

/*
 * CVS Info -
 * Filename : $Source$
 * Branch : $Name$
 * Modified by : $Author: samreid $
 * Revision : $Revision: 54200 $
 * Date modified : $Date: 2011-07-19 02:45:40 +0200 (mar, 19 lug 2011) $
 */
package edu.colorado.phet.common.phetcommon.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.SwingUtilities;

/**
 * MvcEventChannel
 * <p/>
 * A specialization of EventChannel that executes calls to specified event listeners in the Swing
 * thread. These listeners are indicated by their type: SwingThreadModelListener.
 *
 * @author Ron LeMaster
 * @version $Revision: 54200 $
 */
public class ModelEventChannel extends EventChannel {

    public ModelEventChannel( Class interf ) {
        super( interf );
    }

    protected void invokeMethod( final Method method,
                                 final Object target,
                                 final Object[] args ) throws InvocationTargetException, IllegalAccessException {

        // If the target listener is part of the view, then invoke the callback method in the
        // Swing dispatch thread. Otherwise, call the parent class behavior
        if ( target instanceof SwingThreadModelListener ) {
            SwingUtilities.invokeLater( new Runnable() {
                public void run() {
                    try {
                        ModelEventChannel.super.invokeMethod( method, target, args );
                    }
                    catch ( InvocationTargetException e ) {
                        e.printStackTrace();
                    }
                    catch ( IllegalAccessException e ) {
                        e.printStackTrace();
                    }
                }
            } );
        }
        else {
            super.invokeMethod( method, target, args );
        }
    }
}
