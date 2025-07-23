package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.example.constants.ChoiceBoxesOptions;
import org.example.service.impl.ConfigFileServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UiController {

    private final ConfigFileServiceImpl configFileService = new ConfigFileServiceImpl();

    private final Map<Button, GridPane> buttonGridPaneMap = new HashMap<>();

    private final Map<TextField, String> configTextFields = new HashMap<>();

    private final Map<ChoiceBox, String> choiceBoxes = new HashMap<>();


    @FXML
    private TextField aecaAiaDistributionPointPatternField;

    @FXML
    private TextField aecaAiaPublishPointPatternField;

    @FXML
    private TextField aecaCdpProtField;

    @FXML
    private GridPane aecaConfigPane;

    @FXML
    private TextField aecaCrlDistributionPointPatternField;

    @FXML
    private TextField aecaCrlPublishPointPattern;

    @FXML
    private TextField aecaDeltaCrlDistributionPointPatternField;

    @FXML
    private TextField aecaGroupField;

    @FXML
    private TextField aecaOcspPatternField;

    @FXML
    private TextField aecaPathField;

    @FXML
    private TextField aecaUserField;

    @FXML
    private TextField aecaVaPortField;

    @FXML
    private GridPane allServicesEnvPane;

    @FXML
    private TextField apiKeyField;

    @FXML
    private TextField archiveCronField;

    @FXML
    private TextField archiveEnabledField;

    @FXML
    private TextField archiveMilisAgoField;

    @FXML
    private TextField archivePathField;

    @FXML
    private GridPane backupConfigPane;

    @FXML
    private TextField backupPathField;

    @FXML
    private TextField blockInnactiveAccountCronField;

    @FXML
    private TextField blockInnactiveAccountDelayField;

    @FXML
    private Button buttonAllService;

    @FXML
    private Button buttonBackup;

    @FXML
    private Button buttonCa;

    @FXML
    private Button buttonDatabase;

    @FXML
    private Button buttonEventDilivery;

    @FXML
    private Button buttonGateway;

    @FXML
    private Button buttonLogs;

    @FXML
    private Button buttonMemory;

    @FXML
    private Button buttonPublisher;

    @FXML
    private Button buttonScripts;

    @FXML
    private Button buttonSecurity;

    @FXML
    private Button buttonSettings;

    @FXML
    private Button buttonSubjects;

    @FXML
    private Button buttonUser;

    @FXML
    private Button buttonValidation;

    @FXML
    private Button buttonWebserver;

    @FXML
    private TextField certificaeteSslPath;

    @FXML
    private GridPane certificateAuthorityEnvPane;

    @FXML
    private TextField certificateServerNameField;

    @FXML
    private TextField certificatesAccountPath;

    @FXML
    private StackPane contentStack;

    @FXML
    private TextField crlCleanQueuesField;

    @FXML
    private TextField crlSchedulerField;

    @FXML
    private TextField cryptotokenPathField;

    @FXML
    private GridPane databaseConfigPane;

    @FXML
    private TextField databaseHostField;

    @FXML
    private TextField databaseNameField;

    @FXML
    private TextField databasePasswordField;

    @FXML
    private TextField databasePortField;

    @FXML
    private TextField databaseUsernameField;

    @FXML
    private ChoiceBox<String> emailEnabledChoiseBox;

    @FXML
    private TextField emailFromField;

    @FXML
    private TextField emailHostField;

    @FXML
    private TextField emailLoginField;

    @FXML
    private TextField emailPasswordField;

    @FXML
    private TextField emailPortField;

    @FXML
    private ChoiceBox<String> emailProtocolChoiceBox;

    @FXML
    private TextField emailScheduleField;

    @FXML
    private ChoiceBox<String> emailSmtpAuthChoiceBox;

    @FXML
    private ChoiceBox<String> emailStartTlsChoiceBox;

    @FXML
    private ChoiceBox<String> enableGcDiagnositcsChoiseBox;

    @FXML
    private ChoiceBox<String> enableHeapDumpChoiseBox;

    @FXML
    private TextField encryptionKeyPathField;

    @FXML
    private TextField environmentPathField;

    @FXML
    private GridPane eventDeliveryEnvPane;

    @FXML
    private GridPane gatewayEnvPane;

    @FXML
    private TextField hostnameField;

    @FXML
    private TextField httpPortField;

    @FXML
    private TextField httpsPortField;

    @FXML
    private TextField initialAdminPrincipalField;

    @FXML
    private TextField initialCaCommonNameField;

    @FXML
    private TextField initialClientPasswordField;

    @FXML
    private ChoiceBox<String> initialCryptographyHashAlghorythmChoiceBox;

    @FXML
    private ChoiceBox<String> initialCryptographyKeyAlghorythmChoiceBox;

    @FXML
    private ChoiceBox<String> initialCryptographyKeyBitsChoiceBox;

    @FXML
    private TextField initialServerPasswordField;

    @FXML
    private ChoiceBox<String> initialCryptographyProviderChoiceBox;

    @FXML
    private TextField internalHttpConnectionTimeoutField;

    @FXML
    private TextField internalHttpReadTimeoutField;

    @FXML
    private TextField issuersNameField;

    @FXML
    private TextField ldapAutomaticallyCertificatesPublicationCronField;

    @FXML
    private ChoiceBox<String> ldapAutomaticallyCertificatesPublicationEnableChoiseBox;

    @FXML
    private TextField ldapCleanQueuesField;

    @FXML
    private TextField ldapPartitionSizeField;

    @FXML
    private TextField ldapSyncConnectionPointField;

    @FXML
    private TextField ldapSyncResource;

    @FXML
    private ChoiceBox<String> loggingPkcs12MacProtectionAlgorithmChoiceBox;

    @FXML
    private ChoiceBox<String> pkcs12certificateProtectionAlgorithmChoiceBox;

    @FXML
    private ChoiceBox<String> loggingResponseChoiceBox;

    @FXML
    private ChoiceBox<String> loggingSqlChoiceBox;

    @FXML
    private TextField logsBAseField;

    @FXML
    private GridPane logsEnvPane;

    @FXML
    private TextField logsFileMaxSizeField;

    @FXML
    private TextField logsMaxHistoryField;

    @FXML
    private TextField logsTotalSizeCapField;

    @FXML
    private Label longlabel;

    @FXML
    private Label longlabel1;

    @FXML
    private Label longlabel11;

    @FXML
    private Label longlabel111;

    @FXML
    private Label longlabel112;

    @FXML
    private Label longlabel12;

    @FXML
    private Label longlabel2;

    @FXML
    private Label longlabel21;

    @FXML
    private Label longlabel211;

    @FXML
    private Label longlabel2111;

    @FXML
    private Label longlabel21111;

    @FXML
    private Label longlabel21112;

    @FXML
    private Label longlabel211121;

    @FXML
    private Label longlabel2111211;

    @FXML
    private Label longlabel2111212;

    @FXML
    private Label longlabel21113;

    @FXML
    private TextField maxDbPoolSizeField;

    @FXML
    private TextField maxRequestsCountField;

    @FXML
    private GridPane memoryConfigPane;

    @FXML
    private TextField memoryField;

    @FXML
    private TextField numberOfServicesField;

    @FXML
    private Label parameterForNginxAndApacheLabel;

    @FXML
    private TextField pkcs12CertificateProtectionAlgorithmField;

    @FXML
    private ChoiceBox<String> pkcs12KeyProtectionAlgorithmChoiceBox;

    @FXML
    private TextField proxyConnectTimoutField;

    @FXML
    private TextField proxyReadTimeoutField;

    @FXML
    private TextField proxySendTimeoutField;

    @FXML
    private GridPane publisherEnvPane;

    @FXML
    private TextField refreshExpireField;

    @FXML
    private TextField rootCertPathField;

    @FXML
    private GridPane securityEnvPane;

    @FXML
    private TextField sessionMaxCountField;

    @FXML
    private GridPane settingsEnvPane;

    @FXML
    private ChoiceBox<String> signHashAlgorithmChoiceBox;

    @FXML
    private ChoiceBox<String> signKeyAlgorithmChoiceBox;

    @FXML
    private ChoiceBox<String> signKeyLengthChoiceBox;

    @FXML
    private ChoiceBox<String> signProviderChoiceBox;

    @FXML
    private TextField sslChiphersField;

    @FXML
    private GridPane subjectsEnvPane;

    @FXML
    private TextField tokenExpireField;

    @FXML
    private ChoiceBox<String> useTlsChoiceBox;

    @FXML
    private GridPane userConfigPane;

    @FXML
    private GridPane validationAuthorityEnvPane;

    @FXML
    private ChoiceBox<String> webserverChoiseBox;

    @FXML
    private GridPane webserverConfigPane;

    @FXML
    private TextField webserverConfigPathField;

    @FXML
    private TextField webserverPathField;

    private List<Button> menuButtons = new ArrayList<>();

    private void initializeMenuButtons() {

        menuButtons = List.of(
                buttonWebserver,
                buttonMemory,
                buttonSecurity,
                buttonBackup,
                buttonCa,
                buttonAllService,
                buttonDatabase,
                buttonEventDilivery,
                buttonGateway,
                buttonLogs,
                buttonPublisher,
                buttonScripts,
                buttonSettings,
                buttonUser,
                buttonValidation,
                buttonSubjects
        );
    }

    @FXML
    public void initialize() {
        Map<String, String> configParams = configFileService.readConfigFile();
        initializeMenuButtons();
        initializeConfigFieldsMap();
        initializeChoiceBoxes();
        handleCLicks();
        initializeButtonMap();
        addListeners();
        populateTextFields(configParams);

    }

    private void populateTextFields(Map<String, String> configParams) {
        configTextFields.keySet().forEach(field -> field.setText(configParams.get(configTextFields.get(field))));
        choiceBoxes.keySet().forEach(box -> box.setValue(configParams.get(choiceBoxes.get(box))));
    }

    private void addListeners() {
        configTextFields.keySet().forEach(field -> field.textProperty().addListener((observable, oldValue, newValue) -> {

                configFileService.updateConfigFile(configTextFields.get(field), newValue);

        }));

        choiceBoxes.keySet().forEach(choiceBox -> choiceBox.valueProperty().addListener((observable, oldValue, newValue) ->
                configFileService.updateConfigFile(choiceBoxes.get(choiceBox), newValue.toString())));

    }

    public void handleCLicks() {
        menuButtons.forEach(button -> button.setOnAction(event -> activateButton(button)));
    }

    private void activateButton(Button button) {
        buttonGridPaneMap.keySet().stream().forEach(b -> b.getStyleClass().remove("vbox-button-active"));
        button.getStyleClass().add("vbox-button-active");
        buttonGridPaneMap.get(button).toFront();
    }

    private void initializeButtonMap() {
        buttonGridPaneMap.put(buttonWebserver, webserverConfigPane);
        buttonGridPaneMap.put(buttonBackup, backupConfigPane);
        buttonGridPaneMap.put(buttonUser, userConfigPane);
        buttonGridPaneMap.put(buttonDatabase, databaseConfigPane);
        buttonGridPaneMap.put(buttonAllService, allServicesEnvPane);
        buttonGridPaneMap.put(buttonCa, certificateAuthorityEnvPane);
        buttonGridPaneMap.put(buttonEventDilivery, eventDeliveryEnvPane);
        buttonGridPaneMap.put(buttonPublisher, publisherEnvPane);
        buttonGridPaneMap.put(buttonValidation, validationAuthorityEnvPane);
        buttonGridPaneMap.put(buttonSettings, settingsEnvPane);
        buttonGridPaneMap.put(buttonLogs, logsEnvPane);
        buttonGridPaneMap.put(buttonSecurity, securityEnvPane);
        buttonGridPaneMap.put(buttonGateway, gatewayEnvPane);
        buttonGridPaneMap.put(buttonSubjects, subjectsEnvPane);
        buttonGridPaneMap.put(buttonMemory, memoryConfigPane);
    }

    private void initializeConfigFieldsMap() {

        configTextFields.put(webserverPathField, "webserver_path");
        configTextFields.put(aecaPathField, "aeca_path");
        configTextFields.put(environmentPathField, "environment_path");
        configTextFields.put(cryptotokenPathField, "cryptotoken_path");
        configTextFields.put(webserverConfigPathField, "webserver_config_path");
        configTextFields.put(encryptionKeyPathField, "encryption_key_path");
        configTextFields.put(proxyConnectTimoutField, "proxy_connect_timeout");
        configTextFields.put(proxySendTimeoutField, "proxy_send_timeout");
        configTextFields.put(proxyReadTimeoutField, "proxy_read_timeout");
        configTextFields.put(sslChiphersField, "ssl_ciphers");
        configTextFields.put(backupPathField, "backup_path");
        configTextFields.put(logsBAseField, "logs_base");
        configTextFields.put(archivePathField, "archive_path");
        configTextFields.put(certificaeteSslPath, "certificates_ssl_path");
        configTextFields.put(certificatesAccountPath, "certificates_account_path");
        configTextFields.put(aecaUserField, "aeca_user");
        configTextFields.put(aecaGroupField, "aeca_group");
        configTextFields.put(memoryField, "memory");
        configTextFields.put(maxDbPoolSizeField, "max_db_pool_size");
        configTextFields.put(databaseUsernameField, "database_username");
        configTextFields.put(databasePasswordField, "database_password");
        configTextFields.put(databaseHostField, "database_host");
        configTextFields.put(databasePortField, "database_port");
        configTextFields.put(databaseNameField, "database_name");
        configTextFields.put(rootCertPathField, "root_cert_path");
        configTextFields.put(httpPortField, "http_port");
        configTextFields.put(httpsPortField, "https_port");
        configTextFields.put(hostnameField, "hostname");
        configTextFields.put(numberOfServicesField, "number_of_services");
        configTextFields.put(logsFileMaxSizeField, "logs_file_max_size");
        configTextFields.put(logsMaxHistoryField, "logs_max_history");
        configTextFields.put(logsTotalSizeCapField, "logs_total_size_cap");
        configTextFields.put(internalHttpReadTimeoutField, "internal_http_read_timeout");
        configTextFields.put(internalHttpConnectionTimeoutField, "internal_http_connection_timeout");
        configTextFields.put(apiKeyField, "api_key");
        configTextFields.put(crlSchedulerField, "crl_scheduler");
        configTextFields.put(crlCleanQueuesField, "crl_clean_queues");
        configTextFields.put(emailHostField, "email_host");
        configTextFields.put(emailPortField, "email_port");
        configTextFields.put(emailLoginField, "email_login");
        configTextFields.put(emailPasswordField, "email_password");
        configTextFields.put(emailFromField, "email_from");
        configTextFields.put(emailScheduleField, "email_schedule");
        configTextFields.put(aecaVaPortField, "aeca_va_port");
        configTextFields.put(aecaCdpProtField, "aeca_cdp_port");
        configTextFields.put(aecaCrlPublishPointPattern, "aeca_crl_publish_point_pattern");
        configTextFields.put(aecaCrlDistributionPointPatternField, "aeca_crl_distribution_point_pattern");
        configTextFields.put(aecaDeltaCrlDistributionPointPatternField, "aeca_delta_crl_distribution_point_pattern");
        configTextFields.put(aecaAiaPublishPointPatternField, "aeca_aia_publish_point_pattern");
        configTextFields.put(aecaAiaDistributionPointPatternField, "aeca_aia_distribution_point_pattern");
        configTextFields.put(aecaOcspPatternField, "aeca_ocsp_pattern");
        configTextFields.put(initialCaCommonNameField, "initial_ca_common_name");
        configTextFields.put(initialAdminPrincipalField, "initial_admin_principal");
        configTextFields.put(initialClientPasswordField, "initial_client_password");
        configTextFields.put(initialServerPasswordField, "initial_server_password");
        configTextFields.put(certificateServerNameField, "certificate_server_name");
        configTextFields.put(issuersNameField, "issuers_name");
        configTextFields.put(archiveCronField, "archive_cron");
        configTextFields.put(archiveCronField, "archive_cron");
        configTextFields.put(archiveMilisAgoField, "archive_millis_ago");
        configTextFields.put(sessionMaxCountField, "session_max_count");
        configTextFields.put(tokenExpireField, "token_expire");
        configTextFields.put(refreshExpireField, "refresh_expire");
        configTextFields.put(blockInnactiveAccountDelayField, "block_inactive_account_delay");
        configTextFields.put(blockInnactiveAccountCronField, "block_inactive_account_cron");
        configTextFields.put(maxRequestsCountField, "max_requests_count");
        configTextFields.put(ldapSyncConnectionPointField, "ldap_sync_connection_point");
        configTextFields.put(ldapSyncResource, "ldap_sync_resource");
        configTextFields.put(ldapCleanQueuesField, "ldap_clean_queues");
        configTextFields.put(ldapPartitionSizeField, "ldap_partition_size");
        configTextFields.put(ldapAutomaticallyCertificatesPublicationCronField, "ldap_automatically_certificates_publication_cron='0 0 * * * *'");

    }

    public void initializeChoiceBoxes() {
        webserverChoiseBox.setItems(ChoiceBoxesOptions.webserverChoiceBoxOptions);
        emailEnabledChoiseBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        enableGcDiagnositcsChoiseBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        enableHeapDumpChoiseBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        pkcs12KeyProtectionAlgorithmChoiceBox.setItems(ChoiceBoxesOptions.pkcs12KeyProtectionAlgorithmOptions);
        loggingPkcs12MacProtectionAlgorithmChoiceBox.setItems(ChoiceBoxesOptions.loggingPkcs12MacProtectionAlgorithmOptions);
        pkcs12certificateProtectionAlgorithmChoiceBox.setItems(ChoiceBoxesOptions.pkcs12certificateProtectionAlgorithmOptions);
        emailProtocolChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        emailSmtpAuthChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        emailStartTlsChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        initialCryptographyProviderChoiceBox.setItems(ChoiceBoxesOptions.initialCryptographyProviderOptions);
        initialCryptographyHashAlghorythmChoiceBox.setItems(ChoiceBoxesOptions.initialCryptographyHashAlghorythmOptions);
        initialCryptographyKeyAlghorythmChoiceBox.setItems(ChoiceBoxesOptions.initialCryptographyKeyAlghorythm);
        initialCryptographyKeyBitsChoiceBox.setItems(ChoiceBoxesOptions.initialCryptographyKeyBitsOptions);
        signHashAlgorithmChoiceBox.setItems(ChoiceBoxesOptions.signHashAlgorithmOptions);
        signKeyAlgorithmChoiceBox.setItems(ChoiceBoxesOptions.signKeyAlgorithmOptions);
        signKeyLengthChoiceBox.setItems(ChoiceBoxesOptions.signKeyLengthOptions);
        signProviderChoiceBox.setItems(ChoiceBoxesOptions.signProviderOptions);
        useTlsChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        loggingResponseChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);
        loggingSqlChoiceBox.setItems(ChoiceBoxesOptions.trueFalseChoiceBoxOptions);

        choiceBoxes.put(webserverChoiseBox, "webserver");
        choiceBoxes.put(emailEnabledChoiseBox, "email_enabled");
        choiceBoxes.put(enableGcDiagnositcsChoiseBox, "enable_gc_diagnostic");
        choiceBoxes.put(enableHeapDumpChoiseBox, "enable_heap_dump");
        choiceBoxes.put(pkcs12KeyProtectionAlgorithmChoiceBox, "pkcs12_key_protection_algorithm");
        choiceBoxes.put(loggingPkcs12MacProtectionAlgorithmChoiceBox, "logging_pkcs12_mac_protection_algorithm");
        choiceBoxes.put(pkcs12certificateProtectionAlgorithmChoiceBox, "pkcs12_certificate_protection_algorithm");
        choiceBoxes.put(emailProtocolChoiceBox, "email_protocol");
        choiceBoxes.put(emailSmtpAuthChoiceBox, "email_smtp_auth");
        choiceBoxes.put(emailStartTlsChoiceBox, "email_start_tls");
        choiceBoxes.put(initialCryptographyProviderChoiceBox, "initial_cryptography_provider");
        choiceBoxes.put(initialCryptographyHashAlghorythmChoiceBox, "initial_cryptography_hash_algorithm");
        choiceBoxes.put(initialCryptographyKeyAlghorythmChoiceBox, "initial_cryptography_key_algorithm");
        choiceBoxes.put(initialCryptographyKeyBitsChoiceBox, "initial_cryptography_key_bits");
        choiceBoxes.put(loggingResponseChoiceBox, "logging_response");
        choiceBoxes.put(loggingSqlChoiceBox, "logging_sql");
        choiceBoxes.put(loggingPkcs12MacProtectionAlgorithmChoiceBox, "pkcs12_mac_protection_algorithm");
        choiceBoxes.put(signProviderChoiceBox, "sign_provider");
        choiceBoxes.put(signKeyAlgorithmChoiceBox, "sign_key_algorithm");
        choiceBoxes.put(signKeyLengthChoiceBox, "sign_key_length");
        choiceBoxes.put(signHashAlgorithmChoiceBox, "sign_hash_algorithm");
        choiceBoxes.put(ldapAutomaticallyCertificatesPublicationEnableChoiseBox, "ldap_automatically_certificates_publication_enable");

    }

}
