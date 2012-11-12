// Copyright 2002-2011, University of Colorado

/*
 * CVS Info -
 * Filename : $Source$
 * Branch : $Name$
 * Modified by : $Author: samreid $
 * Revision : $Revision: 47760 $
 * Date modified : $Date: 2011-01-07 18:42:54 +0100 (ven, 07 gen 2011) $
 */
package edu.colorado.phet.common.phetcommon.model;

import java.util.Vector;

/**
 * A synchronized list of Commands to be executed in the BaseModel's update.
 *
 * @author ?
 * @version $Revision: 47760 $
 */
public class CommandQueue implements Command {
    private Vector al = new Vector();

    public int size() {
        return al.size();
    }

    public void doIt() {
        while ( !al.isEmpty() ) {
            commandAt( 0 ).doIt();
            al.remove( 0 );
        }
    }

    private Command commandAt( int i ) {
        return (Command) al.get( i );
    }

    public void addCommand( Command c ) {
        al.add( c );
    }

}
