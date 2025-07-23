#!/bin/bash

#Конфигурация развертывания

#Используемый веб-сервер (nginx, apache или cpnginx)
webserver='#CHANGEIT'
#Расположение конфигурции веб-сервера
#Расположение конфигурации по умолчанию:
#- конфигурация nginx располагается по пути /etc/nginx
#- конфигурация cpnginx располагается по пути /etc/opt/cprocsp/cpnginx
#- для AstraLinux конфигурация apache располагается по пути /etc/apache2
#- для RedOS конфигурация apache располагается по пути /etc/httpd
webserver_path='#CHANGEIT'
aeca_path='/opt/aecaCa/dist'
environment_path='/opt/aecaCa/dist/environment'
cryptotoken_path='/opt/aecaCa/dist/cryptotoken'
webserver_config_path='/opt/aecaCa/dist/webserver'
encryption_key_path='/opt/aecaCa/scripts/key'
proxy_connect_timeout='320'
proxy_send_timeout='320'
proxy_read_timeout='720'
#Параметр действует только для вебсерверов nginx и apache
ssl_ciphers=''

#Путь до резервных копий
backup_path='/opt/aecaCa/dist/backup'
#Путь хранения лог-файлов
logs_base='/opt/aecaCa/dist/logs'
#Путь хранения архива журнала событий
archive_path='/opt/aecaCa/dist/archive'
#Путь хранения контейнера, сертификата и ключа web-сервера, а также цепочек сертификатов разрешенных издателей
certificates_ssl_path='/opt/aecaCa/dist/certificates/ssl'
#Путь хранения контейнера сертификата администратора инициализации
certificates_account_path='/opt/aecaCa/dist/certificates/account'

#Конфигурация пользователя
aeca_user='aeca'
aeca_group='aeca'

#Конфигурация памяти (значение в МБ)
memory='8192'
enable_gc_diagnostic='false'
enable_heap_dump='false'

#Конфигурация БД
max_db_pool_size='200'
use_tls='false'

database_username='aeca'
database_password='#CHANGEIT'
database_host='localhost'
database_port='5432'
database_name='aecaca'
root_cert_path='#CHANGEIT'

#Конфигурация aeca-ca
http_port='80'
https_port='443'
hostname='localhost'

#Переменные окружения, используемые всеми сервисами
number_of_services='17'
logging_response='false'
logging_sql='false'
#Переменные окружения для logback
logs_file_max_size='10MB'
logs_max_history='10'
logs_total_size_cap='100MB'
#Максимальный таймаут ожидания ответа методов сервисов при внутреннем взаимодействии. Единица измерения – секунды.
internal_http_read_timeout='240'
#Максимальный таймаут ожидания подключения к сервисам при внутреннем взаимодействии. Единица измерения – секунды.
internal_http_connection_timeout='60'
#Ключ для внутренней аутентификации
api_key='2d2ec9b4-ad3d-4ed0-8961-d2a4ab99d810'

#Переменные окружения, используемые certificate-authority-service
#Алгоритм хеширования для ключа контейнера PKCS12
#Рекомендуется использовать алгоритм PBEWithHmacSHA256AndAES_256; устаревший алгоритм PBEWithSHA1AndDESede
pkcs12_key_protection_algorithm=PBEWithHmacSHA256AndAES_256

#Алгоритм хеширования MAC контейнера PKCS12
#Рекомендуется использовать алгоритм HmacPBESHA256; устаревший алгоритм HmacPBESHA1
pkcs12_mac_protection_algorithm=HmacPBESHA256

#Алгоритм хеширования для сертификата контейнера PKCS12
#Рекомендуется использовать алгоритм PBEWithHmacSHA256AndAES_256; устаревший алгоритм PBEWithSHA1AndRC2_40
pkcs12_certificate_protection_algorithm=PBEWithHmacSHA256AndAES_256


