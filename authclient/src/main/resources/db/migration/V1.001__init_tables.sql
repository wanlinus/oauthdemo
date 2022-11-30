create table if not exists `tb_oauth2_server`
(
    `id`              int auto_increment primary key,
    `registration_id` varchar(32),
    `client_id`       varchar(128),
    `client_secret`   varchar(255),
    `redirect_url`    varchar(255)
);

replace into tb_oauth2_server(id, registration_id, client_id, client_secret, redirect_url)
    value (1, 'auth', 'client_id', 'client_secret', 'http://127.0.0.1:8080/oauth/redirect');