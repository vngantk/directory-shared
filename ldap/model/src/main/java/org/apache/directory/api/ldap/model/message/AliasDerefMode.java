/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.api.ldap.model.message;


import java.util.Map;

import org.apache.directory.api.i18n.I18n;
import org.apache.directory.api.ldap.model.constants.JndiPropertyConstants;


/**
 * Type-safe derefAliases search parameter enumeration which determines the mode
 * of alias handling. Note that the jndi values of these ValuedEnums correspond
 * to the string value for the java.naming.ldap.derefAliases JNDI LDAP specific
 * property.  The integer value represents the values used in the LDAP ASN.1 for
 * different settings.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public enum AliasDerefMode
{
    /** Alias handling mode value that treats aliases like entries */
    NEVER_DEREF_ALIASES(0, "never"),

    /** Alias handling mode value that dereferences only when searching */
    DEREF_IN_SEARCHING(1, "searching"),

    /** Alias handling mode value that dereferences only in finding the base */
    DEREF_FINDING_BASE_OBJ(2, "finding"),

    /** Alias handling mode value that dereferences always */
    DEREF_ALWAYS(3, "always");

    /** Stores the integer value of each element of the enumeration */
    private int value;
    /** Stores the integer value of each element of the enumeration */
    private String jndiValue;


    /**
     * Private constructor so no other instances can be created other than the
     * public static constants in this class.
     * 
     * @param value the integer value of the enumeration.
     */
    private AliasDerefMode( int value, String jndiValue )
    {
        this.value = value;
        this.jndiValue = jndiValue;
    }


    /**
     * @return The value associated with the current element.
     */
    public int getValue()
    {
        return value;
    }


    /**
     * Gets the enumeration from by extracting the value for the JNDI LDAP
     * specific environment property, java.naming.ldap.derefAliases, from the
     * environment.
     * 
     * @param env
     *            the JNDI environment with a potential value for the
     *            java.naming.ldap.derefAliases property
     * @return the enumeration for the environment
     */
    public static AliasDerefMode getEnum( Map<String, Object> env )
    {
        String property = ( String ) env.get( JndiPropertyConstants.JNDI_LDAP_DAP_DEREF_ALIASES );

        if ( null == property )
        {
            return DEREF_ALWAYS;
        }
        else
        {
            if ( property.trim().equalsIgnoreCase( "always" ) )
            {
                return DEREF_ALWAYS;
            }
            else if ( property.trim().equalsIgnoreCase( "never" ) )
            {
                return NEVER_DEREF_ALIASES;
            }
            else if ( property.trim().equalsIgnoreCase( "finding" ) )
            {
                return DEREF_FINDING_BASE_OBJ;
            }
            else if ( property.trim().equalsIgnoreCase( "searching" ) )
            {
                return DEREF_IN_SEARCHING;
            }
            else
            {
                throw new IllegalArgumentException( I18n.err( I18n.ERR_04186, property,
                    JndiPropertyConstants.JNDI_LDAP_DAP_DEREF_ALIASES ) );
            }
        }
    }


    /**
     * Checks to see if we dereference while searching and finding the base.
     * 
     * @return true if value is DEREF_ALWAYS, false otherwise
     */
    public boolean isDerefAlways()
    {
        return this == DEREF_ALWAYS;
    }


    /**
     * Checks to see if we never dereference aliases.
     * 
     * @return true if value is NEVER_DEREF_ALIASES, false otherwise
     */
    public boolean isNeverDeref()
    {
        return this == NEVER_DEREF_ALIASES;
    }


    /**
     * Checks to see if we dereference while searching.
     * 
     * @return true if value is DEREF_ALWAYS_VAL, or DEREF_IN_SEARCHING, and
     *         false otherwise.
     */
    public boolean isDerefInSearching()
    {
        switch ( this )
        {
            case DEREF_ALWAYS:
                return true;

            case DEREF_FINDING_BASE_OBJ:
                return false;

            case DEREF_IN_SEARCHING:
                return true;

            case NEVER_DEREF_ALIASES:
                return false;

            default:
                throw new IllegalArgumentException( I18n.err( I18n.ERR_04187 ) );
        }
    }


    /**
     * Checks to see if we dereference while finding the base.
     * 
     * @return true if value is DEREF_ALWAYS, or DEREF_FINDING_BASE_OBJ, and
     *         false otherwise.
     */
    public boolean isDerefFindingBase()
    {
        switch ( this )
        {
            case DEREF_ALWAYS:
                return true;

            case DEREF_FINDING_BASE_OBJ:
                return true;

            case DEREF_IN_SEARCHING:
                return false;

            case NEVER_DEREF_ALIASES:
                return false;

            default:
                throw new IllegalArgumentException( "Class has bug: check for valid enumeration values" );
        }
    }


    /**
     * get the AliasDerefMode corresponding to the integer value passed
     *
     * @param val the AliasDerefMode's integer value
     * @return the AliasDerefMode whose value is equivalent to the given integer value
     */
    public static AliasDerefMode getDerefMode( int val )
    {
        switch ( val )
        {
            case 0:
                return NEVER_DEREF_ALIASES;

            case 1:
                return DEREF_IN_SEARCHING;

            case 2:
                return DEREF_FINDING_BASE_OBJ;

            case 3:
                return DEREF_ALWAYS;

            default:
                throw new IllegalArgumentException( "Unknown derefmode " + val );
        }
    }


    /**
     * get the AliasDerefMode corresponding to the string value {@link  #jndiValue} passed
     *
     * @param val the AliasDerefMode's string value
     * @return the AliasDerefMode whose value is equivalent to the given string value
     */
    public static AliasDerefMode getDerefMode( String val )
    {
        if ( val != null )
        {
            if ( val.equals( NEVER_DEREF_ALIASES.jndiValue ) )
            {
                return NEVER_DEREF_ALIASES;
            }

            if ( val.equals( DEREF_IN_SEARCHING.jndiValue ) )
            {
                return DEREF_IN_SEARCHING;
            }

            if ( val.equals( DEREF_FINDING_BASE_OBJ.jndiValue ) )
            {
                return DEREF_FINDING_BASE_OBJ;
            }

            if ( val.equals( DEREF_ALWAYS.jndiValue ) )
            {
                return DEREF_ALWAYS;
            }
        }

        throw new IllegalArgumentException( "Unknown derefmode " + val );
    }


    public String getJndiValue()
    {
        return jndiValue;
    }
}
