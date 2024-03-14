drop database rest_project;
create database rest_project;
use rest_project;
create table sushi_type (
                            type_id binary(16) primary key,
                            type_name varchar(50) not null);

create table sushi (
                       sushi_id binary(16) primary key,
                       sushi_name varchar(50) not null,
                       price decimal(5, 2) not null,
                       description varchar(1000) not null,
                       type_id binary(16) not null,
                       foreign key (type_id) references sushi_type(type_id));

create table orders (
                        order_id binary(16) primary key,
                        status tinyint not null,
                        total_price decimal(6,2) not null);

create table order_component (
                                 order_id binary(16),
                                 sushi_id binary(16),
                                 amount int,
                                 foreign key (order_id) references orders(order_id) on delete cascade,
                                 foreign key (sushi_id) references sushi(sushi_id),
                                 PRIMARY KEY (order_id, sushi_id));

CREATE PROCEDURE calculate_new_total_price(IN in_order_id binary(16))
BEGIN
    DECLARE total DECIMAL(6, 2);

    -- Вычисляем итоговую стоимость для данного заказа
SELECT IFNULL(SUM(s.price * oc.amount), 0) INTO total
FROM orders o
         LEFT JOIN order_component oc ON o.order_id = oc.order_id
         LEFT JOIN sushi s ON oc.sushi_id = s.sushi_id
WHERE o.order_id = in_order_id;

-- Обновляем поле total_price в таблице orders
UPDATE orders SET total_price = total WHERE order_id = in_order_id;
END;

CREATE TRIGGER update_total_price_trigger AFTER INSERT ON order_component
    FOR EACH ROW
BEGIN
    CALL calculate_new_total_price(NEW.order_id);
END;

CREATE TRIGGER update_total_price_trigger2 AFTER UPDATE ON order_component
    FOR EACH ROW
BEGIN
    CALL calculate_new_total_price(NEW.order_id);
END;

CREATE TRIGGER update_total_price_trigger3 AFTER DELETE ON order_component
    FOR EACH ROW
BEGIN
    CALL calculate_new_total_price(OLD.order_id);
END;
