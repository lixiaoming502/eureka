/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.eureka2.model.interest;

import com.netflix.eureka2.model.StdModelsInjector;
import com.netflix.eureka2.model.instance.InstanceInfo;
import com.netflix.eureka2.testkit.data.builder.SampleInstanceInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author David Liu
 */
public class StdAbstractPatternInterestTest {

    static {
        StdModelsInjector.injectStdModels();
    }

    @Test
    public void testCompiledPatternNotPartOfEqualsOrHashCode() {
        InstanceInfo toMatch = SampleInstanceInfo.Backend.build();

        StdApplicationInterest interest = new StdApplicationInterest(toMatch.getApp(), Interest.Operator.Like);
        Assert.assertNull(interest.getCompiledPattern());

        StdApplicationInterest interestCompiled = new StdApplicationInterest(toMatch.getApp(), Interest.Operator.Like);
        interestCompiled.matches(toMatch);  // compiles the pattern
        Assert.assertNotNull(interestCompiled.getCompiledPattern());

        Assert.assertEquals(interest, interestCompiled);
    }
}