#Переменные окружения, используемые publisher-service
#CRON выражение, по которому запускается служба выпуска CRL
crl_scheduler='0 */1 * * * *'
#CRON выражение, по которому очищаются очереди службы выпуска CRL
crl_clean_queues='0 */30 * * * *'

#Переменные окружения, используемые event-delivery-service
#Хост почтового сервера
email_host='127.0.0.1'
#Порт почтового сервера
email_port='25'
#Логин пользователя
email_login='aeca'
#Пароль пользователя
email_password='aeca'
#Почтовый адрес, "с которого отправлено сообщение". Может не работать. Google проставляет логин
email_from='no_reply@aeca.ru'
#CRON для запуска метода отправки почтовых уведомлений
email_schedule='0 0 12 * * *'
#Флаг отправки почтовых уведомлений, если выкл. то сообщения не отправляются
email_enabled='true'
#Протокол подключения к почтовому серверу
email_protocol='smtp'
#Флаг: использование SMTP-авторизации
email_smtp_auth='false'
#Флаг: использование диретивы start tls при подключении к почтовому серверу
email_start_tls='false'

#Переменные окружения, используемые validation-authority-service
#Порт, на котором запущен AECA VA
aeca_va_port='8888'
#Порт, на котором запущен AECA VA - CDP
aeca_cdp_port='8080'
#Шаблон URL точки публикации CRL
aeca_crl_publish_point_pattern='http://{0}:{1}/aecaCdp/api/v2/crl/publish-crl/{2}'
#Шаблон URL точки распространения CRL
aeca_crl_distribution_point_pattern='http://{0}:{1}/aecaCdp/api/v2/crl/get-crl/{2}'
#Шаблон URL точки распространения Delta CRL
aeca_delta_crl_distribution_point_pattern='http://{0}:{1}/aecaCdp/api/v2/crl/get-delta-crl/{2}'
#Шаблон URL точки публикации AIA
aeca_aia_publish_point_pattern='http://{0}:{1}/aecaCdp/api/v2/aia/publish-aia/{2}'
#Шаблон URL точки распространения AIA
aeca_aia_distribution_point_pattern='http://{0}:{1}/aecaCdp/api/v2/aia/get-aia/{2}'
#Шаблон URL сервиса OCSP
aeca_ocsp_pattern='http://{0}:{1}/aeca-va/ocsp'

#Переменные окружения, используемые settings-service
#Криптопровайдер (используется для технологического ЦС и сертификатов веб-сервера и администратора инициализации)
#Выбирается между стандартным - 'EMBEDDED' и КриптоПро - 'CRYPTO_PRO'
initial_cryptography_provider='EMBEDDED'
#Алгоритм ключа (используется для технологического ЦС и сертификатов веб-сервера и администратора инициализации)
#Доступные для выбора значения алгоритмов ключа
#	Для стандартного провайдера (EMBEDDED) - 'RSA' и 'ECDSA'
#	Для провайдера КриптоПро (CRYPTO_PRO) - 'GOST_R_34_10_2012'
initial_cryptography_key_algorithm='RSA'
#Длина ключа (используется для технологического ЦС и сертификатов веб-сервера и администратора инициализации)
initial_cryptography_key_bits='2048'
#Хеш алгоритм (используется для технологического ЦС)
#Доступные для выбора значения алгоритмов хэширования
#	Для стандартного провайдера (EMBEDDED)
#		Для алгоритма ключа 'RSA' доступны алгоритмы хэширования 'SHA1', 'SHA256', 'SHA512', 'SHA384'
#		Для алгоритма ключа 'ECDSA' доступны алгоритмы хэширования 'SHA1', 'SHA256', 'SHA512', 'SHA384'
#	Для провайдера КриптоПро (CRYPTO_PRO)
#		Для алгоритма ключа 'GOST_R_34_10_2012' доступен алгоритм хэширования 'GOST_R_34_11_2012'
initial_cryptography_hash_algorithm='SHA256'
#CN сертификата технологического ЦС
initial_ca_common_name="INITIAL_CA"
#Хеш алгоритм сертификата технологического ЦС
#Имя учетной записи администратора инициализации
initial_admin_principal='INITIAL_ADMIN'
#Алгоритм ключа сертификата администратора инициализации
#Пароль от pkcs12 контейнера сертификата администратора инициализации
initial_client_password='INITIAL'
#Алгоритм ключа сертификата Web-сервера
#Пароль от pkcs12 контейнера сертификата Web-сервера
initial_server_password='INITIAL'
#Шаблон имени файлов сертификата и закрытого ключа сертификата Web-сервера
certificate_server_name='server'
#Шаблон имени файла активных издателей
issuers_name='issuers'

