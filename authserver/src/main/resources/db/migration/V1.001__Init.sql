create table if not exists tb_user
(
    `id`       int auto_increment primary key,
    `name`     varchar(32) unique not null,
    `password` varchar(128)       not null
);
replace into tb_user(id, name, password) value
    (1, 'wanli', '$2a$10$1wibYn3kh1FhLZkG3ZpMEepqhAVJWxzt3tDb7tSVXsTJNc6G4dXZG');

create table if not exists tb_register_client
(
    `id`                       int auto_increment primary key,
    `client_id`                varchar(32),
    `client_secret`            varchar(128),
    `redirect_url`             varchar(256),
    `client_name`              varchar(32),
    `client_id_issued_at`      datetime,
    `client_secret_expires_at` datetime,
    `scopes`                   varchar(128)
);

replace into tb_register_client(id, client_id, client_secret, redirect_url, client_name, client_id_issued_at,
                                client_secret_expires_at, scopes)
    VALUE (1, 'client_id', 'client_secret', 'http://127.0.0.1:8080/login/oauth2/code/app', 'client_name', now(), '2122-01-01 00:00:00',
           'read')