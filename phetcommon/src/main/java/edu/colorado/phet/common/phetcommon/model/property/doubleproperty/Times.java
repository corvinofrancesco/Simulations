// Copyright 2002-2011, University of Colorado
package edu.colorado.phet.common.phetcommon.model.property.doubleproperty;

import edu.colorado.phet.common.phetcommon.model.property.ObservableProperty;
import edu.colorado.phet.common.phetcommon.util.function.Function0;

/**
 * The product of two properties.
 *
 * @author Sam Reid
 */
public class Times extends CompositeDoubleProperty {
    public Times( final ObservableProperty<Double> a, final ObservableProperty<Double> b ) {
        super( new Function0<Double>() {
            public Double apply() {
                return a.get() * b.get();
            }
        }, a, b );
    }
}