#Переменные окружения, используемые logs-service
#CRON выражение, по которому запускается архивация журнала событий
archive_cron='0 0 0 1 * *'
#Флаг: включена архивация
archive_enabled='true'
#Архивировать записи старше (значение в мс)
archive_millis_ago='15778800000'

#Переменные окружения, используемые security-service
#Максимальное число сессий аккаунта (-1 - ограничение отключено)
session_max_count='100'
#Время жизни JWT токена доступа в миллисекундах
token_expire='180000'
#Время жизни JWT токена обновления в миллисекундах
refresh_expire='86400000'
#Провайдер подписи (выбирается между стандартным - 'EMBEDDED' и КриптоПро - 'CRYPTO_PRO')
sign_provider='EMBEDDED'
#Алгоритм подписи ключа
#Для стандартного провайдера доступны алгоритмы 'RSA' и 'ECDSA'
#Для провайдера КриптоПро доступны алгоритмы 'RSA' и 'GOST_R_34_10_2012'
sign_key_algorithm='RSA'
#Длина ключа подписи
sign_key_length='2048'
#Алгоритм хэширования подписи
#Доступные для выбора значения алгоритмов хэширования
#	Для стандартного провайдера (EMBEDDED)
#		Для алгоритма ключа 'RSA' доступны алгоритмы хэширования 'SHA1', 'SHA256', 'SHA512', 'SHA384'
#		Для алгоритма ключа 'ECDSA' доступны алгоритмы хэширования 'SHA1', 'SHA256', 'SHA512', 'SHA384'
#	Для провайдера КриптоПро (CRYPTO_PRO)
#		Для алгоритма ключа 'GOST_R_34_10_2012' доступен алгоритм хэширования 'GOST_R_34_11_2012'
#		Для алгоритма ключа 'RSA' доступны алгоритмы хэширования 'SHA1', 'SHA256', 'SHA512', 'SHA384'
sign_hash_algorithm='SHA512'
#Для блокировки неактивных аккаунтов в милисекундах
block_inactive_account_delay='0'
#CRON выражение, по которому запускается блокировка неактивных аккаунтов
block_inactive_account_cron='0 0 * * * *'

#Переменные окружения, используемые api-gateway-service
#Максимальное число параллельных HTTP запросов
max_requests_count='30'

#Переменные окружения, используемые subjects-service
#Флаг включения автоматической публикации сертификатов
ldap_automatically_certificates_publication_enable='true'
#Расписание автоматической публикации сертификатов
ldap_automatically_certificates_publication_cron='0 0 * * * *'
#CRON выражение, по которому запускается синхронизация точек подключения ресурсных систем (частичная синхронизация)
ldap_sync_connection_point='0 */30 * * * *'
#CRON выражение, по которому запускается синхронизация ресурсных систем (полная синхронизация)
ldap_sync_resource='0 0 0 * * *'
#CRON выражение, по которому запускается очистка необработанных элементов очередей на синхронизацию
ldap_clean_queues='0 */30 * * * *'
#Максимальное количество объектов, получаемых из ресурсных систем при каждом запросе.
ldap_partition_size='1000'
