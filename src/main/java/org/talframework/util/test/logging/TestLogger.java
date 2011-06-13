/**
 * Copyright (C) 2011 Tom Spencer <thegaffer@tpspencer.com>
 *
 * This file is part of TAL.
 *
 * TAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TAL. If not, see <http://www.gnu.org/licenses/>.
 *
 * Note on dates: Year above is the year this code was built. This
 * project first created in 2008. Code was created between these two
 * years inclusive.
 */
package org.talframework.util.test.logging;

import org.apache.log4j.BasicConfigurator;

/**
 * This class can be safely used at any point in unit tests to
 * ensure that logging is configured. As there is a singleton
 * instance of this class that only initialised logging once, we
 * can be sure logging is not re-inisitalised.
 * 
 * @author Tom Spencer
 */
public class TestLogger {
    private static final TestLogger INSTANCE = new TestLogger();
    
    /**
     * Hidden constructor
     */
    private TestLogger() {
        BasicConfigurator.configure();
    }
    
    /**
     * @return The single logger instance
     */
    public static TestLogger getInstance() {
        return INSTANCE;
    }
}
