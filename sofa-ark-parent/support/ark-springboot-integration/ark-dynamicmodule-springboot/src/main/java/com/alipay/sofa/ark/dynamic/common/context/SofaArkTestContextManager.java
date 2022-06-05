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
package com.alipay.sofa.ark.dynamic.common.context;

import com.alipay.sofa.ark.dynamic.listener.SofaArkTestExecutionListener;
import com.alipay.sofa.ark.dynamic.util.CommonUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * The type Sofa ark test context manager.
 *
 * @author hanyue
 * @version : SofaArkTestContextManager.java, v 0.1 2022年05月24日 下午9:37 hanyue Exp $
 */
public class SofaArkTestContextManager {

    private final SofaArkTestContext sofaArkTestContext;

    private final List<SofaArkTestExecutionListener> testExecutionListeners = new ArrayList<>();

    /**
     * Instantiates a new Sofa ark test context manager.
     *
     * @param testClass the test class
     */
    public SofaArkTestContextManager(Class<?> testClass) {
        this.sofaArkTestContext = new SofaArkTestContext(testClass);
        registerTestExecutionListeners(CommonUtils.newArrayList(ServiceLoader.load(
                SofaArkTestExecutionListener.class, getClass().getClassLoader())));
    }

    /**
     * Register test execution listeners.
     *
     * @param testExecutionListeners the test execution listeners
     */
    public void registerTestExecutionListeners(List<SofaArkTestExecutionListener> testExecutionListeners) {
        registerTestExecutionListeners(testExecutionListeners
                .toArray(new SofaArkTestExecutionListener[0]));
    }

    /**
     * Register test execution listeners.
     *
     * @param testExecutionListeners the test execution listeners
     */
    public void registerTestExecutionListeners(SofaArkTestExecutionListener... testExecutionListeners) {
        Collections.addAll(this.testExecutionListeners, testExecutionListeners);
        AnnotationAwareOrderComparator.sort(this.testExecutionListeners);
    }

    /**
     * Gets test execution listeners.
     *
     * @return the test execution listeners
     */
    public final List<SofaArkTestExecutionListener> getTestExecutionListeners() {
        return testExecutionListeners;
    }

    /**
     * Gets sofa ark test context.
     *
     * @return the sofa ark test context
     */
    public final SofaArkTestContext getSofaArkTestContext() {
        return sofaArkTestContext;
    }

    private List<SofaArkTestExecutionListener> getReversedTestExecutionListeners() {
        List<SofaArkTestExecutionListener> listenersReversed = new ArrayList<>(
                getTestExecutionListeners());
        Collections.reverse(listenersReversed);
        return listenersReversed;
    }

    /**
     * Before install master.
     *
     * @throws Exception the exception
     */
    public void beforeInstallMaster() throws Exception {
        for (SofaArkTestExecutionListener testExecutionListener : getTestExecutionListeners()) {
            try {
                testExecutionListener.beforeInstallMaster(getSofaArkTestContext(),
                        getSofaArkTestContext().getTestClass().getClassLoader());
            } catch (Throwable ex) {
                ReflectionUtils.rethrowException(ex);
            }
        }
    }

    /**
     * After install master.
     *
     * @throws Exception the exception
     */
    public void afterInstallMaster() throws Exception {
        for (SofaArkTestExecutionListener testExecutionListener : getTestExecutionListeners()) {
            try {
                testExecutionListener.afterInstallMaster(getSofaArkTestContext(),
                        getSofaArkTestContext().getTestClass().getClassLoader());
            } catch (Throwable ex) {
                ReflectionUtils.rethrowException(ex);
            }
        }
    }

    /**
     * Before install biz.
     *
     * @throws Exception the exception
     */
    public void beforeInstallBiz() throws Exception {
        for (SofaArkTestExecutionListener testExecutionListener : getTestExecutionListeners()) {
            try {
                testExecutionListener.beforeInstallBiz(getSofaArkTestContext(),
                        getSofaArkTestContext().getTestClass().getClassLoader());
            } catch (Throwable ex) {
                ReflectionUtils.rethrowException(ex);
            }
        }
    }

    /**
     * After install biz.
     *
     * @throws Exception the exception
     */
    public void afterInstallBiz() throws Exception {
        for (SofaArkTestExecutionListener testExecutionListener : getTestExecutionListeners()) {
            try {
                testExecutionListener.afterInstallBiz(getSofaArkTestContext(),
                        getSofaArkTestContext().getTestClass().getClassLoader());
            } catch (Throwable ex) {
                ReflectionUtils.rethrowException(ex);
            }
        }
    }
}