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
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.messaging.integration.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.wso2.broker.amqp.AmqpServerConfiguration;
import org.wso2.broker.amqp.Server;
import org.wso2.broker.common.BrokerConfigProvider;
import org.wso2.broker.common.StartupContext;
import org.wso2.broker.core.Broker;
import org.wso2.broker.core.configuration.BrokerConfiguration;
import org.wso2.broker.core.security.authentication.jaas.BrokerLoginModule;
import org.wso2.broker.core.security.authentication.user.User;
import org.wso2.broker.core.security.authentication.user.UserStoreManager;
import org.wso2.broker.core.security.authentication.user.UsersFile;
import org.wso2.broker.rest.BrokerRestServer;
import org.wso2.broker.rest.config.RestServerConfiguration;
import org.wso2.messaging.integration.util.DbUtils;
import org.wso2.messaging.integration.util.TestConfigProvider;
import org.wso2.messaging.integration.util.TestConstants;

import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

public class SuiteInitializer {
    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SuiteInitializer.class);

    private Broker broker;

    private Server server;

    private BrokerRestServer restServer;

    @Parameters({ "broker-port", "broker-ssl-port", "broker-hostname", "admin-username", "admin-password" ,
                  "broker-rest-port"})
    @BeforeSuite
    public void beforeSuite(String port, String sslPort, String hostname, String adminUsername, String adminPassword,
            String restPort, ITestContext context)
            throws Exception {
        LOGGER.info("Starting broker on " + port + " for suite " + context.getSuite().getName());
        StartupContext startupContext = new StartupContext();
        TestConfigProvider configProvider = new TestConfigProvider();

        BrokerConfiguration brokerConfiguration = new BrokerConfiguration();
        BrokerConfiguration.AuthenticationConfiguration authenticationConfiguration = new BrokerConfiguration
                .AuthenticationConfiguration();
        authenticationConfiguration.setLoginModule(BrokerLoginModule.class.getCanonicalName());
        brokerConfiguration.setAuthenticator(authenticationConfiguration);
        configProvider.registerConfigurationObject(BrokerConfiguration.NAMESPACE, brokerConfiguration);

        AmqpServerConfiguration serverConfiguration = new AmqpServerConfiguration();
        serverConfiguration.setHostName(hostname);
        serverConfiguration.getPlain().setPort(port);
        serverConfiguration.getSsl().setEnabled(true);
        serverConfiguration.getSsl().setPort(sslPort);
        serverConfiguration.getSsl().getKeyStore().setLocation(TestConstants.KEYSTORE_LOCATION);
        serverConfiguration.getSsl().getKeyStore().setPassword(TestConstants.KEYSTORE_PASSWORD);
        serverConfiguration.getSsl().getTrustStore().setLocation(TestConstants.TRUST_STORE_LOCATION);
        serverConfiguration.getSsl().getTrustStore().setPassword(TestConstants.TRUST_STORE_PASSWORD);
        configProvider.registerConfigurationObject(AmqpServerConfiguration.NAMESPACE, serverConfiguration);

        RestServerConfiguration restConfig = new RestServerConfiguration();
        restConfig.getPlain().setPort(restPort);
        configProvider.registerConfigurationObject(RestServerConfiguration.NAMESPACE, restConfig);

        startupContext.registerService(BrokerConfigProvider.class, configProvider);

        DbUtils.setupDB();
        startupContext.registerService(DataSource.class, DbUtils.getDataSource());
        restServer = new BrokerRestServer(startupContext);
        broker = new Broker(startupContext);
        broker.startMessageDelivery();
        server = new Server(startupContext);
        server.start();
        restServer.start();

        //Add test user
        UsersFile usersFile = new UsersFile();
        User testUser = new User();
        testUser.setUsername(adminUsername);
        testUser.setPassword(adminPassword);
        List<User> userList = new LinkedList<>();
        userList.add(testUser);
        usersFile.setUsers(userList);
        UserStoreManager.addUser(usersFile.getUsers().get(0));

    }

    @AfterSuite
    public void afterSuite(ITestContext context) throws Exception {
        restServer.stop();
        server.stop();
        broker.stopMessageDelivery();
        LOGGER.info("Stopped broker for suite " + context.getSuite().getName());
    }

}
