package org.example.constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChoiceBoxesOptions {

    public static final ObservableList<String> webserverChoiceBoxOptions =
            FXCollections.observableArrayList("nginx", "apache", "cpngix");
    public static final ObservableList<String> trueFalseChoiceBoxOptions =
            FXCollections.observableArrayList("true", "false");
    public static final ObservableList<String> pkcs12KeyProtectionAlgorithmOptions = FXCollections.observableArrayList("PBEWithHmacSHA256AndAES_256", "PBEWithSHA1AndDESede");
    public static final ObservableList<String> loggingPkcs12MacProtectionAlgorithmOptions = FXCollections.observableArrayList("HmacPBESHA256", "HmacPBESHA1");
    public static final ObservableList<String> initialCryptographyProviderOptions = FXCollections.observableArrayList("EMBEDDED", "CRYPTO_PRO");
    public static final ObservableList<String> initialCryptographyHashAlghorythmOptions = FXCollections.observableArrayList("SHA1", "SHA256", "SHA512", "SHA384", "GOST_R_34_11_2012");
    public static final ObservableList<String> pkcs12certificateProtectionAlgorithmOptions = FXCollections.observableArrayList("PBEWithHmacSHA256AndAES_256", "PBEWithSHA1AndRC2_40");
    public static final ObservableList<String> initialCryptographyKeyAlghorythm = FXCollections.observableArrayList("RSA", "ECDSA", "GOST_R_34_10_2012");
    public static final ObservableList<String> initialCryptographyKeyBitsOptions = FXCollections.observableArrayList("2048", "512", "1024", "256", "128");
    public static final ObservableList<String> signHashAlgorithmOptions = FXCollections.observableArrayList("SHA1", "SHA256", "SHA512", "SHA384", "GOST_R_34_11_2012");
    public static final ObservableList<String> signKeyAlgorithmOptions = FXCollections.observableArrayList("RSA", "ECDSA", "GOST_R_34_10_2012");
    public static final ObservableList<String> signKeyLengthOptions = FXCollections.observableArrayList("2048", "512", "1024", "256", "128");
    public static final ObservableList<String> signProviderOptions = FXCollections.observableArrayList("EMBEDDED", "CRYPTO_PRO");
}

