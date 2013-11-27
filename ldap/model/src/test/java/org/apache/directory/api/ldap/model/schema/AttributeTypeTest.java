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
package org.apache.directory.api.ldap.model.schema;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycila.junit.concurrent.Concurrency;
import com.mycila.junit.concurrent.ConcurrentJunitRunner;


/**
 * Unit tests class AttributeType.
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@RunWith(ConcurrentJunitRunner.class)
@Concurrency()
public class AttributeTypeTest
{
    private MutableAttributeType attributeType;


    /**
     * Initialize attribute type instances
     */
    @Before
    public void initAttributeTypes() throws Exception
    {
        attributeType = new MutableAttributeType( "1.2.3.4" );
        attributeType.setNames( "name1", "name2" );
        attributeType.setDescription( "description" );
        attributeType.setObsolete( false );
        attributeType.setEqualityOid( "caseIgnoreMatch" );
        attributeType.setSuperiorOid( "2.3.4.5" );
    }


    @Test
    public void testToString() throws Exception
    {
        String string1 = attributeType.toString();
        System.out.println( string1 );

        assertNotNull( string1 );
        assertTrue( string1.startsWith( "attributetype (" ) );
        assertTrue( string1.contains( " NAME " ) );
        assertTrue( string1.contains( "\n\tDESC " ) );
        assertTrue( string1.contains( "\n\tSUP " ) );
        assertTrue( string1.contains( "\n\tUSAGE" ) );
    }
}
