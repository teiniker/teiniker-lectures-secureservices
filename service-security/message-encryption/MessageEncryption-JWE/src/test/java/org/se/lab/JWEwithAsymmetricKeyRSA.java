package org.se.lab;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;

public class JWEwithSharedSymmetricKey
{
    byte[] key128 =
    { (byte) 177, (byte) 119, (byte) 33, (byte) 13, (byte) 164, (byte) 30, (byte) 108, (byte) 121, (byte) 207,
            (byte) 136, (byte) 107, (byte) 242, (byte) 12, (byte) 224, (byte) 19, (byte) 226 };

    @Test
    public void testEncryption() throws KeyLengthException, JOSEException
    {
        // Create the header
        JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);

        // Set the plain text
        Payload payload = new Payload("Hello world!");

        // Create the JWE object and encrypt it
        JWEObject jweObject = new JWEObject(header, payload);
        jweObject.encrypt(new DirectEncrypter(key128));

        // Serialise to compact JOSE form...
        String jweString = jweObject.serialize();

        System.out.println(jweString);
    }

    @Test
    public void testDecryption() throws KeyLengthException, JOSEException, ParseException
    {
        String jweString = "eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiZGlyIn0..0bxqq9GF8lvQJ1si.fB7kmdL3MXZkXU6h.8TQzsHO3n2y9L08qF00uVQ";

        // Create the JWE object and encrypt it
        JWEObject jweObject = JWEObject.parse(jweString);

        // Decrypt
        jweObject.decrypt(new DirectDecrypter(key128));

        // Get the plain text
        Payload payload = jweObject.getPayload();
        Assert.assertEquals("Hello world!", payload.toString());
    }
}
