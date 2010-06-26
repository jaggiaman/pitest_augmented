/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.distributed.message;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.InetSocketAddress;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.apache.commons.lang.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

public class RunDetailsTest {

  private RunDetails testee;
  
  @Before
  public void setUp() {
    this.testee = new RunDetails(new InetSocketAddress(0), 1);
  }

  @Test
  public void testEqualsContractKept() {
    EqualsVerifier.forClass(RunDetails.class).withPrefabValues(
        InetSocketAddress.class, new InetSocketAddress(0),
        new InetSocketAddress(1)).verify();
  }

  @Test
  public void testCanBeSerializedAndDeserialized() throws Exception {
    try {
      SerializationUtils.clone(this.testee);
    } catch (final Throwable t) {
      fail();
    }
  }
  
  @Test
  public void testIdentifierContainsOnlyAlphaNumericCharacters() {
    assertTrue(testee.getIdentifier().matches("[A-Za-z0-9]*"));
  }
}