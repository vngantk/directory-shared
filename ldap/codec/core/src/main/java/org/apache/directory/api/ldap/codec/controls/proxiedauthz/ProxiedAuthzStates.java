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
package org.apache.directory.api.ldap.codec.controls.proxiedauthz;


import org.apache.directory.api.asn1.ber.grammar.Grammar;
import org.apache.directory.api.asn1.ber.grammar.States;


/**
 * This class store the ProxiedAuthzControl's grammar constants. It is also used
 * for debugging purposes.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public enum ProxiedAuthzStates implements States
{
    /** The END_STATE */
    END_STATE,

    // =========================================================================
    // Proxied Authz control grammar states
    // =========================================================================

    /** Sequence Tag */
    START_STATE,

    /** authzId */
    AUTHZ_ID_STATE,

    /** terminal state */
    LAST_PA_STATE;

    /**
     * Get the grammar name
     * 
     * @return The grammar name
     */
    public String getGrammarName()
    {
        return "PROXIED_AUTHZ_GRAMMAR";
    }


    /**
     * Get the grammar name
     * 
     * @param grammar The grammar class
     * @return The grammar name
     */
    public String getGrammarName( Grammar<?> grammar )
    {
        if ( grammar instanceof ProxiedAuthzGrammar )
        {
            return "PROXIED_AUTHZ_GRAMMAR";
        }

        return "UNKNOWN GRAMMAR";
    }


    /**
     * Get the string representing the state
     * 
     * @param state The state number
     * @return The String representing the state
     */
    public String getState( int state )
    {
        return ( ( state == END_STATE.ordinal() ) ? "PA_END_STATE" : name() );
    }


    /**
     * {@inheritDoc}
     */
    public boolean isEndState()
    {
        return this == END_STATE;
    }


    /**
     * {@inheritDoc}
     */
    public ProxiedAuthzStates getStartState()
    {
        return START_STATE;
    }
}