/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.ark.transloader.fixture;

import com.alipay.sofa.ark.transloader.Triangulate;

/**
 * @author hanyue
 * @version : WithStaticFinalFields.java, v 0.1 2022年06月04日 9:18 PM hanyue Exp $
 */
public class WithStaticFinalFields extends NonCommonJavaObject {

    private static final String fieldValue = Triangulate.anyString();

    private static final String getName(String name) {
        return name;
    }

    private static final String getFieldValue() {
        return fieldValue;
    }

    private static String getFieldValue2() {
        return fieldValue;
    }

    static String getFieldValue3() {
        return fieldValue;
    }
}