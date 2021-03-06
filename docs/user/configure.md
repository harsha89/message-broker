# Configuring Message Broker

The main configuration file of the broker can be located at ${message.broker.home}/conf/broker.yaml. The default 
values are sufficient to run the message broker in some environments (e.g. development, QA). This guide can be use to
 assist you in configuring the broker for other use cases.

## Broker-core configurations

These configurations are defined under the namespace `wso2.broker`. 

| Config                      | Default value                          | Description                                   |
|-----------------------------| ---------------------------------------|-----------------------------------------------|
| datasource:url              | jdbc:derby:database                    | Database URL.                                 |
| database:user               | root                                   | Database username                             |
| database:password           | root                                   | Database password.                            |
| authenticator:loginModule   | org.wso2.broker.core.security.authentication.jaas.BrokerLoginModule          | JAAS login module used to authenticate users. |

## AMQP transport configurations

These configurations are defined under the namespace `wso2.broker.transport.amqp`. 

| Config                      | Default value                                | Description                                                                                                  |
|-----------------------------|----------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| hostName                    | localhost                                    | Hostname configuration used in creating the server socket                                                    |
| maxRedeliveryCount          | 5                                            | Maximum number of redeliveries before publishing a message to the DLX (dead letter exchange).                |
| channelFlow:lowLimit        | 100                                          | The low limit used to enable channel flow when it is disabled. Value corresponds to the number of messages.  |
| channelFlow:highLimit       | 1000                                         | The high limit used to disable channel flow when it is enabled. Value corresponds to the number of messages. |
| plain:port                  | 5672                                         | Port used for the nonsecured transport.                                                                      |
| ssl:enabled                 | true                                         | Indicate if secured transport is enabled. Accepted values are 'true' or 'false'.                             |
| ssl:port                    | 8672                                         | Port used to bind the secured transport.                                                                     |
| ssl:protocol                | TLS                                          | Secureprotocol used to encrypt data.                                                                         |
| ssl:keyStore:type           | JKS                                          | Type of the keystore file.                                                                                   |
| ssl:keyStore:location       | resources/security/keystore.jks              | Location of the keystore file. The path can be relative to broker home or absolute path.                     |
| ssl:keyStore:password       | wso2carbon                                   | Keystore password.                                                                                           |
| ssl:keyStore:certType       | SunX509                                      | Cert type used in the keystore file.                                                                         |
| ssl:trustStore:type         | JKS                                          | Type of truststore file.                                                                                     |
| ssl:trustStore:location     | resources/security/client-truststore.jks     | Location of the keystore file. The path can be relative to broker home or absolute path.                     |
| ssl:trustStore:password     | wso2carbon                                   | Truststore password.                                                                                         |
| ssl:trustStore:certType     | SunX509                                      | Cert type used in the truststore file.                                                                       |

## Admin service related configurations

These configurations are defined under the namespace `wso2.broker.transport.amqp`. 

| Config         | Default value | Description                                           |
|----------------|---------------|-------------------------------------------------------|
| plain:hostName | localhost     | Hostname configuration used in creating server socket |
| plain:port     | 9000          | Port used for the nonsecured admin service.           |
