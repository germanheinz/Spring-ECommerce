package com.springbootangular.api.auth;



/*
* Llave secreta personalizada
* Con la llave secreta se crea nuestro token en el servidor de autorizacion, y luego ya autenticado, el cliente. lo envia al servidor de recursos
* El servidor de recurso verifica los permisos y va a validar el token que sea autentico
* a travez del servidor de autorizacion y va a utilizar esta misma llave secreta
*
*
* */
public class JwtConfig {
    public static final String SECRET_KEY = "GER";
    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA87SCgw1dU6DXYZdvCOjcU43kIDc8//IUW4ZnsOFru1HljDJr\n" +
            "pjvpexCbm9ldCuBxrLQvbJya4/MqKRJZXNBSO7r0OkyHzn306qugmTBb06FfujlH\n" +
            "lfi1O1a7fVSGrFPksUZOW/thJzMNddCTsXrRS2KTrC3gzyvWbCkFvzsF72VjgJfD\n" +
            "bqsGu8KVUF0CNdTVKialkziyC8lEtQZLidfslC8r1NRQu6I7Gqhseq9urCAbKW1o\n" +
            "MpZ3ERNQIT/vFc++NmqVTBZxQSTGdgPpYvIh6pTBC0Jmu6v9P0s4THbrYRKol6o9\n" +
            "pN86QRVuXoeWpBX0Ch0m8cX94MhtddqNPiFq9QIDAQABAoIBAEz0yG3Hz5uz469+\n" +
            "mWJv3ftIiCwgJVQRRppsQ9wYIuPhBaKS3Wl968mfwfOh3P6tR1gpAfzDKyomRD75\n" +
            "+1wSsJUKAUtTlWMAZVGhZrJZEfYvNEJjChbylZAqYERAZ9KNH19XezESnWg6d9qn\n" +
            "Raw6w1KRy14Ikr/48Nnwzk7HvaUNenCewXUEeiI6L4weJSgRdb1ZN4aYSqWYCNX8\n" +
            "xtDRYF9qhgFIYFFXREQ875jXo+hnzmdUa6FoMQjkPpLGusTB5UyBkWoJWOf/ncTe\n" +
            "EQ7g37iPh2pi2eYcotHyDMn5zRzBGTK+vXnYP5Ko3fwZxzqdqovJXKTtpuKYNI20\n" +
            "RydlNjUCgYEA+1paUOU/66UZJJiJ3ZzNtj7PeWpIIxRdGuinQl8xkvFhRQGc0+TF\n" +
            "pVmLzsKPHdWQs5D7jr8G87KHbAdZ4/3AbPHXIO03XEz4nrwYTQulrMpymDQdBg20\n" +
            "/MWkveqsur2L0F+Vwv4BrhUNcAh8zFRaXp2gSNhvsdqZe3HT9gJUOEsCgYEA+DX1\n" +
            "xhy8M9txK9y0NtdQ8rkhc1cLojpsUZrrk0vsUDM6GRmGHgA+soiNkeI+Xa2B2W+p\n" +
            "nU9X0T0B7vlpL07G8GecIISwm6K1asdeZOYTXKbqVVlmdz4+V15DsYPvu3ZDBQzl\n" +
            "iB2cGrbX7XOF6yY4SPiBqW+Kef0Qwqn5JWuFYb8CgYEAnLO1vt1NvoGNbU1UFj+d\n" +
            "2FLMUmu6UXRun0oQjMaAR5YJCFZ7CXeE6RMsoFiXlQgCUJDA5Duf/+p7lxP2LLeU\n" +
            "cddMQZDihL7P+VZW6CGsxk7opo9G0PFWtogZrvVRfpKRSfVtjsWBEqC5YfFIrLKi\n" +
            "LH6k4QVejUk1ispqVOMa0oUCgYEAmqX8MOh8do72T07e1gCHxgOFDPkiHXzrOlkd\n" +
            "CBRY84tHWpYlkxGx/sjH9CoTk4hkjBZfhs0FuS+awcuSNtCNcQINK9GgvAe/ovz/\n" +
            "Gjyjuamh/XWLOyaD7ymjYboDpzhC/6WOaaWROq/Lj33X5bBVh1mWZ/xAOktrou4G\n" +
            "dsMsQr8CgYAgeUbWpDpOTQc3WQ/sSs5GmW3cygXjkVXJDVZoy8igoCYX+1R1+AXy\n" +
            "qWoM0mG6jjHkZyYp8F3Wps/+B96TP+dwSfSUaDAYNVLWBHDP98qKj7rURxmDR6RZ\n" +
            "wpP+XpjgLJ7/xiPFBd958/9sA70q4Y7o8FdTHbMtmycWEM2DCZcbnw==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA87SCgw1dU6DXYZdvCOjc\n" +
            "U43kIDc8//IUW4ZnsOFru1HljDJrpjvpexCbm9ldCuBxrLQvbJya4/MqKRJZXNBS\n" +
            "O7r0OkyHzn306qugmTBb06FfujlHlfi1O1a7fVSGrFPksUZOW/thJzMNddCTsXrR\n" +
            "S2KTrC3gzyvWbCkFvzsF72VjgJfDbqsGu8KVUF0CNdTVKialkziyC8lEtQZLidfs\n" +
            "lC8r1NRQu6I7Gqhseq9urCAbKW1oMpZ3ERNQIT/vFc++NmqVTBZxQSTGdgPpYvIh\n" +
            "6pTBC0Jmu6v9P0s4THbrYRKol6o9pN86QRVuXoeWpBX0Ch0m8cX94MhtddqNPiFq\n" +
            "9QIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
