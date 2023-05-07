package PXY;


import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


/**
 * RSA签名和验签工具类
 * 
 * @author 钱多多--付广才
 *
 */
public class RSAToolkit {

    //private static final Logger log = Logger.getLogger(RSAToolkit.class);
    public static final String RSA = "RSA";
    public static final String MD5_WITH_RSA = "MD5withRSA";
    public static final String SHA1_WITH_RSA = "SHA1WithRSA";
    public static final String SHA256_WITH_RSA = "SHA256WithRSA";

    private RSAToolkit() {
    }

    /**
     * 使用MD5WithRSA算法签名数据
     * 
     * @param privateKey
     * @param signByte
     * @return
     */
    public static byte[] signMD5WithRSA(byte[] privateKey, byte[] signByte) {
        return sign(privateKey, signByte, MD5_WITH_RSA);
    }

    /**
     * 使用SHA1WithRSA算法签名数据(RSA)
     * 
     * @param privateKey
     * @param signByte
     * @return
     */
    public static byte[] signSHA1WithRSA(byte[] privateKey, byte[] signByte) {
        return sign(privateKey, signByte, SHA1_WITH_RSA);
    }

    /**
     * 使用SHA256WithRSA算法签名数据(RSA2)
     * 
     * @param privateKey
     * @param signByte
     * @return
     */
    public static byte[] signSHA256WithRSA(byte[] privateKey, byte[] signByte) {
        return sign(privateKey, signByte, SHA256_WITH_RSA);
    }

    /**
     * 使用指定的签名算法签名数据
     * 
     * @param privateKey
     *            私钥
     * @param signByte
     *            待签名数据
     * @param signatureAlgorithm
     *            签名算法
     * @return
     */
    public static byte[] sign(byte[] privateKey, byte[] signByte, String signatureAlgorithm) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyf = KeyFactory.getInstance(RSA);
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            Signature signet = Signature.getInstance(signatureAlgorithm);
            signet.initSign(myprikey);
            signet.update(signByte);
            return signet.sign();
        } catch (Exception e) {
            //log.error("签名异常", e);
        }
        return null;
    }

    /**
     * 
     * RSA签名校验验证
     * 
     * @param publicKey：RSA公钥
     * @param signByte：待签名源内容
     * @param signedByte：RSA签名结果
     * @return
     */
    public static boolean verifySignSHA1WithRSA(byte[] publicKey, byte[] signByte, byte[] signedByte) {
        return verifySign(publicKey, signByte, signedByte, SHA1_WITH_RSA);
    }

    /**
     * 
     * RSA签名校验验证
     * 
     * @param publicKey：RSA公钥
     * @param signByte：待签名源内容
     * @param signedByte：RSA签名结果
     * @return
     */
    public static boolean verifySignSHA256WithRSA(byte[] publicKey, byte[] signByte, byte[] signedByte) {
        return verifySign(publicKey, signByte, signedByte, SHA256_WITH_RSA);
    }

    /**
     * 
     * RSA签名校验验证
     * 
     * @param publicKey：RSA公钥
     * @param signByte：待签名源内容
     * @param signedByte：RSA签名结果
     * @return
     */
    public static boolean verifySignMD5WithRSA(byte[] publicKey, byte[] signByte, byte[] signedByte) {
        return verifySign(publicKey, signByte, signedByte, MD5_WITH_RSA);
    }

    /**
     * 使用指定的签名算法校验签名是否合法
     * 
     * @param publicKey
     *            公钥
     * @param signByte
     *            待校验的数据
     * @param signedByte
     *            签名数据
     * @param charset
     *            编码
     * @param signatureAlgorithm
     *            签名算法
     * @return
     */
    public static boolean verifySign(byte[] publicKey, byte[] signByte, byte[] signedByte, String signatureAlgorithm) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signetcheck = Signature.getInstance(signatureAlgorithm);
            signetcheck.initVerify(pubKey);
            signetcheck.update(signByte);
            return signetcheck.verify(signedByte);
        } catch (Exception e) {
            //log.error("验签异常", e);
        }
        return false;
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     * 
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        return cipher.doFinal(encryptedData);
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     * 
     * @param encryptedData
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        return cipher.doFinal(encryptedData);
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        return cipher.doFinal(data);
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     * 
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        return cipher.doFinal(data);
    }

}
