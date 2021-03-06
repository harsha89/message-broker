/*
 *   Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.wso2.broker.core.security.authentication.sasl.plain;

import org.wso2.broker.core.security.authentication.sasl.SaslServerBuilder;

import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.SaslServerFactory;

/**
 * Class implements {@link SaslServerBuilder} to build the PLAIN SASL provider mechanism
 */
public class PlainSaslServerBuilder implements SaslServerBuilder {

    public String getMechanismName() {
        return PlainSaslServer.PLAIN_MECHANISM;
    }

    @Override
    public CallbackHandler getCallbackHandler() {
        return new PlainSaslCallbackHandler();
    }

    @Override
    public Map<String, ?> getProperties() {
        return null;
    }

    @Override
    public Class<? extends SaslServerFactory> getServerFactoryClass() {
        return PlainSaslServerFactory.class;
    }
}
