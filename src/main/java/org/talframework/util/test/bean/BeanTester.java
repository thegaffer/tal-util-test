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
package org.talframework.util.test.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.Assert;

import org.talframework.util.beans.BeanDefinition;
import org.talframework.util.beans.definition.BeanDefinitionsSingleton;

/**
 * This class is a basic test for the behaviour of a
 * simple bean class. It will test the following:
 * <ul>
 * <li>The each properties setter returns the value 
 * set in the setter.
 * <li>That it implements hashcode and a different
 * value is produced after setting the properties.
 * <li>This is implements equals and it returns
 * correctly after setting its properties.
 * <li>That it implements toString and produces
 * a different value
 * </ul>
 * 
 * The tester will enter default values for the 
 * properties it comes across including numbers
 * strings and dates. If it encounters another
 * object as a property and that property is a
 * class it will create an empty instance of it.
 * If the property is an interface it will create
 * a proxy object. If any of these defaults is
 * undesirable then you can provide default values
 * for certain properties. Doing a lot of this
 * will somewhat negate the usefulness of this
 * helper - i.e. you might as well have tested it
 * normally.
 * 
 * @author Tom Spencer
 */
public abstract class BeanTester {
    
    /**
     * Call to perform basic checks on a bean. See the
     * class javadoc for more info in these tests.
     * 
     * @param bean The bean to test
     */
    public static void testBean(Class<?> bean) {
        testBean(bean, null);
    }
    
    /**
     * Call to perform basic checks on a bean and provide
     * some default values for the properties.
     * 
     * @param bean The bean
     * @param values The default values (any not provided use defaults)
     */
    public static void testBean(Class<?> bean, Map<String, Object> values) {
        testAccessorsAndMutators(bean, values);
        testHashCode(bean, values);
        testEquals(bean, values);
        testToString(bean, values);
    }

    /**
     * Ensure we can get/set every property and
     * when we get is the same we set!
     */
    private static void testAccessorsAndMutators(Class<?> beanClass, Map<String, Object> values) {
        BeanDefinition def = BeanDefinitionsSingleton.getInstance().getDefinition(beanClass);
        
        Object bean = def.newInstance();
        int seed = 56;
        for( String prop : def.getProperties() ) {
            if( !def.canRead(prop) ) continue;
            if( !def.canWrite(prop) ) continue;
            
            Object val = values != null ? values.get(prop) : null;
            if( val == null ) val = getSuitableValue(def.getPropertyType(prop), seed);
            
            def.write(bean, prop, val);
            Object val2 = def.read(bean, prop);
            Assert.assertEquals(val, val2);
            
            ++seed;
        }
    }
    
    /**
     * Ensure two beans setup with same value
     * have same hash code and two that are
     * different do not. Ideally do this against
     * every property
     */
    private static void testHashCode(Class<?> bean, Map<String, Object> values) {
        // TODO: Instate when Roo generators implementing hashcode
        /*BeanWrapper wrapper1 = new BeanWrapperImpl(bean);
        BeanWrapper wrapper2 = new BeanWrapperImpl(bean);
        BeanWrapper wrapper3 = new BeanWrapperImpl(bean);
        
        PropertyDescriptor[] props = wrapper1.getPropertyDescriptors();
        for( int i = 0 ; i < props.length ; i++ ) {
            if( props[i].getWriteMethod() == null ) continue;
            Object val = getSuitableValue(props[i].getPropertyType(), i);
            wrapper1.setPropertyValue(props[i].getName(), val);
            wrapper2.setPropertyValue(props[i].getName(), val);
            
            Assert.assertTrue(wrapper1.getWrappedInstance().hashCode() == wrapper2.getWrappedInstance().hashCode());
            Assert.assertFalse(wrapper1.getWrappedInstance().hashCode() == wrapper3.getWrappedInstance().hashCode());
            
            wrapper3.setPropertyValue(props[i].getName(), val);
        }*/
    }
    
    /**
     * Ensure two beans setup with the same values
     * are equals, and two that are not are not
     * equal. Ideally test this against each and
     * every property
     */
    private static void testEquals(Class<?> bean, Map<String, Object> values) {
        // TODO: Instate when Roo generators implementing equals
        /*BeanWrapper wrapper1 = new BeanWrapperImpl(bean);
        BeanWrapper wrapper2 = new BeanWrapperImpl(bean);
        BeanWrapper wrapper3 = new BeanWrapperImpl(bean);
        
        PropertyDescriptor[] props = wrapper1.getPropertyDescriptors();
        for( int i = 0 ; i < props.length ; i++ ) {
            if( props[i].getWriteMethod() == null ) continue;
            Object val = getSuitableValue(props[i].getPropertyType(), i);
            wrapper1.setPropertyValue(props[i].getName(), val);
            wrapper2.setPropertyValue(props[i].getName(), val);
            
            Assert.assertTrue(wrapper1.getWrappedInstance().equals(wrapper2.getWrappedInstance()));
            Assert.assertFalse(wrapper1.getWrappedInstance().equals(wrapper3.getWrappedInstance()));
            
            wrapper3.setPropertyValue(props[i].getName(), val);
        }*/
    }
    
    /**
     * We don't care what it is but we do not expect
     * the bean to have the basic toString output
     */
    private static void testToString(Class<?> bean, Map<String, Object> values) {
        try {
            Assert.assertNotNull(bean.getDeclaredMethod("toString", (Class<?>[])null));
        }
        catch( NoSuchMethodException e) {
            Assert.assertTrue("No toString method declared", false);
        }
    }
    
    /**
     * Helper to get a suitable value given a property
     * type.
     * 
     * @param type The type of the property
     * @param seed The seed value
     * @return The value
     */
    private static Object getSuitableValue(Class<?> type, int seed) {
        try {
            Object val = null;
            
            if( String.class.isAssignableFrom(type) ) val = "Test" + seed;
            else if( Date.class.isAssignableFrom(type) ) val = new Date();
            else if( Double.class.isAssignableFrom(type) ) val = new Double(seed);
            else if( double.class.equals(type) ) val = (double)seed;
            else if( Float.class.isAssignableFrom(type) ) val = new Float(seed);
            else if( float.class.equals(type) ) val = (float)seed;
            else if( Long.class.isAssignableFrom(type) ) val = new Long(seed);
            else if( long.class.equals(type) ) val = (long)seed;
            else if( Integer.class.isAssignableFrom(type) ) val = new Integer(seed);
            else if( int.class.equals(type) ) val = (int)seed;
            else if( Short.class.isAssignableFrom(type) ) val = new Short((short)seed);
            else if( short.class.equals(type) ) val = (short)seed;
            else if( Character.class.isAssignableFrom(type) ) val = new Character((char)seed);
            else if( char.class.equals(type) ) val = (char)seed;
            else if( Boolean.class.isAssignableFrom(type) ) val = new Boolean(seed != 0);
            else if( boolean.class.equals(type) ) val = (seed != 0);
            
            else if( type.isArray() ) val = Array.newInstance(type.getComponentType(), 1);
            else if( SortedSet.class.isAssignableFrom(type) ) val = new TreeSet<Object>();
            else if( Set.class.isAssignableFrom(type) ) val = new HashSet<Object>();
            else if( List.class.isAssignableFrom(type) ) val = new ArrayList<Object>();
            else if( Map.class.isAssignableFrom(type) ) val = new HashMap<Object, Object>();

            // Interface
            else if( type.isInterface() ) {
                
            }
            
            // try and create instance
            else type.newInstance();
            
            return val;
        }
        catch( Exception e ) {
            throw new IllegalArgumentException("Cannot create value for property: " + e);
        }
    }
}
