/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.broker.core.store.dao;

import org.wso2.broker.common.data.types.FieldTable;
import org.wso2.broker.core.Binding;
import org.wso2.broker.core.BrokerException;

import javax.sql.DataSource;

/**
 * Defines functionality required at persistence layer for managing {@link Binding}s.
 */
public abstract class BindingDao extends BaseDao {

    public BindingDao(DataSource dataSource) {
        super(dataSource);
    }

    public abstract void persist(String exchangeName, Binding binding) throws BrokerException;

    public abstract void delete(String queueName, String routingKey, String exchangeName) throws BrokerException;

    public abstract void retrieveBindingsForExchange(
            String exchangeName, BindingCollector bindingCollector) throws BrokerException;

    /**
     * Interface used as a callback to retrieve bindings from the database.
     * {@link #addBinding(String, String, FieldTable)} is invoked per each binding retrieved from the database.
     */
    @FunctionalInterface
    public interface BindingCollector {

        void addBinding(String queueName, String routingKey, FieldTable arguments) throws BrokerException;
    }
}
