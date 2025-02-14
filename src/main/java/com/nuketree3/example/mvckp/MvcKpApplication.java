package com.nuketree3.example.mvckp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcKpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcKpApplication.class, args);
    }


    /*
    Реализовать поиск: +
    регистрация через почту: +
    (!) сообщать пользователю о статусах(письмо отправлено/не отправлено): -
    оформление заказа: +
    комментарии: +
    админов и панель администрации: +- (нормально оформить select и правильно обработать запросы, которые ничего не возвращают): +
    вернуть картинки: +
    (!) ui сделать нормальный: :)
    добавить связь один ко многим (при удалении пользователя удалялись все его коменты, роли и тп): + реализован с помощью триггера
    при пустой корзине нельзя ничего заказать: +
     */
}
