package n.baldyoung.Encryption;


import java.util.Arrays;

/**
 * 对称加密服务
 * 数据加密模块，提供对字符串和字节数据的加密和解密服务。
 */
public class EncryptionModule {
    // 默认密钥权值
    static private byte DEFAULT_WEIGHT_VALUE = 3;
    // 密钥对应的字符串
    private String secretKey;
    // 密钥
    private byte[] keys;
    // 密钥的权值
    private byte weightValue;
    private EncryptionModule(String tSecretKey){
        this.secretKey = tSecretKey;
        this.keys = this.secretKey.getBytes();
        this.weightValue = getWeightValue(this.keys);
    }
    public byte[] encrypt(byte[] data) {
        byte[] newData = Arrays.copyOf(data, data.length);
        pack(newData, this.keys);
        return newData;
    }
    public byte[] decode(byte[] data) {
        byte[] newData = Arrays.copyOf(data, data.length);
        unpack(newData, this.keys);
        return newData;
    }
    private void pack(byte[] data, byte[] key) {
        int i=0;
        while(i < data.length) {
            data[i] = (byte)(data[i] ^ getKeyStatus(key, i) ^ this.weightValue);
            i++;
        }
    }
    private void unpack(byte[] data, byte[] key) {
        int i=0;
        while(i < data.length) {
            data[i] = (byte)(data[i] ^ getKeyStatus(key, i) ^ this.weightValue);
            i++;
        }
    }
    static private byte getKeyStatus(byte[] key, int p) {
        int t = ( p >= key.length ? (p % key.length) : p);
        return key[t];
    }
    static private byte getWeightValue(byte[] key) {
        byte weight = DEFAULT_WEIGHT_VALUE;
        for(byte temp : key) {
            weight = (byte)(weight ^ temp);
        }
        return weight;
    }
    static public EncryptionModule getInstance(String StringKey) throws NullPointerException{
        if (null == StringKey) {
            throw new NullPointerException();
        }
        return new EncryptionModule(StringKey);
    }



}
