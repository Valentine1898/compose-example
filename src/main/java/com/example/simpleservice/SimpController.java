package com.example.simpleservice;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpController {

    private final RabbitTemplate rabbitTemplate;

    private final EntityDao entityDao;

    @Autowired
    public SimpController(RabbitTemplate rabbitTemplate, EntityDao entityDao) {
        this.rabbitTemplate = rabbitTemplate;
        this.entityDao = entityDao;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> method() {

        String result = "<p> Все хорошо, я работаю";
        try {
            rabbitTemplate.convertAndSend("test", SimpEntity.builder()
                    .id(2L)
                    .name("MAsha"));
            result = result + "<p> В очередь ребита удачно добавлена запись";
        } catch (Exception e) {
            e.printStackTrace();
            result = result + "<p> При добавлении в очередь вылетел ексепшн (с ребитом что-то не та)";
        }

        try {
            entityDao.saveAndFlush(SimpEntity.builder()
                    .id(System.currentTimeMillis())
                    .name("Masha")
                    .build());
            result = result + "<p> Успешно добавлена запись в БД ";
        } catch (Exception e) {
            e.printStackTrace();
            result = result + "<p> Не удалось записать в БД (постгресу плохо)";

        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


}
