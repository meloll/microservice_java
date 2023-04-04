package com.github.meloll.hroauth.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
public class OauthProperties {

    private String providerUri;

    private JksProperties jks;


    public String getProviderUri() {
        return providerUri;
    }

    public JksProperties getJksProperties() {
        return jks;
    }

    public void setProviderUri(String providerUri) {
        this.providerUri = providerUri;
    }

    public JksProperties getJks() {
        return jks;
    }

    public void setJks(JksProperties jks) {
        this.jks = jks;
    }

    static class JksProperties{

        private String keyPass;

        private String storePass;

        private String alias;

        private String path;

        public String getKeyPass() {
            return keyPass;
        }

        public void setKeyPass(String keyPass) {
            this.keyPass = keyPass;
        }

        public String getStorePass() {
            return storePass;
        }

        public void setStorePass(String storePass) {
            this.storePass = storePass;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }


}
