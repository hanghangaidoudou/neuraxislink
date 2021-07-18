package com.etong.pms.test;

import com.etong.pms.constant.Constant;
import com.etong.pms.domain.Student;
import com.etong.pms.domain.User;
import com.etong.pms.model.dto.UserInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @ClassName TestMain
 * @Author wch
 * @DATE 2019/8/23 20:13
 * @return
 */
public class TestProducerMain {

    public static void main(String[] args) {
        mqProducer();
    }

    public static void mqProducer() {

        try {
            //1、创建工厂连接对象，需要制定ip和端口号
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://3.114.235.173:61615");
            //2、使用连接工厂创建一个连接对象
            Connection connection = connectionFactory.createConnection();
            //3、开启连接
            connection.start();
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Queue queue = session.createQueue("test-queue");
            //6、使用会话对象创建生产者对象
            MessageProducer producer = session.createProducer(queue);

            for (int i = 0; i < 100; i++) {
                //7、使用会话对象创建一个消息对象
                TextMessage textMessage = session.createTextMessage("hello!test-queue" + i);
                //8、发送消息
                producer.send(textMessage);
                System.out.println("发送消息: " + textMessage.getText());
                Thread.sleep(1000);
                System.out.println("=====================================");
            }
            //9、关闭资源
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
