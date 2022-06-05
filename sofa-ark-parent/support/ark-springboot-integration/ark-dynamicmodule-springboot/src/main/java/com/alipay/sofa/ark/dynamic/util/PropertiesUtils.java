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
package com.alipay.sofa.ark.dynamic.util;

import com.alipay.sofa.ark.dynamic.common.SofaArkTestConstants;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Load Test Config (config/sofaark-config.properties)
 *
 * @author hanyue
 * @version : PropertiesUtils.java, v 0.1 2022年05月25日 下午1:56 hanyue Exp $
 */
public class PropertiesUtils {

    private static Properties properties = null;

    private PropertiesUtils() {
    }

    static {
        try {
            properties = PropertiesLoaderUtils
                .loadAllProperties(SofaArkTestConstants.SOFAARK_CONFIG_PROPERTIES);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @param def the def
     * @return the property
     */
    public static String getProperty(String key, String def) {
        return properties.getProperty(key, def);
    }
}