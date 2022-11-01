-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 01 2022 г., 22:50
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `aviacom`
--

-- --------------------------------------------------------

--
-- Структура таблицы `airplane`
--

CREATE TABLE `airplane` (
  `id` bigint NOT NULL,
  `business_class_clients_count` int NOT NULL,
  `economic_class_clients_count` int NOT NULL,
  `first_class_clients_count` int NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `layout_type_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `airplane`
--

INSERT INTO `airplane` (`id`, `business_class_clients_count`, `economic_class_clients_count`, `first_class_clients_count`, `model`, `layout_type_id`) VALUES
(315, 12, 4, 20, 'Sukhoi Superjet 100', 13),
(467, 1, 11, 0, 'Ribalet', 15);

-- --------------------------------------------------------

--
-- Структура таблицы `airplane_voyage_list`
--

CREATE TABLE `airplane_voyage_list` (
  `airplane_id` bigint NOT NULL,
  `voyage_list_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `booking`
--

CREATE TABLE `booking` (
  `id` bigint NOT NULL,
  `date_end` text,
  `client_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `city`
--

CREATE TABLE `city` (
  `id` bigint NOT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `city`
--

INSERT INTO `city` (`id`, `description`, `name`, `country_id`) VALUES
(510, '123', 'Москва', 509),
(511, '123', 'Ставрополь', 509);

-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE `client` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passport_number` varchar(255) DEFAULT NULL,
  `passport_series` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `client`
--

INSERT INTO `client` (`id`, `email`, `name`, `passport_number`, `passport_series`, `password`, `patronymic`, `phone`, `surname`) VALUES
(462, 'ikriler@inbox.ru', 'Москва', '123452', '1234', '$2a$10$hnXiz8EZH42vejcVmVfJle0CAIisjJGMiYJYqntAAQ8ceStYIEH7W', 'Александрович', '+7(999)999-99-93', 'Иванов'),
(464, '1235@mail.ru', '123', '123457', '1234', '$2a$10$tLwngDL0duM03xjAEVAg1OdO/54bS0BB5EkqpQZMFuLwQ5hCXhHCq', '123', '+7(999)999-99-96', '123'),
(465, 'ds215@mail.ru', 'ИИгорь', '123888', '1234', '$2a$10$FHttYEBY7qBVu1GT.Any4Odrmukeapfs49GtPnhvoIJrERmwsdsvW', 'Семечко', '+7(999)999-99-94', 'Феофан');

-- --------------------------------------------------------

--
-- Структура таблицы `country`
--

CREATE TABLE `country` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(3, 'Австралия'),
(509, 'Россия'),
(5, 'США'),
(508, 'Япония');

-- --------------------------------------------------------

--
-- Структура таблицы `employee`
--

CREATE TABLE `employee` (
  `id` bigint NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `employee`
--

INSERT INTO `employee` (`id`, `login`, `name`, `password`, `patronymic`, `phone`, `surname`, `post_id`) VALUES
(355, 'Админ', 'Москва', '$2a$10$/kJ/Y4P1hNlIeWLplT9lSOnypmydrU9hpMdhHZsSYwoByBvP/d9Qa', 'Бобрович', '+7(999)123-99-99', 'Иванов', 33),
(358, 'Диспетчер', 'Диспетчер', '$2a$10$1TVmTM9HeSl3nVCGpJfFcOY/5KUpV5W5ed/RhfLEQg73s4VhHEm.S', 'Диспетчер', '+7(999)999-32-99', 'Диспетчер', 35),
(359, 'Агент', 'Агент', '$2a$10$O2xVi3.H9BE829deJ5Ub4uWS9f0OZE/30NM.fzozfhqrqdt5b7IwC', 'Агент', '+7(999)999-99-32', 'Агент', 36);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(553);

-- --------------------------------------------------------

--
-- Структура таблицы `layout_type`
--

CREATE TABLE `layout_type` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `layout_type`
--

INSERT INTO `layout_type` (`id`, `name`) VALUES
(15, 'Большая'),
(13, 'Малая'),
(14, 'Средняя');

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `post`
--

INSERT INTO `post` (`id`, `name`, `alias`) VALUES
(33, 'ADMIN', 'Админ'),
(34, 'CASHIER', 'Кассир'),
(35, 'AIRDROME', 'Диспетчер'),
(36, 'BOOKING', 'Агент бронирования'),
(37, 'PERSONNEL', 'Кадровик');

-- --------------------------------------------------------

--
-- Структура таблицы `sale`
--

CREATE TABLE `sale` (
  `id` bigint NOT NULL,
  `sale_date` datetime(6) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `sale`
--

INSERT INTO `sale` (`id`, `sale_date`, `client_id`, `employee_id`, `ticket_id`) VALUES
(551, '2022-11-01 00:00:00.000000', 462, NULL, 514);

-- --------------------------------------------------------

--
-- Структура таблицы `seat_class`
--

CREATE TABLE `seat_class` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `seat_class`
--

INSERT INTO `seat_class` (`id`, `name`, `price`) VALUES
(16, 'Эконом', 0),
(17, 'Бизнес', 25),
(18, 'Первый', 50);

-- --------------------------------------------------------

--
-- Структура таблицы `ticket`
--

CREATE TABLE `ticket` (
  `id` bigint NOT NULL,
  `price` double DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  `seat_class_id` bigint DEFAULT NULL,
  `voyage_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `ticket`
--

INSERT INTO `ticket` (`id`, `price`, `seat`, `seat_class_id`, `voyage_id`) VALUES
(513, 12000, '1A', 18, 512),
(514, 12000, '1B', 18, 512),
(515, 12000, '2A', 18, 512),
(516, 12000, '2B', 18, 512),
(517, 12000, '3A', 18, 512),
(518, 12000, '3B', 18, 512),
(519, 12000, '4A', 18, 512),
(520, 12000, '4B', 18, 512),
(521, 12000, '5A', 18, 512),
(522, 12000, '5B', 18, 512),
(523, 12000, '6A', 18, 512),
(524, 12000, '6B', 18, 512),
(525, 12000, '7A', 18, 512),
(526, 12000, '7B', 18, 512),
(527, 12000, '8A', 18, 512),
(528, 12000, '8B', 18, 512),
(529, 12000, '9A', 18, 512),
(530, 12000, '9B', 18, 512),
(531, 12000, '10A', 18, 512),
(532, 12000, '10B', 18, 512),
(533, 10000, '11A', 17, 512),
(534, 10000, '11B', 17, 512),
(535, 10000, '12A', 17, 512),
(536, 10000, '12B', 17, 512),
(537, 10000, '13A', 17, 512),
(538, 10000, '13B', 17, 512),
(539, 10000, '14A', 17, 512),
(540, 10000, '14B', 17, 512),
(541, 10000, '15A', 17, 512),
(542, 10000, '15B', 17, 512),
(543, 10000, '16A', 17, 512),
(544, 10000, '16B', 17, 512),
(545, 8000, '17A', 16, 512),
(546, 8000, '17B', 16, 512),
(547, 8000, '18A', 16, 512),
(548, 8000, '18B', 16, 512);

-- --------------------------------------------------------

--
-- Структура таблицы `voyage`
--

CREATE TABLE `voyage` (
  `id` bigint NOT NULL,
  `date_time_inc` text,
  `date_time_out` text,
  `airplane_id` bigint DEFAULT NULL,
  `city_inc_id` bigint DEFAULT NULL,
  `city_out_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `voyage`
--

INSERT INTO `voyage` (`id`, `date_time_inc`, `date_time_out`, `airplane_id`, `city_inc_id`, `city_out_id`) VALUES
(512, '2022-11-23 01:06', '2022-11-30 00:06', 315, 510, 511);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `airplane`
--
ALTER TABLE `airplane`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2cxiac7q4m6ucyrpo26rsrpdo` (`layout_type_id`);

--
-- Индексы таблицы `airplane_voyage_list`
--
ALTER TABLE `airplane_voyage_list`
  ADD UNIQUE KEY `UK_q64cv8pxsx08rdarppfqdwwoh` (`voyage_list_id`),
  ADD KEY `FK24cr7pnf4xnuna9v0g78w0x5o` (`airplane_id`);

--
-- Индексы таблицы `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhs7eej4m2orrmr5cfbcrqs8yw` (`client_id`),
  ADD KEY `FK1dnnhqt4wl3v6a72hxiarf7lg` (`employee_id`),
  ADD KEY `FKte7386dwsq1v3bgg8bbfe9nuq` (`ticket_id`);

--
-- Индексы таблицы `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qsstlki7ni5ovaariyy9u8y79` (`name`),
  ADD KEY `FKrpd7j1p7yxr784adkx4pyepba` (`country_id`);

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`);

--
-- Индексы таблицы `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`);

--
-- Индексы таблицы `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcm3b9d5fiw8s6co7lkw8c0lbs` (`post_id`);

--
-- Индексы таблицы `layout_type`
--
ALTER TABLE `layout_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_h884kghfjdurqek6s02lla6w1` (`name`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKon0o9ba5ajsnwivekhl1tfjiy` (`client_id`),
  ADD KEY `FKmct0j4p9ggcme3rlx7006bya1` (`employee_id`),
  ADD KEY `FKbimqd12gjex7dniuhauseoeo2` (`ticket_id`);

--
-- Индексы таблицы `seat_class`
--
ALTER TABLE `seat_class`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf3j5pc5wwxy1tt60owit4r9qq` (`seat_class_id`),
  ADD KEY `FKdc16jbdeel6rd71vyoe8anvfg` (`voyage_id`);

--
-- Индексы таблицы `voyage`
--
ALTER TABLE `voyage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrwe0270tenm6ckb80g8i75nu9` (`airplane_id`),
  ADD KEY `FKir6a4lqv3qne5fg2hqm6ofius` (`city_inc_id`),
  ADD KEY `FKd2y8b1m46lw1dsaj9dgqff403` (`city_out_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `airplane`
--
ALTER TABLE `airplane`
  ADD CONSTRAINT `FK2cxiac7q4m6ucyrpo26rsrpdo` FOREIGN KEY (`layout_type_id`) REFERENCES `layout_type` (`id`);

--
-- Ограничения внешнего ключа таблицы `airplane_voyage_list`
--
ALTER TABLE `airplane_voyage_list`
  ADD CONSTRAINT `FK24cr7pnf4xnuna9v0g78w0x5o` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
  ADD CONSTRAINT `FKpr72sr3crwj8qk64bvofu6tgw` FOREIGN KEY (`voyage_list_id`) REFERENCES `voyage` (`id`);

--
-- Ограничения внешнего ключа таблицы `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `FK1dnnhqt4wl3v6a72hxiarf7lg` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKhs7eej4m2orrmr5cfbcrqs8yw` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKte7386dwsq1v3bgg8bbfe9nuq` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`);

--
-- Ограничения внешнего ключа таблицы `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `FKrpd7j1p7yxr784adkx4pyepba` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`);

--
-- Ограничения внешнего ключа таблицы `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKcm3b9d5fiw8s6co7lkw8c0lbs` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Ограничения внешнего ключа таблицы `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `FKbimqd12gjex7dniuhauseoeo2` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  ADD CONSTRAINT `FKmct0j4p9ggcme3rlx7006bya1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKon0o9ba5ajsnwivekhl1tfjiy` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Ограничения внешнего ключа таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FKdc16jbdeel6rd71vyoe8anvfg` FOREIGN KEY (`voyage_id`) REFERENCES `voyage` (`id`),
  ADD CONSTRAINT `FKf3j5pc5wwxy1tt60owit4r9qq` FOREIGN KEY (`seat_class_id`) REFERENCES `seat_class` (`id`);

--
-- Ограничения внешнего ключа таблицы `voyage`
--
ALTER TABLE `voyage`
  ADD CONSTRAINT `FKd2y8b1m46lw1dsaj9dgqff403` FOREIGN KEY (`city_out_id`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `FKir6a4lqv3qne5fg2hqm6ofius` FOREIGN KEY (`city_inc_id`) REFERENCES `city` (`id`),
  ADD CONSTRAINT `FKrwe0270tenm6ckb80g8i75nu9` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
