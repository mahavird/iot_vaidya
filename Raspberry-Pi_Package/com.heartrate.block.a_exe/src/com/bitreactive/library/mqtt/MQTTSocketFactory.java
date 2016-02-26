package com.bitreactive.library.mqtt;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class MQTTSocketFactory {

	public static SSLSocketFactory getSocketFactory(String CAcertFile, String certFile, String keyFile) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
		
        /**
         * Add BouncyCastle as a Security Provider
         */
        Security.addProvider(new BouncyCastleProvider());

        JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter().setProvider("BC");

        /**
         * Load Certificate Authority (CA) certificate
         */
        PEMParser reader = new PEMParser(new FileReader(CAcertFile));
        X509CertificateHolder caCertHolder = (X509CertificateHolder) reader.readObject();
        reader.close();

        X509Certificate caCert = certificateConverter.getCertificate(caCertHolder);

        /**
         * Load client certificate
         */
        reader = new PEMParser(new FileReader(certFile));
        X509CertificateHolder certHolder = (X509CertificateHolder) reader.readObject();
        reader.close();

        X509Certificate cert = certificateConverter.getCertificate(certHolder);

        /**
         * Load client private key
         */
        reader = new PEMParser(new FileReader(keyFile));
        Object keyObject = reader.readObject();
        reader.close();

        // PEMDecryptorProvider provider = new JcePEMDecryptorProviderBuilder().build("".toCharArray());
        JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter().setProvider("BC");

        KeyPair key;

 //       if (keyObject instanceof PEMEncryptedKeyPair) {
  //          key = keyConverter.getKeyPair(((PEMEncryptedKeyPair) keyObject).decryptKeyPair(provider));
  //      } else {
            key = keyConverter.getKeyPair((PEMKeyPair) keyObject);
   //     }

        /**
         * CA certificate is used to authenticate server
         */
        KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        caKeyStore.load(null, null);
        caKeyStore.setCertificateEntry("ca-certificate", caCert);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(caKeyStore);

        /**
         * Client key and certificates are sent to server so it can authenticate the client
         */
        KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        clientKeyStore.load(null, null);
        clientKeyStore.setCertificateEntry("certificate", cert);
        clientKeyStore.setKeyEntry("private-key", key.getPrivate(), "".toCharArray(),
                new Certificate[]{cert});

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(clientKeyStore, "".toCharArray());

        /**
         * Create SSL socket factory
         */
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        /**
         * Return the newly created socket factory object
         */
        return context.getSocketFactory();		
	}
}
